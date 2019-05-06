package com.hunter.user.order;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Product extends JPanel {
	Canvas can;
	ImageIcon icon;
	Image img;
	JLabel la_name, la_price;
	String path;
	int product_id;
	String name;
	String price;
	OrderList orderList;
	Bag bag;
	int table_no;
	public Product(OrderList orderList, Bag bag, String path, int product_id, String name, String price) {
		this.orderList = orderList;
		this.bag = bag;
		this.path = path;
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.table_no = this.bag.orderMain.table_no;
		icon = new ImageIcon(path);
		img = icon.getImage();

		can = new Canvas() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 200, 180, this);
			}
		};

		can.setPreferredSize(new Dimension(200, 180));
		la_name = new JLabel(name, SwingConstants.CENTER);
		la_price = new JLabel(price, SwingConstants.CENTER);

		/*
		 * ch.addItemListener(new ItemListener() { public void
		 * itemStateChanged(ItemEvent e) { if (ch.getState()) {
		 * orderList.cartList.add(product_id); } else {
		 * orderList.cartList.remove(product_id); }
		 * System.out.println("��ٱ��Ͽ� ����� ��ǰ�� ������ " + orderList.cartList.size()); } });
		 */
		la_name.setFont(new Font("MD����ü", Font.PLAIN, 30));
		la_price.setFont(new Font("MD����ü", Font.PLAIN, 40));
		la_name.setPreferredSize(new Dimension(200, 70)); // ���� �����������ֱ�
		la_price.setPreferredSize(new Dimension(155, 70)); // ���� �����������ֱ�
		this.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new LineBorder(Color.BLACK)));

		add(can);
		add(la_name);
		add(la_price);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, product_id+"�� ��ٱ��Ͽ� �����ðھ��?");
				if(result == JOptionPane.OK_OPTION) {
					addCart(product_id);
				}
			}		
		});
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(220, 200));
	}
		
	//��ٱ��Ͽ� ���!!
	public void addCart(int product_id) {
		int cart_id=orderList.dupulicateCheck(table_no,product_id);//�ߺ� ���� üũ
		if(cart_id!=0){
			JOptionPane.showMessageDialog(this, "������ �������׽��ϴ�!");
			orderList.updateEa(cart_id);
		}else {
			int result2=orderList.goCart(table_no,product_id);
			if(result2>0) {
				JOptionPane.showMessageDialog(this, "��ٱ��Ͽ� ��ҽ��ϴ�");
			}else {
				JOptionPane.showMessageDialog(this, "��ٱ��� ��� ����");
			}
		}		
		bag.getCartList();
		bag.getSum();
		bag.la_money.setText(Integer.toString(bag.sum));
		System.out.println(bag.getSum());
	}
}



