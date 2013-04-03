package org.dhs.pedersen.spraglesnakk.testserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class RunServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
        server.setHandler(new WebAppContext("src/main/webapp", "/"));
     
        server.start();
        server.join();
	}
}
