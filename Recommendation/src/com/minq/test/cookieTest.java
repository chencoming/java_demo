package com.minq.test;


public class cookieTest {
	public static void main(String[] args){
		Cookie c = new cookieTest();
		Cookie[] cookies = request.getCookies();//��������Ի�ȡһ��cookie���� 
		for(Cookie cookie : cookies){ 
		    cookie.getName();// get the cookie name 
		    cookie.getValue(); // get the cookie value 
		}
	}
	
}
