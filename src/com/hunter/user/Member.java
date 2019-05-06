package com.hunter.user;

import java.util.Date;

//�α��ν� ���� �ο���..
public class Member {
	User user;
	
	private int memberId = 0; // ������̺� pk
	private int type = 2; // ȸ��Ÿ�� ����Ʈ�� 2��
	private String id; // ���̵�
	private String phone; // ��ȭ��ȣ
	private String pw; // ��й�ȣ
	private Date date; // ��ϳ�¥ sysdate

	public Member(User user) {
		this.user=user;
	}
	//����� ������ ���
	public void addMember() {
		memberId++;
		user.setFlag(true);
	}

	// ������̺� pk���
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	// ȸ��Ÿ��
	public int getType() {
		return type;
	}

	// ���̵�
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// ��ȭ
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// ��й�ȣ
	public String getPw() {
		return pw;
	}

	public void setPwe(String pw) {
		this.pw = pw;
	}

	// ��й�ȣ
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
