package com.hunter.user.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hunter.ui.map.TableMap;
import com.hunter.user.User;
import com.hunter.user.UserMain;


public class ChatMain extends JPanel{
	String TAG=this.getClass().getName();
	//Main main;
	UserMain userMain;
	User user;
	
	JPanel p_west;//���̺��,ä����Ȳ ���� �г�
	JPanel p_west_north;//west�� ���̺�� �г�
	JPanel p_west_center;//west�� ä����Ȳ �г�
		
	JPanel p_center;
	JPanel p_center_center;//my chat list���� �г�
	JPanel p_center_south;//btns���� �г�
	
	//TableMap
	TableMap tableMap;//����� ���̺��
	//current chat list
	CurrentChatList currentChatList;
	
	//my chat list
	MyChatList myChatList;
	
	//p_center_center�� ���� ��ư��
	JButton bt_chat_enter;//���� ��ȭ�� ����
	JButton bt_chat_create;//��ȭ�� �����
	JButton bt_exit;//��ȭ�� ������(Ȩ����)
		
	public ChatMain(UserMain userMain,User user) {
		System.out.println(TAG+" chatMAin�� �޸𸮿� �ö󰬴�.");
		this.userMain=userMain;
		this.user = user;
		setLayout(new BorderLayout());
		
		//�޸� �ø���
		p_west = new JPanel();
		//p_west_north = new JPanel();
		//p_west_north�� tableMap�� �ҷ���
		p_west_north = new TableMap(this,this.userMain,this.user);
		//tableMap = new TableMap(this,this.userMain);
		//p_west_center�� currentChatList�� �ҷ���
		currentChatList = new CurrentChatList(this,this.userMain);
		p_west_center = new JPanel();
		
		p_center = new JPanel();
		p_center_center = new JPanel();
		//p_center_center�� myChatList�ҷ�����
		myChatList = new MyChatList(this, this.userMain);
		p_center_south = new JPanel();
		bt_chat_enter = new JButton("���� ��ȭ�� ����");
		bt_chat_create = new JButton("��ȭ�� ����");
		bt_exit = new JButton("������");

		//���̾ƿ�,ũ��,����
		p_west.setLayout(new BorderLayout(0,0));
		p_west.setPreferredSize(new Dimension(700, 900));

		p_west_north.setLayout(new FlowLayout(HEIGHT, 0, 0));
		p_west_north.setPreferredSize(new Dimension(700,450));
		p_west_north.setBackground(Color.GRAY);
		p_west_center.setPreferredSize(new Dimension(700,450));
		p_west_center.setBackground(Color.PINK);
		
		p_center.setLayout(new BorderLayout());
		p_center.setPreferredSize(new Dimension(700, 900));

		p_center_center.setPreferredSize(new Dimension(700,750));
		p_center_south.setLayout(new FlowLayout(WIDTH,0,0));
		p_center_south.setPreferredSize(new Dimension(700, 150));
		p_center_south.setBackground(Color.BLACK);
		
		Dimension d_bt = new Dimension(180, 80);
		bt_chat_enter.setPreferredSize(d_bt);
		bt_chat_create.setPreferredSize(d_bt);
		bt_exit.setPreferredSize(d_bt);
		bt_chat_enter.setBackground(Color.ORANGE);
		bt_chat_create.setBackground(Color.ORANGE);
		bt_exit.setBackground(Color.ORANGE);
		bt_chat_enter.setForeground(new Color(34,61,75));
		bt_chat_create.setForeground(new Color(34,61,75));
		bt_exit.setForeground(new Color(34,61,75));
		
		//����
		//p_west_north.add(tableMap);
		p_west_center.add(currentChatList);
		p_west.add(p_west_north,BorderLayout.NORTH);
		p_west.add(p_west_center,BorderLayout.CENTER);
		add(p_west,BorderLayout.WEST);
		
		p_center_center.add(myChatList);
		p_center.add(p_center_center,BorderLayout.CENTER);
		
		p_center_south.add(bt_chat_enter);
		p_center_south.add(bt_chat_create);
		p_center_south.add(bt_exit);
		p_center.add(p_center_center);
		p_center.add(p_center_south,BorderLayout.SOUTH);
		add(p_center);
				
		setBackground(Color.RED);
		setPreferredSize(new Dimension(1400, 900));
		setVisible(true);
		System.out.println(TAG+" �� ���� tablenum�� "+user.getTableNum()+","+user);//user.getTableNum()���� �޾ƿ��� ����
			//��, ChatMain�� User���� ź�������� ������
	}
}
