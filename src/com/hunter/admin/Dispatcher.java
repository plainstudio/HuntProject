package com.hunter.admin;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Dispatcher {
	JSONParser jsonParser;
	ServerMain serverMain;
	public Dispatcher(ServerMain serverMain) {
		this.serverMain = serverMain;
	}
	
	public void dispatch(String msg) {
		jsonParser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) jsonParser.parse(msg);
			String requestType = (String) obj.get("requestType");
			if(requestType.equals("order")) {
				System.out.println("서버에 주문!");
				//updateOrderList(){
				//}
			}
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
