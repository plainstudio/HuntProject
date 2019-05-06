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

public class TableMap3 extends JFrame{
	JPanel p_north;
	JLabel label;
	JPanel p_west;
	JPanel p_center;//그냥 복도를 표현
	JPanel p_east;
	//JPanel[][] tables_west = new JPanel[rows][cols];
	ArrayList<JPanel> tables = new ArrayList<JPanel>(30);
	ArrayList<JPanel> p_west_pages = new ArrayList<JPanel>(15);
	ArrayList<JPanel> p_east_pages = new ArrayList<JPanel>(15);
	int table_no=1;
	
	DefaultTable4 defaultTable;
	
	public TableMap3() {
		//p_north관련처리
		p_north = new JPanel();
		p_north.setPreferredSize(new Dimension(700, 50));
		label = new JLabel("출입구");
		label.setPreferredSize(new Dimension(60, 50));
		label.setBackground(Color.ORANGE);
		label.setOpaque(true);
		label.setForeground(new Color(34,61,75));
		label.setHorizontalAlignment(SwingConstants.CENTER);		
		p_north.setBackground(Color.BLACK);		
		p_north.add(label);
		add(p_north,BorderLayout.NORTH);
		
		//p_west 처리
		p_west = new JPanel();
		p_west.setLayout(new FlowLayout(WIDTH,0,0));
		p_west.setLayout(new FlowLayout(HEIGHT,0,0));
		p_west.setPreferredSize(new Dimension(300, 400));
		
		//p_east 처리
		p_east = new JPanel();
		p_east.setLayout(new FlowLayout(WIDTH,0,0));
		p_east.setLayout(new FlowLayout(HEIGHT,0,0));
		p_east.setPreferredSize(new Dimension(300, 400));
	
		for(int i=0;i<tables.size();i++) {
			tables.add(i,new DefaultTable4(this));
			System.out.println(tables.get(0));
		}
		for(int i=0;i<tables.size();i++) {
			if(i<16) {
				p_west_pages.add(i,tables.get(i));
				p_west.add(p_west_pages.get(i));
				
			}else {
				p_east_pages.add(i, tables.get(i));
				p_east.add(p_east_pages.get(i));
			}
			
			p_west_pages.get(i).setPreferredSize(new Dimension(100,80));
			p_west_pages.get(i).setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.black));
			p_west_pages.get(i).setBackground(new Color(27,188,155));
			p_east_pages.get(i).setPreferredSize(new Dimension(100,80));
			p_east_pages.get(i).setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.black));
			p_east_pages.get(i).setBackground(new Color(27,188,155));
			//tables.get(i).setPreferredSize(new Dimension(100,80));
			//tables.get(i).setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.black));
			//tables.get(i).setBackground(new Color(27,188,155));
		}
		add(p_west,BorderLayout.WEST);
		add(p_east,BorderLayout.EAST);
		
		//p_center 복도 처리
		p_center = new JPanel();
		p_center.setPreferredSize(new Dimension(100,400));
		p_center.setBackground(Color.BLACK);
		add(p_center);

		//크기, 크기변경여부, 보이기, 뜨는 위치
		setSize(700,485);		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TableMap3();
	}
}
