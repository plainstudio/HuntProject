package com.hunter.ui.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TableMap_backup extends JFrame{
	JPanel p_north;
	JLabel label;
	JPanel p_west,p_center,p_east;//p_center::hallway
	ArrayList<JPanel> container = new ArrayList<JPanel>(30);
	int table_no=1;
	
	DefaultTable4 defaultTable;
	
	public TableMap_backup() {
		
		//p_north
		p_north = new JPanel();
		label = new JLabel("���Ա�");
		//p_west 
		p_west = new JPanel();
		//p_center 
		p_center = new JPanel();
		//p_east
		p_east = new JPanel();
		
		//�� �߰��θ� �г� ���̾ƿ� �Ŵ���, ũ��, ���� �� ��ġ
		p_north.setPreferredSize(new Dimension(700, 50));
		p_north.setBackground(Color.BLACK);
		label.setPreferredSize(new Dimension(60,50));
		label.setBackground(Color.ORANGE);
		label.setOpaque(true);
		label.setForeground(new Color(34,61,75));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		p_west.setLayout(new FlowLayout(WIDTH,0,0));
		p_west.setLayout(new FlowLayout(HEIGHT,0,0));
		p_west.setPreferredSize(new Dimension(300, 400));
		p_west.setBackground(Color.pink);
		
		p_center.setPreferredSize(new Dimension(100, 400));
		p_center.setBackground(Color.BLACK);
		
		p_east.setLayout(new FlowLayout(WIDTH,0,0));
		p_east.setLayout(new FlowLayout(HEIGHT,0,0));
		p_east.setPreferredSize(new Dimension(300, 400));
		p_east.setBackground(Color.green);
		
		//�μ� ������Ʈ ����
		p_north.add(label);
		
		//������ DefaultPage ���� �� ����
		for(int i=0;i<30;i++) {
			//container.add(i, new DefaultTable(this));
			this.add(container.get(i));
			System.out.println(container.get(i));
		}
		
		//�߰��θ���� ����
		add(p_north,BorderLayout.NORTH);
		add(p_west,BorderLayout.WEST);
		add(p_center);
		add(p_east,BorderLayout.EAST);

		//ũ��, ũ�⺯�濩��, ���̱�, �ߴ� ��ġ
		setSize(700,485);		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TableMap_backup();
	}
}
