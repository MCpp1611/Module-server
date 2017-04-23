package xomodule;

import java.util.Arrays;

public class COURSE implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		RoomSqlManager rsm = RoomSqlManager.getInstance();
		Room r = rsm.get(str[3]);
		if(r == null)
			return;
		if(!r.next_course.equals(str[4]))
			return;
		int target = Integer.parseInt(str[2]);
		if(target > 9 || target < 1)
			return;
		target--;
		if(r.map[target/3][target%3] != ' ')
			return;
		NetworkManager nm = NetworkManager.getInstance();
		if(str[4].equals(r.first.id))
		{
			nm.sendAuto("NEW_COURSE;"+(target+1)+";x", r.first.id);
			nm.sendAuto("NEW_COURSE;"+(target+1)+";x", r.second.id);
		}
		else
		{
			nm.sendAuto("NEW_COURSE;"+(target+1)+";o", r.first.id);
			nm.sendAuto("NEW_COURSE;"+(target+1)+";o", r.second.id);
		}
		if(str[4].equals(r.first.id))
		{
			r.map[target/3][target%3] = 'x';
			r.next_course = r.second.id;
			nm.sendAuto("YOUR_COURSE", r.second.id);
		}
		else
		{
			r.map[target/3][target%3] = 'o';
			r.next_course = r.first.id;
			nm.sendAuto("YOUR_COURSE", r.first.id);
		}
		if(isWin(r, 'x'))
		{
			nm.sendAuto("WIN;" + winCoordinate(r, 'x'), r.first.id);
			nm.sendAuto("LOSE;" + winCoordinate(r, 'x'), r.second.id);
			rsm.remove(r);
			UserList.getInstance().register(r.first);
			UserList.getInstance().register(r.second);
			new UTIL().sendAllWithoutOne("NEW_OPPONENT;"+r.first.login, r.first.login);
			new UTIL().sendAllWithoutOne("NEW_OPPONENT;"+r.second.login, r.second.login);
		}
		else if(isWin(r, 'o'))
		{
			nm.sendAuto("LOSE;" + winCoordinate(r, 'o'), r.first.id);
			nm.sendAuto("WIN;" + winCoordinate(r, 'o'), r.second.id);
			rsm.remove(r);
			UserList.getInstance().register(r.first);
			UserList.getInstance().register(r.second);
			new UTIL().sendAllWithoutOne("NEW_OPPONENT;"+r.first.login, r.first.login);
			new UTIL().sendAllWithoutOne("NEW_OPPONENT;"+r.second.login, r.second.login);
		}
		else if(isDraw(r))
		{
			nm.sendAuto("DRAW;", r.first.id);
			nm.sendAuto("DRAW;", r.second.id);
			rsm.remove(r);
			UserList.getInstance().register(r.first);
			UserList.getInstance().register(r.second);
			new UTIL().sendAllWithoutOne("NEW_OPPONENT;"+r.first.login, r.first.login);
			new UTIL().sendAllWithoutOne("NEW_OPPONENT;"+r.second.login, r.second.login);
		}
		System.out.println(Arrays.toString(r.map[0]));
		System.out.println(Arrays.toString(r.map[1]));
		System.out.println(Arrays.toString(r.map[2]));

	}
	
	private boolean isWin(Room r, char c)
	{
		return (r.map[0][0] == c && r.map[0][1] == c && r.map[0][2] == c) || (r.map[1][0] == c && r.map[1][1] == c && r.map[1][2] == c) || (r.map[2][0] == c && r.map[2][1] == c && r.map[2][2] == c) || (r.map[0][0] == c && r.map[1][0] == c && r.map[2][0] == c) || (r.map[0][1] == c && r.map[1][1] == c && r.map[2][1] == c) || (r.map[0][2] == c && r.map[1][2] == c && r.map[2][2] == c) || (r.map[0][0] == c && r.map[1][1] == c && r.map[2][2] == c) || (r.map[0][2] == c && r.map[1][1] == c && r.map[2][0] == c);

	}
	
	private String winCoordinate(Room r, char c)
	{
		if(r.map[0][0] == c && r.map[0][1] == c && r.map[0][2] == c)
			return "1;3;";
		else if(r.map[1][0] == c && r.map[1][1] == c && r.map[1][2] == c)
			return "4;6;";
		else if(r.map[2][0] == c && r.map[2][1] == c && r.map[2][2] == c)
			return "7;9;";
		else if(r.map[0][0] == c && r.map[1][0] == c && r.map[2][0] == c)
			return "1;7;";
		else if(r.map[0][1] == c && r.map[1][1] == c && r.map[2][1] == c)
			return "2;8;";
		else if(r.map[0][2] == c && r.map[1][2] == c && r.map[2][2] == c)
			return "3;9;";
		else if(r.map[0][0] == c && r.map[1][1] == c && r.map[2][2] == c)
			return "1;9;";
		else if(r.map[0][2] == c && r.map[1][1] == c && r.map[2][0] == c)
			return "3;7;";
		return "";
	}
	
	private boolean isDraw(Room r)
	{
		for(int i = 0; i < 3; i++)
			for(int a = 0; a < 3; a++)
				if(r.map[i][a] == ' ')
					return false;
		return true;

	}

}
