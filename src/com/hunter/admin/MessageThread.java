package com.hunter.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageThread extends Thread{
	ServerMain serverMain;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag = true;
	
	public MessageThread(ServerMain serverMain,Socket socket) {
		this.serverMain = serverMain;
		this.socket = socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			serverMain.dispatcher.dispatch(msg);
			//######채팅 추가???  채팅방.area.append(msg+"\n") ???
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(flag) {
			listen();
		}
	}
}
