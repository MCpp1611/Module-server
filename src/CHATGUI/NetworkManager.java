package CHATGUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManager
{
	private Socket s;
	private volatile DataInputStream dis;
	private volatile DataOutputStream dos;
	private NetworkManager()
	{
		try
		{
			s = new Socket("localhost", 726);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static class SingletonHolder
	{
		private static volatile NetworkManager INSTANCE = new NetworkManager();
	}
	public static NetworkManager getInstance() {
		return SingletonHolder.INSTANCE;
	}
	public String read() throws IOException
	{
		String str = dis.readUTF();
		System.out.println(str);
		return str.substring(str.indexOf(';')+1);
	}
	public void send(String str)
	{
		try
		{
			dos.writeUTF("CHAT;" + str);
			dos.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
