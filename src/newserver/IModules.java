package newserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class IModules implements ICommand
{

	@Override
	public void doCommand(Msg msg)
	{
		String[] str = msg.msg.split("[;]");
		StringBuffer buff = new StringBuffer();//msg.sender.name + ";";
		ArrayList<Modyle> modules = SQLManager.all();
		for(int i = 0; i < modules.size(); i++)
			buff.append(modules.get(i).toString()+";");
		Sender.getInstance().queue.add(new Msg("Redirect;Server;"+msg.senderName+";"+buff.toString()+";", "Server"));
		
		
	}

}
