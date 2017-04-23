package websocketadaptor;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Server server = new Server();
	    ServerConnector connector = new ServerConnector(server);
	    connector.setPort(8080);
	    server.addConnector(connector);

	    // add first handler
	    ResourceHandler resource_handler = new ResourceHandler();
	    resource_handler.setDirectoriesListed(true);
	    resource_handler.setWelcomeFiles(new String[]{ "index.html" });
	    resource_handler.setResourceBase("C:\\Users\\Artem\\Documents\\WebstormProjects\\XO");

	    HandlerList handlers = new HandlerList();
	    // first <a rel="nofollow" target="_blank" class="intext-link" href="#" style="position: relative; font-weight: bold;">element</a>  is webSocket handler, second <a rel="nofollow" target="_blank" class="intext-link" href="#" style="position: relative; font-weight: bold;">element</a> is first handler
	    handlers.setHandlers(new Handler[] {new WSHandler(), resource_handler});
	     
	    server.setHandler(handlers);

	    server.start();
	    server.join();

	}

}
