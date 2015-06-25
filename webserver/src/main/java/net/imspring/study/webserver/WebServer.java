package net.imspring.study.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {	
	public void start(int port){		
		try {
			ServerSocket socketServer  = new ServerSocket(port);
			System.out.println("服务器启动成功");
			while(true){
				Socket Socket= socketServer.accept();
				new Process(Socket).start();				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public static void main(String[] args) {
		int port = 80 ;
		if(args.length ==1) port  = Integer.parseInt(args[0]);
		
		WebServer server =  new WebServer();
		server.start(port);
	
	}
}
