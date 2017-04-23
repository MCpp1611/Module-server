package xomodule;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManager
{
	Socket s;
	DataInputStream dis;
	private DataOutputStream dos;
	private NetworkManager(){
		try
		{
			s = new Socket("localhost", 727);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	private static class SingletonHolder
	{
		private static NetworkManager instance = new NetworkManager();
	}
	public static NetworkManager getInstance()
	{
		return SingletonHolder.instance;
	}
	public void send(String str)
	{
		try
		{
			dos.writeUTF(str);
			dos.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void sendAuto(String arg1, String arg2)
	{
		send("Redirect;XO;"+arg2.substring(arg2.lastIndexOf(':')+1)+";"+arg1+(arg1.charAt(arg1.length()-1)==';'?"":";")+arg2+";");

	}
	public void sendException(String str) throws IOException
	{
		

	}
}
