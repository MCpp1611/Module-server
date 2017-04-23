package newserver;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable
{
	Sender s;
	
	public static void main(String[] args)
	{
		new Thread(new Server()).start();

	}
	@Override
	public void run()
	{
		try(ServerSocket ss = new ServerSocket(727);)
		{
			
			s = Sender.getInstance();
			while(true)
			{
				new ListeningSocket(ss.accept(), s);
			}
			

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

	}

}
