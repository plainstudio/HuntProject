//유닛 테이블을 표현할 클래스
package com.hunter.ui.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DefaultTable4 extends JPanel{
	TableMap3 tableMap;
	GridBagConstraints gbc;
	ImageIcon icon_bill;
	JLabel la_img_bill;
	ImageIcon icon_wm;
	JLabel la_img_wm;
	JLabel la_table_no;
	//JLabel[][] la_table_no=JLabel[][];
	//ArrayList<JLabel> la_table_no = new ArrayList();
	ImageIcon icon_man;
	JLabel la_img_man;
	JLabel la_howManyWm;//여자 몇명 표시
	JLabel la_howManyMan;//남자 몇명 표시
	String[] table_img_path= {"C:/java_developer/javaSE/HuntingProject/res/list.png","C:/java_developer/javaSE/HuntingProject/res/femenine.png","C:/java_developer/javaSE/HuntingProject/res/masculine.png"};
	
	URL url;
	/*URL url1;
	URL url2;
	URL url3;*/
	int table_no=1;
	
	
	Image test1,test2;
	ImageIcon sel;
	public DefaultTable4(TableMap3 tableMap) {
		this.tableMap = tableMap;
		la_img_bill = new JLabel();
		
		setLayout(new GridBagLayout());
		Dimension d = new Dimension(32, 26);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		/*for(int i=0;i<table_img_path.length;i++) {
			url = this.getClass().getClassLoader().getResource(table_img_path[i]);
		}*/
		
		icon_bill = new ImageIcon("res/"+table_img_path[0]);
		test1 = icon_bill.getImage();
		test2 = test1.getScaledInstance(32, 26, Image.SCALE_SMOOTH);
		sel = new ImageIcon(test2);
		
		la_img_bill.setIcon(sel);
		//System.out.println(url);
		//la_img_bill = new JLabel(icon_bill, SwingConstants.CENTER);
		//la_img_bill = new JLabel(icon_bill,SwingConstants.CENTER);
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.NORTHEAST;
		add(la_img_bill,gbc);
		
		icon_wm = new ImageIcon("res"+table_img_path[1]);
		la_img_wm = new JLabel(icon_wm,SwingConstants.CENTER);
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.WEST;
		add(la_img_wm,gbc);
		
		//pages_west[r][c] = new DefaultTable4(this);
		//pages_east[r][c] = new DefaultTable4(this);
		la_table_no = new JLabel();	
		la_table_no.setPreferredSize(d);
		
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=1;
		gbc.anchor=GridBagConstraints.CENTER;
		
		la_table_no = new JLabel(Integer.toString(tableMap.table_no),SwingConstants.CENTER);
		add(la_table_no,gbc);
		
		/*icon_man = new ImageIcon("res"+table_img_path[2]);
		la_img_man = new JLabel(icon_man,SwingConstants.CENTER);
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.gridwidth=3;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.EAST;
		add(la_img_man,gbc);
		*/
		
		la_howManyWm = new JLabel("3");
		la_howManyWm.setPreferredSize(d);
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=0.5;
		gbc.anchor=GridBagConstraints.SOUTHWEST;
		add(la_howManyWm,gbc);
		
		la_howManyMan = new JLabel("4");
		la_howManyMan.setPreferredSize(d);
		gbc.gridx=2;
		gbc.gridy=2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=0.5;
		gbc.anchor=GridBagConstraints.SOUTHEAST;
		add(la_howManyMan,gbc);
		
		setPreferredSize(new Dimension(100,80));
		//this.setBackground(new Color(27,188,155));
		this.setBackground(Color.ORANGE);
		setVisible(true);
	}
}
