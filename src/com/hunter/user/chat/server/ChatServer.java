package com.hunter.user.chat.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer{
	ServerSocket server;//������ ������ ����
	Thread serverThread;//���ν����尡 �����¿� ������ �ʰ� �ϴ� ������
	int port = 9876;
	//������ ��ȭ�� ��������� ���� ��ü
	Vector<ServerThread> list = new Vector();
	
	public ChatServer() {
		serverThread = new Thread() {
			public void run() {
				runServer();
			}
		};
		//�̹� ������Ʈ������ bt���� Ʈ���Ű� ��� ���۵Ǿ��־����
		serverThread.start();
	}
	
	//�������� �޼���
	public void runServer() {
		try {
			server = new ServerSocket(port);//���� ����
			
			while(true) {
				Socket client = server.accept();//��������::������ ��ٸ�
				String ip = client.getInetAddress().getHostAddress();
				//���Ӱ� ���ÿ� ��ȭ�� ���� Thread�ƹ�Ÿ ����
				ServerThread st = new ServerThread(this,client);
				st.start();
				
				list.add(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
