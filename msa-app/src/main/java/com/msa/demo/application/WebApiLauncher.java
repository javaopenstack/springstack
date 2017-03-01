package com.msa.demo.application;

import com.msa.tools.server.LocalServer;

public class WebApiLauncher {

	public static void main(String argv[]){
		
		LocalServer localServer = new LocalServer(WebApiLauncher.class.getClassLoader().getResourceAsStream("api-config.xml"));
		localServer.startServer();
	}
}
