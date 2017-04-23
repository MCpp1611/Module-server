package websocketadaptor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCommandListener implements Runnable
{
	DataInputStream s;
	DataOutputStream out;
	ArrayList<ListeningSocket> al;
	private ServerCommandListener()
	{
		Socket sock;
		try
		{
			sock = new Socket("localhost", 727);
			s = new DataInputStream(sock.getInputStream());
			out = new DataOutputStream(sock.getOutputStream());
			out.writeUTF("Registration;websocketadaptor;qwerty;v1.0;");
			out.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		al = new ArrayList<ListeningSocket>();
		new Thread(this).start();
	}
	
	private static class SingletonHolder
	{
		private static ServerCommandListener instance = new ServerCommandListener();
	}
	
	public static ServerCommandListener getInstance()
	{
		return SingletonHolder.instance;

	}

	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				String tmp = s.readUTF();
				System.out.println("msg"+tmp);
				if(tmp.equals("ok"))
					continue;
				String[] str = tmp.split("[;]");
				for(int i = 0 ; i < al.size(); i++)
				{
					if((""+al.get(i).unid).equals(str[str.length-1]))
					{
						System.out.println("target finded");
						String buff = "";
						for(int a = 0; a < str.length-1; a++)
							buff+=str[a]+";";
						al.get(i).session.getRemote().sendString(buff);
						break;
					}
				}
				al.forEach(System.out::println);
				
			}
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
