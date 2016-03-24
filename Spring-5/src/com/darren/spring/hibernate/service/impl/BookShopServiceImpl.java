package com.darren.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darren.spring.hibernate.dao.BookShopDao;
import com.darren.spring.hibernate.service.BookShopService;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;

	/**
	 * Spring hibernate���������
	 * 1. �ڷ�����ʼ֮ǰ
	 * 	a). ��ȡSession
	 *  b). ��Session�͵�ǰ�̰߳󶨣������Ϳ�����Dao��ʹ��SessionFactory��getCurrentSession��������ȡSession��
	 *  c). ��������
	 * 2. ������������������û�г����쳣����
	 *  a). �ύ����
	 *  b). ʹ�͵�ǰ�̰߳󶨵�Session�����
	 *  c). �ر�Session
	 * 
	 * 3. �����������쳣����
	 *  a). �ع�����
	 *  b). ʹ�͵�ǰ�̰߳󶨵�Session�����
	 *  c). �ر�Session
	 */
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);

		bookShopDao.updateBookStock(isbn);

		bookShopDao.updateUserAccount(username, price);
	}

}
