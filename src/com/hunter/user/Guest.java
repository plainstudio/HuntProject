package com.hunter.user;

public class Guest {
	User user;
	
	private int guestId=0; // �Խ�Ʈ���̺� pk
	private int type = 1; // ȸ��Ÿ�� //db�� ����Ʈ�� 1�� ��
	
	public Guest(User user) {
		this.user=user;
	}
	//�Խ�Ʈ�� ������ ���
	public void addGuest() {
		guestId++;
		user.setFlag(false);
	}
	// �Խ�Ʈ���̺� pk���
	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	// ȸ��Ÿ��
	public int getType() {
		return type;
	}
	
}
