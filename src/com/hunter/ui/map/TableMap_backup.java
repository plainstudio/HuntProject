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
		label = new JLabel("출입구");
		//p_west 
		p_west = new JPanel();
		//p_center 
		p_center = new JPanel();
		//p_east
		p_east = new JPanel();
		
		//각 중간부모 패널 레이아웃 매니저, 크기, 색상 및 위치
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
		
		//부속 컴포넌트 부착
		p_north.add(label);
		
		//부착할 DefaultPage 생성 및 부착
		for(int i=0;i<30;i++) {
			//container.add(i, new DefaultTable(this));
			this.add(container.get(i));
			System.out.println(container.get(i));
		}
		
		//중간부모들의 부착
		add(p_north,BorderLayout.NORTH);
		add(p_west,BorderLayout.WEST);
		add(p_center);
		add(p_east,BorderLayout.EAST);

		//크기, 크기변경여부, 보이기, 뜨는 위치
		setSize(700,485);		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TableMap_backup();
	}
}
