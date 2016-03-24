package com.darren.spring.hibernate.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.darren.spring.hibernate.service.BookShopService;
import com.darren.spring.hibernate.service.Cashier;

public class SpringHibernateTest {

	private ApplicationContext ctx = null;
	private BookShopService bookShopService = null;
	private Cashier cashier = null;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopService = (BookShopService) ctx.getBean("bookShopService");
		cashier = (Cashier) ctx.getBean("cashier");
	}

	@Test
	public void testCashier() {
		cashier.checkout("aa", Arrays.asList("1001", "1002"));
	}

	@Test
	public void testBookShopService() {
		bookShopService.purchase("aa", "1001");
	}

	@Test
	public void test() {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource);
	}

}
