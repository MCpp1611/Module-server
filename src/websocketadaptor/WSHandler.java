package websocketadaptor;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WSHandler extends WebSocketHandler
{

	@Override
	public void configure(WebSocketServletFactory arg0)
	{
		arg0.register(ListeningSocket.class);
		
	}

}
