package newserver;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
/**
 * ���������
 *
 */
public class Sender implements Runnable
{
	volatile BlockingDeque<Msg> queue = new LinkedBlockingDeque<Msg>(); 
	private Sender()
	{
		new Thread(this).start();
	}
	private static class SingletonHolder
	{
		private static Sender s = new Sender();
	}
	public static Sender getInstance()
	{
		return SingletonHolder.s;
	}
	@Override
	public void run()
	{
		ClientsSingleton cs = ClientsSingleton.getInstance();
		try
		{
			System.out.println("sender");
			while(true)
			{
				Msg s = queue.poll();
				if(s != null)
				{
					System.out.println("test");
					String[] str = s.msg.split("[;]");
//					if(!str[0].equals("Redirect"))
//					{
						try{
							ICommand ic = (ICommand) Class.forName("newserver.I" + str[0]).newInstance();
							ic.doCommand(s);
						}catch (Exception e) {
							e.printStackTrace();
							continue;
						}
						System.out.println("register");
//					}
//					else
//					{
//						System.out.println("sending");
//						System.out.println(1);
//						DataOutputStream sock = null;
//						str[2] = str[2].trim();
//						System.out.println(2);
//						for(int i = 0; i < cs.clients.size(); i++)
//						{
//							if(cs.clients.get(i).name.equals(str[2]))
//							{
//								System.out.println(3);
//								sock = cs.clients.get(i).out;
//								System.out.println("Target finded");
//								break;
//							}
//						}
//						System.out.println(4);
//						Modyle m = SQLManager.get(str[1]);
//						System.out.println(5);
//						if(m == null)
//						{
//							System.out.println("not have sender");
//							continue;
//						}
//						System.out.println(6);
//						if(sock == null)
//						{
//							System.out.println("not have target\"" + str[2] + "\"");
//							SQLManager.show();
//							continue;
//						}
//						System.out.println(7);
//						String buff = s.sender.name + ";";
//						for(int i = 3; i < str.length; i++)
//							buff+=str[i]+";";
//						System.out.println(8);
//						sock.writeUTF(buff);
//						System.out.println(9);
//						sock.flush();
//						System.out.println(10);
//						
//					}
				}
				else
					Thread.sleep(100);
			}
		} catch (Exception e)
		{
			System.out.println("err");
			e.printStackTrace();
		}
		
	}

}
