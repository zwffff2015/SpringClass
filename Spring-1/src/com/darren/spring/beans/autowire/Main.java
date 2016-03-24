package com.darren.spring.beans.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args){
		
		ApplicationContext ctx= new ClassPathXmlApplicationContext("beans-autowire.xml");
		Person person=(Person)ctx.getBean("person1");
		System.out.println(person);
		
		}
}
