package customprotokoladaptor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCommandListener implements Runnable
{
	DataInputStream s;
	ArrayList<ListeningSocket> al;
	public ServerCommandListener(DataInputStream s, ArrayList<ListeningSocket> al)
	{
		this.s = s;
		this.al = al;
		new Thread(this).start();
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
						al.get(i).out.writeUTF(buff);
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
