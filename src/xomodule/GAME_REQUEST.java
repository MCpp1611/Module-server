package xomodule;

public class GAME_REQUEST implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		UserList.getInstance().getById(str[3]).lastreq = str[2];
		User u = UserList.getInstance().get(str[2]);
		if(u == null)
			return;
		for(Room r: RoomSqlManager.getInstance())
			if(r.first.id == str[3] || r.second.id == str[3])
				return;
		NetworkManager.getInstance().sendAuto("GAME_REQUEST;"+UserList.getInstance().getById(str[3]).login, UserList.getInstance().get(str[2]).id);
		
	}

}
