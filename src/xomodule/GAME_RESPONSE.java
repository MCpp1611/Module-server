package xomodule;

public class GAME_RESPONSE implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		UserList ul = UserList.getInstance();
		User u = ul.get(str[2]);
		User user = ul.getById(str[4]);
		if(u == null)
		{
			System.out.println("not register");
			return;
		}
		if(!u.lastreq.equals(UserList.getInstance().getById(str[4]).login))
		{
			System.out.println("not required");
			return;
		}
		if(str[3].equals("0"))
		{
			System.out.println("not confirmed");
			u.lastreq = "";
			return;
		}
		RoomSqlManager.getInstance().register(
				new Room(u.id
						+user.id,
						u, user));
		ul.remove(u);
		ul.remove(user);
		new UTIL().sendAll("POTENTIAL_OPPONENT_EXIT");
		NetworkManager nm = NetworkManager.getInstance();
		nm.sendAuto("NEW_ROOM;" + u.id+user.id, u.id);
		nm.sendAuto("NEW_ROOM;" + u.id+user.id, user.id);
		
	}

}
