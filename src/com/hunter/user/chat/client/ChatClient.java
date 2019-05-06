package com.hunter.user.chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.hunter.user.chat.hapseok.AskJoin;

public class ChatClient extends JFrame{
	JPanel p_center;//chatting zone
	JPanel p_center_north;//chat room title
	JPanel p_center_center;//chat text area
	JPanel p_center_south;//chat inserting zone
	JPanel p_east;//btn zone
	
	JLabel la_title;
	JTextPane area;//ä�� �޽����� �ѷ��� ��
	StyledDocument doc;
	SimpleAttributeSet left;
	SimpleAttributeSet right;
	JScrollPane scroll;
	
	JTextArea ta_insertText;
	JButton bt_emoticon;
	JButton bt_insert;
	
	JButton bt_exit;//������
	JButton bt_chadan;//����
	JButton bt_joinTable;//�ռ���û
	
	int port = 9898;
	//��ȭ�� ����
	Socket client;
	ClientThread ct;
	
	//�ռ���û����
	AskJoin askJoin;
	String ip;
	
	public ChatClient(String ip) {
		this.ip = ip;
		//this.askJoin = askJoin;
		BorderLayout bl = new BorderLayout(0,0);
		setLayout(bl);
		p_center = new JPanel();
		p_center_north = new JPanel();
		p_center_center = new JPanel();
		p_center_south = new JPanel();
		
		la_title = new JLabel("�߹����� ��ƿ�~");
		area = new JTextPane();
		scroll = new JScrollPane(area);
		doc = area.getStyledDocument();
		left = new SimpleAttributeSet();
		right = new SimpleAttributeSet();
		
		ta_insertText = new JTextArea();
		bt_emoticon = new JButton("�̸�Ƽ��");
		bt_insert = new JButton("�Է�");
		
		p_east = new JPanel();
		bt_exit = new JButton("������");
		bt_chadan = new JButton("�������");
		bt_joinTable = new JButton("�ռ���û");
		
		//���̾ƿ��� ����
		p_center.setLayout(bl);
		p_center_north.setLayout(bl);
		p_center_center.setLayout(bl);
		//�޽��� ��Ÿ��
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		
		//����
		p_center_north.add(la_title);
		p_center.add(p_center_north,BorderLayout.NORTH);
		p_center_center.add(scroll);
		p_center.add(p_center_center);
		p_center_south.add(ta_insertText);
		p_center_south.add(bt_emoticon);
		p_center_south.add(bt_insert);
		p_center.add(p_center_south,BorderLayout.SOUTH);
		this.add(p_center, BorderLayout.CENTER);
		
		p_east.add(bt_exit);
		p_east.add(bt_chadan);
		p_east.add(bt_joinTable);
		this.add(p_east, BorderLayout.EAST);
		
		//Ʈ���� ���� ����
		connect();
		
		//�޽��� �Է��ϰ� ������
		ta_insertText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent k) {
				int key = k.getKeyCode();
				if(key==KeyEvent.VK_ENTER) {
					ct.send(ta_insertText.getText());
					ta_insertText.setText("");
				}
			}
		});
		bt_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeChat();
			}
		});
		bt_chadan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				blockThisTable();
			}
		});
		bt_joinTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				proposeJoin();
			}
		});
		setVisible(true);
		setSize(500, 700);
		setLocationRelativeTo(null);
	}
	
	//���� ���� �� ���� �޼���
	public void connect() {
		//port��ȣ�� ����� 9898�� ������ �ʱ�ȭ
		String ip="";//���̺�ʿ��� Ŭ������ �Ѱܹ��� ip
		try {
			client = new Socket();
			ct = new ClientThread(this, client);
			ct.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ä�� ����, â �ݱ� �޼���
	public void closeChat() {
		//��ȭ������ �ִٸ� �ݱ������� ��ȭ db�� ����
		//clientThread ����(�ڿ�ȸ��)
		//â �ݾƾ��ϰ�--> visible false��
	}
	
	//���� �޼���
	public void blockThisTable() {}
	
	//�ռ���û �޼���
	public void proposeJoin() {
		//this.askJoin = new AskJoin(this);
		/*
		String[] options= {"��, ��û�ҷ���","�ƴϿ�, ����ҷ���"};
		int ask = JOptionPane.showOptionDialog(this, "�ռ��� ��û�Ͻðڽ��ϱ�?", "�ռ���û", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		switch(ask) {
		case JOptionPane.OK_OPTION:
			JOptionPane.showMessageDialog(this, "������ �ǻ縦 �������Դϴ�.");
			//���濡�� �����Ͻðڽ��ϱ�?�� �������
			break;
		case JOptionPane.CANCEL_OPTION:
			JOptionPane.showMessageDialog(this, "��û�� ����ϼ̽��ϴ�.");
			break;
		}
		*/
	}
}
