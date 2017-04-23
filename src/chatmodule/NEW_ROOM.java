package chatmodule;

import java.io.IOException;

public class NEW_ROOM implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		RoomSqlManager rsm = RoomSqlManager.getInstance();
		try
		{
			NetworkManager.getInstance().dos.writeUTF("Redirect;CHAT;customprotokoladaptor;NEW_ROOM;"+rsm.register(new Room(str[2]))+";" + str[3] + ";");
			NetworkManager.getInstance().dos.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
