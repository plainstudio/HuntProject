package com.hunter.ui.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.hunter.user.chat.ChatMain;

public class TableMap5 extends JPanel{
	JPanel p_north;
	JLabel label;
	JPanel p_west;
	JPanel[] container=new JPanel[30];//UnitTable이 와서 붙을 패널(mini들 부착)
	JPanel[] mini=new JPanel[30];//
	JPanel p_center;
	JPanel p_east;
	ChatMain chatMain;
	
	public TableMap5(ChatMain chatMain) {
		this.chatMain = chatMain;
		setLayout(new BorderLayout(0,0));
		p_north = new JPanel();
		label = new JLabel("출입구");
		p_west = new JPanel();
		p_center = new JPanel();
		p_east = new JPanel();
		
		p_north.setPreferredSize(new Dimension(700, 35));
		p_north.setBackground(Color.BLACK);
		
		label.setPreferredSize(new Dimension(80, 35));
		label.setBackground(Color.ORANGE);
		label.setOpaque(true);
		label.setForeground(new Color(34, 61, 75));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		p_west.setPreferredSize(new Dimension(300, 415));
		p_west.setBackground(Color.pink);
		p_west.setLayout(new FlowLayout(WIDTH, 0, 0));
		
		p_center.setSize(new Dimension(100, 415));
		p_center.setBackground(Color.BLACK);
		
		p_east.setPreferredSize(new Dimension(300, 415));
		p_east.setBackground(Color.orange);
		p_east.setLayout(new FlowLayout(WIDTH,0,0));
		
		Dimension d = new Dimension(100, 80);
		
		for(int i=0;i<30;i++) {
			container[i] = new JPanel();
			container[i].setPreferredSize(d);
			container[i].setLayout(new FlowLayout(WIDTH, 0, 0));
			if(i<15) {
				p_west.add(container[i]);
			}else {
				p_east.add(container[i]);
			}
		}
		
		//attach semi-parents container
		p_north.add(label);
		add(p_north,BorderLayout.NORTH);		
		add(p_west, BorderLayout.WEST);
		add(p_center, BorderLayout.CENTER);
		add(p_east, BorderLayout.EAST);
		
		/////////////////////////////////////////////////////////	     
		for(int i=0;i<30;i++) {
			//mini[i] = new DefaultTable(this);
			mini[i].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
			
			container[i].add(mini[i]);
			mini[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//flagChanger();
					showOption();
				}
			});
		}
		/////////////////////////////////////////////////////////////////////
		setSize(new Dimension(700, 435));
		//setResizable(false);
		setBackground(Color.BLACK);
		setVisible(true);
	}
	
	//public void flagChanger() {
		//
	//}
	
	public void showOption() {//임시 메서드:: 수정 예정
		System.out.println("00번 테이블 눌렀쪙?");
		//JOptionPane.showMessageDialog(this, "00번 테이블에 대화를 신청하시겠습니까?");
		//Custom button text
		Object[] options = {"Yes","No, thanks","다른 옵션 보기"};
		int n = JOptionPane.showOptionDialog(
					this,"00번 테이블에 대화를 신청하시겠습니까?",
					"ㅎㅅㅎ",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[0]);
		switch(n) {
		case JOptionPane.OK_OPTION:
			System.out.println("상대방의 의사를 묻는 중입니다..");
			JOptionPane.showMessageDialog(this, "상대방의 의사를 묻는 중입니다.");
			//ppukkugi();
			break;
		case JOptionPane.NO_OPTION:
			System.out.println("취소하셨습니다.");
			JOptionPane.showMessageDialog(this, "취소하셨습니다.");
			break;
		case JOptionPane.CANCEL_OPTION:
			System.out.println("다른 옵션 보기");
			JOptionPane.showMessageDialog(this, "확인 후 다른옵션보기");
			//anotherJob();
			break;
		}
	}
}
