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

public class DefaultTable3 extends JFrame{
	//TableMap tableMap;
	JPanel wrapper;
	GridBagLayout Gbag = new GridBagLayout();
	GridBagConstraints gbc;//�޸𸮿� �ø��鼭 ������
	ImageIcon icon_bill;//��꼭 ������ �̹�����
	//Image img_bill;
	JLabel la_img_bill;
	ImageIcon icon_wm;//���� ǥ�ÿ�
	//Image img_wm;
	JLabel la_img_wm;
	JLabel la_table_no;//���̺��ȣ ������
	ImageIcon icon_man;//������ ������ �̹�����
	//Image img_man;
	JLabel la_img_man;
	JLabel la_howManyWm;//���� ��� ǥ��
	JLabel la_howManyMan;//���� ��� ǥ��
	String[] table_img_path= {"won.png","femenine.png","masculine.png"};
	int table_no = 1;
	URL url;
	
	public DefaultTable3() {	
		wrapper = new JPanel();
		wrapper.setLayout(Gbag);
		
		//������� ����
		/*for(int i=0;i<table_img_path.length;i++) {
			url=this.getClass().getClassLoader().getResource(table_img_path[i]);
		}
		icon_bill = new ImageIcon("res"+table_img_path[0]);
		//example)label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		icon_bill.setImage(icon_bill.getImage().getScaledInstance(30,24,Image.SCALE_SMOOTH));
		la_img_bill = new JLabel(icon_bill, SwingConstants.CENTER);
		
		icon_wm = new ImageIcon("res"+table_img_path[1]);
		icon_wm.setImage(icon_wm.getImage().getScaledInstance(30, 24, Image.SCALE_SMOOTH));
		la_img_wm = new JLabel(icon_wm,SwingConstants.CENTER);
		
		la_table_no = new JLabel("01"); 
		//la_table_no.setText(Integer.toString(table_no));
		
		icon_man = new ImageIcon("res"+table_img_path[2]);
		icon_man.setImage(icon_man.getImage().getScaledInstance(30, 24, Image.SCALE_SMOOTH));
		la_img_man = new JLabel(icon_man,SwingConstants.CENTER);
		
		la_howManyWm = new JLabel("3");
		la_howManyMan = new JLabel("3");*/
		
		//gridbag layout ���� ����
		/*setGridBag(la_img_bill,2,0,1,1);
		setGridBag(la_img_wm,0,1,1,1);
		setGridBag(la_table_no,1,1,1,1);
		setGridBag(la_img_man,2,1,1,1);
		setGridBag(la_howManyWm,0,2,1,1);
		setGridBag(la_howManyMan,2,2,1,1);*/
				
		setSize(200,150);
		setBackground(new Color(27,188,155));
		//wrapper.setPreferredSize(new Dimension(100, 80));//�Ǵ� 96,78
		setVisible(true);
		
	}
	
	public void setGridBag(Component c,int x,int y,int width,int height) {
		gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.NONE;//���� ��ҵ� Ŀ���ٰ� �ٱ� ũ�� ���� ���ϰڴ�.
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=width;
		gbc.gridheight=height;
		Gbag.setConstraints(c, gbc);
		wrapper.add(c,gbc);
	}
	
	public static void main(String[] args) {
		new DefaultTable3();
	}
}
/*public void setGridBag(Component c,int x,int y,int width,int height) {
gbc = new GridBagConstraints();
gbc.fill=GridBagConstraints.NONE;//���� ��ҵ� Ŀ���ٰ� �ٱ� ũ�� ���� ���ϰڴ�.
gbc.gridx=x;
gbc.gridy=y;
gbc.gridwidth=width;
gbc.gridheight=height;
Gbag.setConstraints(c, gbc);//swing�� imageIcon�� awt component�� �ٲٴ� ��� ã��
this.add(c);
}*/

/*
 * icon_bill = new ImageIcon("res"+table_img_path[0]);
		img_bill = icon_bill.getImage();
		
		icon_bill.setImage(icon_bill.getImage().getScaledInstance(30,24,Image.SCALE_SMOOTH));
		icon_wm = new ImageIcon("res"+table_img_path[1]);
		icon_wm.setImage(icon_wm.getImage().getScaledInstance(30, 24, Image.SCALE_SMOOTH));
		la_table_no = new JLabel("01"); 
		la_table_no.setText(Integer.toString(table_no));
		icon_man = new ImageIcon("res"+table_img_path[2]);
		icon_man.setImage(icon_man.getImage().getScaledInstance(30, 24, Image.SCALE_SMOOTH));
		la_howManyWm = new JLabel("3");
		la_howManyMan = new JLabel("3");
 * */
