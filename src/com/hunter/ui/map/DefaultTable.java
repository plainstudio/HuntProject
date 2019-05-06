package com.hunter.ui.map;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.hunter.user.User;

public class DefaultTable extends JPanel{
	String TAG=this.getClass().getName();//����� ����ǥ
	//������ Ŭ����
	TableMap tableMap;
	//���
	JLabel[] labels = new JLabel[9];//space
	String[] img_path= {"list.png","femenine.png","masculine.png"};
	ImageIcon[] img_icon= new ImageIcon[img_path.length];
	URL url;
	Dimension d = new Dimension(30, 24);
	
	public DefaultTable(TableMap tableMap) {
		System.out.println(TAG+"defaultTable�� �޸𸮿� �ö�");//����� ����ǥ
		this.tableMap = tableMap;
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setLayout(new GridLayout(3, 3, 0, 0));
		
		setForeground(Color.WHITE);
		setMinimumSize(new Dimension(100, 83));
		setMaximumSize(new Dimension(100, 83));
		setPreferredSize(new Dimension(100, 83));
			
		for(int i=0;i<img_icon.length;i++) {
			url = this.getClass().getClassLoader().getResource(img_path[i]);
			img_icon[i] = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(18, 18,Image.SCALE_DEFAULT));
		}
		
		int label_no=0;
		for(JLabel label:labels) {
			labels[label_no++] = new JLabel();
		}
		//showMark(this.user.getGender(),this.tableMap.tableNO,this.user.getTotal());
		showMark(this.tableMap.user.getGender(),this.tableMap.tableNO,this.tableMap.user.getTotal());
		System.out.println(TAG+" // ���� ���̺� ������ �����ΰ�? "+this.tableMap.user.getGender());
		for(int i=0;i<labels.length;i++) {
			labels[i].setPreferredSize(d);
			labels[i].setFont(new Font("����", Font.BOLD, 13));
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setHorizontalTextPosition(SwingConstants.CENTER);
			labels[i].setLayout(new FlowLayout(WIDTH,0,0));
			labels[i].setForeground(new Color(34, 61, 75));
			add(labels[i]);
		}
		setVisible(true);
	}
	
	public void showMark(int gender,int number,int howmany) {
		labels[0].setText("");//����
		labels[1].setText("");//����
		labels[2].setIcon(img_icon[0]);//bill ������
		labels[4].setText(Integer.toString(number));//���̺��ȣ
		labels[7].setText("");//����
		System.out.println(TAG+" showMark�޼��� �μ��� �Ѿ�� gender�� "+gender);
		switch(gender) {
			case 1:
				System.out.println(TAG+" "+this.tableMap.tableNO+" �������̺��̴�!!");
				setBackground(new Color(11, 115, 244));
				labels[3].setText("");
				labels[5].setIcon(img_icon[2]);//���� ������
				labels[6].setText("0");
				labels[8].setText(Integer.toString(howmany));
				break;
			case 2:
				System.out.println(TAG+" "+this.tableMap.tableNO+" �������̺��̴�!!");
				setBackground(new Color(253,155,154));
				labels[3].setIcon(img_icon[1]);
				labels[5].setText("");
				labels[6].setText(Integer.toString(howmany));
				labels[8].setText("0");
				break;
			case 3:
				System.out.println(TAG+" "+this.tableMap.tableNO+" ȥ�����̺��̴�.!!");
				setBackground(new Color(167, 165, 168));
				labels[3].setIcon(img_icon[1]);
				labels[5].setIcon(img_icon[2]);
				labels[6].setText("");
				labels[7].setText(Integer.toString(howmany));
				labels[8].setText("");
				break;
			default:
				System.out.println(TAG+" "+this.tableMap.tableNO+" �� ���̺��̴�.");
				setBackground(new Color(102, 205, 170));
				labels[3].setText("");
				labels[5].setText("");
				labels[6].setText("");
				labels[8].setText("");
				break;
		}
		/*
		labels[3]//���� ������
		labels[5]//���� ������
		labels[6]//���ڼ�
		labels[8]//���ڼ�
		 */	
	}

}
