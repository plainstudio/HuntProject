package com.hunter.user;

import java.util.Date;

//�α��ν� ���� �ο���..
public class Bill {
	User user;
	
	private int billId = 0; // �ֹ� ��� pk
	private Date date; // �ֹ��� ������¥ sysdate

	public Bill(User user) {
		this.user=user;
		addBill();
	}
	//����� ������ ���
	public void addBill() {
		billId++;
	}

	// ������̺� pk���
	public int getBillId() {
		return billId;
	}

	// ��¥
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
