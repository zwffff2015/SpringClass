package com.darren.spring.tx.xml;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.darren.spring.tx.xml.service.BookShopService;
import com.darren.spring.tx.xml.service.Cashier;

public class SpringTransactionTest {
	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao = null;
	private BookShopService bookShopService = null;
	private Cashier cashier = null;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext-tx-xml.xml");
		bookShopDao = (BookShopDao) ctx.getBean("bookShopDao");
		bookShopService = (BookShopService) ctx.getBean("bookShopService");
		cashier = (Cashier) ctx.getBean("cashier");
	}

	@Test
	public void testTransactionPropagation() {
		cashier.checkout("AA", Arrays.asList("1001", "1002"));
	}

	@Test
	public void testBookShopService() {
		bookShopService.purchase("AA", "1001");
	}
}
