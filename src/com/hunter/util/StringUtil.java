/*
 * ��Ʈ�� ó�� �����Ͽ� ����� ��Ƴ��� Ŭ����
 * */
package com.hunter.util;

public class StringUtil {
//Ȯ���� �����ϱ�!!
   public static String getExt(String path) {
      //���� ������ ���� index�� ���Ѵ�
      int last=path.lastIndexOf(".");
      String ext=path.substring(last+1,path.length());
      return ext;
   }
   public static String getId(String member_phone) {
      //���� ������ ���� index�� ���Ѵ�
      int last=member_phone.lastIndexOf("-");
      String member_id=member_phone.substring(last+1,member_phone.length());
      return member_id;
   }
   ///////////////////////////////����//////////////////////////////////////////
   public static String getDate(String origin_date) {
	   String date=origin_date.substring(0,11);
	   return date;
   }
   /////////////////////////////////////////////////////////////////////////////
   //public static void main(String[] args) {
      //System.out.println(getExt("eeeeeeeeeee.jpg"));
   //}
}