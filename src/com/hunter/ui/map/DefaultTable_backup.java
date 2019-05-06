package com.hunter.ui.map;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DefaultTable_backup<img> extends JPanel{
	TableMap tableMap;
	int row=3;
	int col=3;
	int rowcol=9;
	ArrayList<JLabel> sector = new ArrayList<JLabel>(rowcol);//9칸의 공간만 확보
	
	String[] img_path= {"list.png","femenine.png","masculine.png"};
	ImageIcon[] img_icon= new ImageIcon[img_path.length];
	Image[] img = new Image[img_path.length];
	URL url;
	
	public DefaultTable_backup(TableMap tableMap) {
		this.tableMap = tableMap;
		
		//gridlayout
		setLayout(new GridLayout(row, col, 1, 0));
		System.out.println(sector.size());
		for(int i=0;i<sector.size();i++) {
			sector.get(i).add(new JLabel()) ;
		}
		
		Dimension d = new Dimension(32, 26);
		//sector(mini panels)
		for(int i=0;i<sector.size();i++) {
			((JLabel)sector.get(i)).setPreferredSize(d);
			((JLabel)sector.get(i)).setHorizontalAlignment(SwingConstants.CENTER);
			((JLabel)sector.get(i)).setBackground(new Color(27,188,155));
			((JLabel)sector.get(i)).setForeground(new Color(255,255,155));
		}
		
		//ImageIcon
		for(int i=0;i<img_icon.length;i++) {
			//url = this.getClass().getClassLoader().getResource((i+1)+".png");
			url = this.getClass().getClassLoader().getResource(img_path[i]);
			//img_icon[i]=new ImageIcon("res/"+img_path[i]);
			img_icon[i] = new ImageIcon(url);
			img[i] = img_icon[i].getImage();
			
		}
		//System.out.println(sector.get(2));
		//sector.get(2).set(img[0]);
		sector.get(2).setIcon(img_icon[0]);
		sector.get(3).setIcon(img_icon[1]);
		//sector.get(4).setText(Integer.toString(this.tableMap.table_no));
		sector.get(5).setIcon(img_icon[2]);
		sector.get(6).setText("여자수");
		sector.get(8).setText("남자수");
		setPreferredSize(new Dimension(100,80));
		this.setBackground(Color.ORANGE);
		setVisible(true);
	}
}
