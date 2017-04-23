package chatmodule;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MainDispatcher extends Thread
{
	public BlockingQueue<String> command = new LinkedBlockingDeque<String>();

	private MainDispatcher()
	{
		start();
	}
	private static class SingletonHolder
	{
		private volatile static MainDispatcher md = new MainDispatcher();
	}
	public static MainDispatcher getInstance()
	{
		return SingletonHolder.md;
	}
	@Override
	public void run()
	{
		while(true)
		{
			String m = command.poll();
			if(m!=null)
			{
				System.out.println(m);
				String[] str = m.split("[;]");
				try
				{
					System.out.println(1);
					ICommand c = (ICommand) Class.forName("chatmodule." + str[1]).newInstance();
					System.out.println(2);
					c.doCommand(m);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
