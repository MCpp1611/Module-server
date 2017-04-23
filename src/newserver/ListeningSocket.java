package newserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ListeningSocket implements Runnable
{
	Socket sc;
	Sender s;
	DataInputStream in;
	volatile DataOutputStream out;
	public String name = "";
	public ListeningSocket(Socket sc, Sender s)
	{
		System.out.println("client");
		this.sc = sc;
		this.s = s;
		new Thread(this).start();
	}
	@Override
	public void run()
	{
    	ClientsSingleton cs = ClientsSingleton.getInstance();
		try
		{
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            String str;
//            = in.readUTF();
//			out.writeUTF("ok");
//            System.out.println("msg"+str);
//            name = str.split("[;]")[0];
//            cs.clients.add(this);
//			s.queue.add(new Msg(str, this));
			while(true)
			{
				str = in.readUTF();
				s.queue.add(new Msg(str, name, this));
				out.writeUTF("ok");
				out.flush();
				System.out.println("msg" + str);
			}
		} catch (Exception e)
		{
			cs.clients.remove(this);
			e.printStackTrace();
			
		}
		
	}

}
