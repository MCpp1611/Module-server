package customprotokoladaptor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListeningSocket extends Thread
{
	Socket sc;
	DataInputStream in;
	volatile DataOutputStream out;
	public String name;
	private DataOutputStream s;
	final String unid;
	private ArrayList<String> modules = new ArrayList<String>();
	public ListeningSocket(Socket sc, DataOutputStream s)
	{
		System.out.println("client");
		this.sc = sc;
		this.s = s;
		start();
		unid = hashCode() + ":customprotokoladaptor";
	}
	@Override
	public void run()
	{
		try
		{
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
			while(true)
			{
				String str = in.readUTF();
				if(str.equals(""));
				String module = str.split("[;]")[0];
				if(modules.indexOf(module) == -1)
					modules.add(module);
				s.writeUTF("Redirect;customprotokoladaptor;"+str+(str.charAt(str.length()-1)==';'?"":";")+unid+";");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			for(int i = 0; i < modules.size(); i++)
				try
				{
					s.writeUTF("Redirect;customprotokoladaptor;"+modules.get(i)+";EXIT;"+unid+";");
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			//cs.clients.remove(this);
		}
		interrupt();
	}
	@Override
	public String toString()
	{
		return ""+unid;
	}

}
