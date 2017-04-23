package CHATGUI.Logic.modules.fromserver;

import CHATGUI.ChatManager;
import CHATGUI.Logic.modules.ICommand;

public class GET_ROOMS implements ICommand
{

	@Override
	public void doCommand(String command)
	{
		String[] str = command.split("[;]");
		ChatManager m = ChatManager.getInstance();
		for(int i = 1; i < str.length; i++)
			m.doCommand("NEW_ROOM;"+str[i]+";");
		
	}

}
