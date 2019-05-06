package com.hunter.user;

import java.util.Date;

//��Ͻ� ���� �ο���..
public class User {
   String TAG = this.getClass().getName();
   private Member member; // ���� �� ����� ����
   private Guest guest; // ���� �� �Խ�Ʈ�� ����

   private boolean flag = true; // ������ �������, �Խ�Ʈ���� �˾ƺ��� ����
   private int userId = 0; // �������̺� pk
   private int tableNum; // ���̺� ��ȣ //�����Ұ� �ƴϴϱ� string
   private int gender; // ����
   private int total; // �� �ο���
   private Date date; // ���� ��¥ //����Ʈ �ý�����Ʈ
   // ip�� ���� �߰�
   private String tableIp;

 
   public User() {
	  
      System.out.println(TAG + " User.java�� �޸𸮿� �ö󰬴�.");
      // ���� �� ����� �Խ�Ʈ �޸𸮿� �ø���
      guest = new Guest(this);
      member = new Member(this);
      setUserId(userId++);
   }

   // �������
   public Member getMemeber() {
      return member;
   }

   // �Խ�Ʈ����
   public Guest getGuest() {
      return guest;
   }

   // �÷��� �� ���
   public boolean getFlag() {
      return flag;
   }

   public void setFlag(boolean flag) {
      this.flag = flag;
   }

   // �������̺� pk���
   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   // ���̺�
   public int getTableNum() {
      return tableNum;
   }

   public void setTableNum(int tableNum) {
      this.tableNum = tableNum;
   }

   // ����
   public int getGender() {
      return gender;
   }

   public void setGender(int gender) {
      this.gender = gender;
   }

   // �� �ο�
   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   // ��¥
   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   // tableIp
   public String getTableIp() {
      return tableIp;
   }

   public void setTableIp(String tableIp) {
      this.tableIp = tableIp;
   }
}