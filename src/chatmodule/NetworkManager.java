package chatmodule;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManager
{
	Socket s;
	volatile DataInputStream dis;
	volatile DataOutputStream dos;
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
		private static volatile NetworkManager instance = new NetworkManager();
	}
	public static NetworkManager getInstance()
	{
		return SingletonHolder.instance;
	}
}
