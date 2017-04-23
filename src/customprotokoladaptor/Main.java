package customprotokoladaptor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Thread
{
	volatile DataInputStream dis;
	public static void main(String[] args) throws IOException
	{
		new Thread(new Main()).start();

	}
	@Override
	public void run()
	{
		ArrayList<ListeningSocket> al = new ArrayList<ListeningSocket>();
		try
		{
			Socket s = new Socket("localhost", 727);
			ServerSocket ss = new ServerSocket(728);
			dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			new ServerCommandListener(dis, al);
			dos.writeUTF("Registration;customprotokoladaptor;qwerty;v1.0;");
			dos.flush();
			while(true)
			{
				al.add(new ListeningSocket(ss.accept(), dos));
			}


		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

	}

}
