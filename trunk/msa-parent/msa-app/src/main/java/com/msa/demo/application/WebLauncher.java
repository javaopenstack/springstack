package com.msa.demo.application;

import com.msa.tools.server.LocalServer;

public class WebLauncher {

	public static void main(String argv[]){
		
		LocalServer localServer = new LocalServer(WebLauncher.class.getClassLoader().getResourceAsStream("web-config.xml"));
		localServer.startServer();
	}
}
