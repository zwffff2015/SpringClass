package com.darren.spring.tx.xml.service.impl;

import com.darren.spring.tx.xml.BookShopDao;
import com.darren.spring.tx.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService {

	private BookShopDao bookShopDao;

	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	@Override
	public void purchase(String username, String isbn) {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
		// 1. ��ȡ��ĵ���
		int price = bookShopDao.findBookPriceByIsbn(isbn);

		// 2. ������Ŀ��
		bookShopDao.updateBookStock(isbn);

		// 3. �����û����
		bookShopDao.updateUserAccount(username, price);
	}
}
