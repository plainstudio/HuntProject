package com.hunter.user.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.text.BadLocationException;

public class ClientThread extends Thread{
	Socket client;//대화용 소켓
	BufferedReader buffr = null;
	BufferedWriter buffw = null;
	ChatClient chatClient;
	boolean receivedMSG = true;//받은 메시지인가 판단용
	
	public ClientThread(ChatClient chatClient,Socket client) {
		this.chatClient = chatClient;
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버의 메시지 보내기 기능
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버의 메시지 받기 :: 입력스트림
	public void listen() {
		String msg = null;
		receivedMSG = true;//받은 메시지인지 여부
		try {
			msg = buffr.readLine();
			//chatClient.area.append(msg+"\n");
			displayMSG(msg+"\n",receivedMSG);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//메시지를 기성 메신저 스타일로 배치해 출력할 메서드
	public void displayMSG(String msg,boolean flag) {
		try {
			if(flag) {//받은 메시지면
				chatClient.doc.insertString(chatClient.doc.getLength(), msg, chatClient.left);				
				chatClient.doc.setParagraphAttributes(chatClient.doc.getLength(), 1,chatClient.left, false);
			}else {
				chatClient.doc.insertString(chatClient.doc.getLength(), msg, chatClient.right);
				chatClient.doc.setParagraphAttributes(chatClient.doc.getLength(), 1, chatClient.right, false);				
			}
			//flag=!flag;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		while(true) {
			listen();
		}
	}
}
