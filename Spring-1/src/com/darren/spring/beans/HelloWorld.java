package com.darren.spring.beans;

public class HelloWorld {

	public String name;
	
	public void setName(String name){
		System.out.println("setName: "+name);
		this.name=name;
	}
	
	public void hello(){
		System.out.println("Hello: "+name);
	}
	
	public HelloWorld(){
		System.out.println("HelloWorld's Constructor");
	}
}
