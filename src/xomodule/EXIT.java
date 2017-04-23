package xomodule;

public class EXIT implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		UserList.getInstance().remove(UserList.getInstance().getById(str[2]));
		NetworkManager nm = NetworkManager.getInstance();
		for(Room r: RoomSqlManager.getInstance())
			if(r.first.id.equals(str[2]))
			{
				nm.sendAuto("OPPONENT_EXIT", r.second.id);
				RoomSqlManager.getInstance().remove(r);
				return;
			}
			else if(r.second.id.equals(str[2]))
			{
				nm.sendAuto("OPPONENT_EXIT", r.first.id);
				RoomSqlManager.getInstance().remove(r);
				return;
			}
		new UTIL().sendAll("POTENTIAL_OPPONENT_EXIT");
		
	}

}
