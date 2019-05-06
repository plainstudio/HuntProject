package com.hunter.user;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
	Socket socket;
	int port =7777;
	MessageThread messageThread;
	
	public ClientMain() {
		connect();
	}
	public void connect() {
		String ip = "192.168.0.24"; //#######나중에 관리자용 컴퓨터 ip 받아오기( 선생님 컴퓨터 )
		try {
			socket = new Socket(ip, port);
			messageThread = new MessageThread(this, socket);
			messageThread.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("client 연결 성공!");
		
	}
	public void sendData(String requestType) {
		System.out.println("requestType : "+requestType+" 왔네?");
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("	\"requestType\":\""+requestType+"\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}
}
