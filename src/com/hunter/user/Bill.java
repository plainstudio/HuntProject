package com.hunter.user;

import java.util.Date;

//로그인시 값이 부여됨..
public class Bill {
	User user;
	
	private int billId = 0; // 주문 요약 pk
	private Date date; // 주문서 생성날짜 sysdate

	public Bill(User user) {
		this.user=user;
		addBill();
	}
	//멤버로 입장한 경우
	public void addBill() {
		billId++;
	}

	// 멤버테이블 pk얻기
	public int getBillId() {
		return billId;
	}

	// 날짜
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
