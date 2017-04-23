package chatmodule;

import java.io.IOException;

public class GET_ROOMS implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		StringBuffer sb = new StringBuffer("GET_ROOMS;");
		RoomSqlManager.getInstance().all().forEach((r)->{sb.append(r.name);sb.append(";");});
		try
		{
			NetworkManager.getInstance().dos.writeUTF("Redirect;CHAT;customprotokoladaptor;"+sb.toString()+command.split("[;]")[3]+";");
			NetworkManager.getInstance().dos.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
