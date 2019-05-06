package com.hunter.admin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.hunter.admin.Dispatcher;

public class ServerMain {
	int port = 7777;
	Thread thread;
	ServerSocket server;
	Dispatcher dispatcher;
	public ServerMain() {
		System.out.println("admin 서버 생성!!");
		thread = new Thread() {
			@Override
			public void run() {
				runServer();
			}
		};
		dispatcher = new Dispatcher(this);
		thread.start();
	}
	
	public void runServer() {
		System.out.println("admin 서버 runserver!!");
		try {
			server = new ServerSocket(port);
			System.out.println("서버 가동!!\n");
			Socket socket = server.accept();
			System.out.println("접속자 발생!");
			MessageThread messageThread = new MessageThread(this, socket);
			messageThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
