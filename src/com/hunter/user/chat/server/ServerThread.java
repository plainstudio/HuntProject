package com.hunter.user.chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	ChatServer chatServer;
	Socket clientSocket;
	BufferedReader buffr = null;
	BufferedWriter buffw = null;
	boolean flag = true;
	public ServerThread(ChatServer chatServer,Socket clientSocket) {
		this.chatServer = chatServer;
		try {
			buffr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//���
	public void listen() {
		try {
			String msg = buffr.readLine();
			send(msg);
		} catch (IOException e) {
			System.out.println("Ŭ���̾�Ʈ�� �������ϴ�.");
			flag=!flag;
			//���Ϳ��� ����
			chatServer.list.remove(this);
			e.printStackTrace();
		}
	}
	
	//���ϱ�
	public void send(String msg) {
		try {			
			for(int i=0;i<chatServer.list.size();i++) {
				chatServer.list.get(i).buffw.write(msg+"\n");
				chatServer.list.get(i).buffw.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(flag) {
			listen();
		}
	}
}
