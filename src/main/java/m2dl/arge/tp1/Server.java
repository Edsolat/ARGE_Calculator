package m2dl.arge.tp1;

import java.io.IOException;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;


public class Server {
	private int port;
	private WebServer webServer;
	
	public Server(int port) throws Exception {
		this.port = port;
		webServer = new WebServer(port);
        
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
      
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        /* Load handler definitions from a property file.
         * The property file might look like:
         *   Calculator=org.apache.xmlrpc.demo.Calculator
         *   org.apache.xmlrpc.demo.proxy.Adder=org.apache.xmlrpc.demo.proxy.AdderImpl
         */
        try {
			phm.load(Thread.currentThread().getContextClassLoader(),
			         "XmlRpcServlet.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /* You may also provide the handler classes directly,
         * like this:
         */ phm.addHandler("Calculator",
        		 m2dl.arge.tp1.Calculator.class);
         
        xmlRpcServer.setHandlerMapping(phm);
      
        XmlRpcServerConfigImpl serverConfig =
            (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);

        try {
			webServer.start();
			System.out.println("Server on port " + port + " started successfuly !");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		this.webServer.shutdown();
	}
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server serv = null;
		int port = Integer.parseInt(args[0]);
		try {
			serv = new Server(port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
