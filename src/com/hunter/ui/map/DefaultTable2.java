package com.hunter.ui.map;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DefaultTable2 extends JFrame{
	JPanel wrapper;
	GridBagLayout Gbag = new GridBagLayout();
	GridBagConstraints gbc;
	ImageIcon icon_bill;
	JLabel la_img_bill;
	ImageIcon icon_wm;
	JLabel la_img_wm;
	JLabel la_table_no;
	ImageIcon icon_man;
	JLabel la_img_man;
	JLabel la_howManyWm;//여자 몇명 표시
	JLabel la_howManyMan;//남자 몇명 표시
	String[] table_img_path= {"won.png","femenine.png","masculine.png"};
	int table_no = 1;
	URL url;
	
	public DefaultTable2() {	
		wrapper = new JPanel();
		wrapper.setLayout(Gbag);
		
		for(int i=0;i<table_img_path.length;i++) {
			url=this.getClass().getClassLoader().getResource(table_img_path[i]);
		}
		icon_bill = new ImageIcon("res"+table_img_path[0]);
		la_img_bill = new JLabel(icon_bill,SwingConstants.CENTER);
		icon_wm = new ImageIcon("res"+table_img_path[1]);
		la_img_wm = new JLabel(icon_wm,SwingConstants.CENTER);
		la_table_no = new JLabel("01");
		icon_man = new ImageIcon("res"+table_img_path[2]);
		la_img_man = new JLabel(icon_man,SwingConstants.CENTER);		
		la_howManyWm = new JLabel("3");
		la_howManyMan = new JLabel("4");
		
		setGridBag(la_img_bill,2,0,1,1);
		setGridBag(la_img_wm,0,1,1,1);
		setGridBag(la_table_no,1,1,1,1);
		setGridBag(la_img_man, 2, 1, 1, 1);
		setGridBag(la_howManyWm, 0, 2, 1, 1);
		setGridBag(la_howManyWm, 2, 2, 1, 1);
		add(wrapper);
		
		setSize(180,180);
		wrapper.setPreferredSize(new Dimension(180, 180));
		this.getContentPane().setBackground(new Color(27,188,155));
		
		setVisible(true);
		
	}
	
	public void setGridBag(Component c,int x,int y,int width,int height) {
		gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;//내부 요소들 커진다고 바깥 크기 변경 안하겠다.
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=width;
		gbc.gridheight=height;
		Gbag.setConstraints(c, gbc);
		wrapper.add(c,gbc);
	}
	
	public static void main(String[] args) {
		new DefaultTable2();
	}
}