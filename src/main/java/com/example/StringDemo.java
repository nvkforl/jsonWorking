package com.example;

public class StringDemo {
	
	public static void main(String[] args) {
		String a= "A";
		String b= null;
		String c= "C";
		
		System.out.println(a+(b==null ? "":b)+c);
		
		int e = 1111111;
		int f = 1111112;
		
		System.out.println(e ==f);
	}

}
