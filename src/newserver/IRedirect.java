package newserver;

import java.io.DataOutputStream;
import java.io.IOException;

public class IRedirect implements ICommand
{

	@Override
	public void doCommand(Msg msg)
	{
		String[] str = msg.msg.split("[;]");
		ClientsSingleton cs = ClientsSingleton.getInstance();
		DataOutputStream sock = null;
		str[2] = str[2].trim();
		for(int i = 0; i < cs.clients.size(); i++)
		{
			if(cs.clients.get(i).name.equals(str[2]))
			{
				sock = cs.clients.get(i).out;
				System.out.println("Target finded");
				break;
			}
		}
		if(!SQLManager.isExist(str[1]))
		{
			System.out.println("not have sender");
			return;
		}
		if(sock == null)
		{
			System.out.println("not have target\"" + str[2] + "\"");
			SQLManager.show();
			return;
		}
		String buff = msg.senderName + ";";
		for(int i = 3; i < str.length; i++)
			buff+=str[i]+";";
		try
		{
			sock.writeUTF(buff);
			sock.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
