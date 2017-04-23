package websocketadaptor;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ListeningSocket
{

	public String unid;
    public Session session;
	private ArrayList<String> modules = new ArrayList<String>();

	@OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());
		unid = hashCode() + ":websocketadaptor";
        this.session = session;
//        try
//		{
//			session.getRemote().sendString("Got your connect message");
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        ServerCommandListener.getInstance().al.add(this);
    }
     
    @OnWebSocketMessage
    public void onText(String message) {
        System.out.println("text: " + message);
		String module = message.split("[;]")[0];
		if(modules.indexOf(module) == -1)
			modules.add(module);
		try
		{
			ServerCommandListener.getInstance().out.writeUTF("Redirect;websocketadaptor;"+message+(message.charAt(message.length()-1)==';'?"":";")+unid+";");
			ServerCommandListener.getInstance().out.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
     
    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    	for(int i = 0; i < modules.size(); i++)
			try
			{
				ServerCommandListener.getInstance().out.writeUTF("Redirect;websocketadaptor;"+modules.get(i)+";EXIT;"+unid+";");
				ServerCommandListener.getInstance().out.flush();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        ServerCommandListener.getInstance().al.remove(this);
    	
    }
	

}
