package CHATGUI.Logic.modules.toserver;

import CHATGUI.NetworkManager;
import CHATGUI.Logic.modules.CmdAdaptor;

public class GET_ROOMS implements ISetData
{

	@Override
	public void toServer(CmdAdaptor listener, String args)
	{
		NetworkManager.getInstance().send("GET_ROOMS;null;");
		
	}

}
