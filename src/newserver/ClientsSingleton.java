package newserver;

import java.util.ArrayList;

public class ClientsSingleton
{
	private static class ClientsSingletonHolder
	{
		private static volatile ClientsSingleton cs = new ClientsSingleton();
	}
	public volatile ArrayList<ListeningSocket> clients = new ArrayList<ListeningSocket>();
	public static ClientsSingleton getInstance()
	{
		return ClientsSingletonHolder.cs;

	}

}
