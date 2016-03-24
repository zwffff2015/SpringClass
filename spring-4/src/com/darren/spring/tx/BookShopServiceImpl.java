package com.darren.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;

	// �������ע��
	// 1. ʹ��propagationָ������Ĵ�����Ϊ������ǰ�����񷽷�������һ�����񷽷�����ʱ
	// ���ʹ������Ĭ��ȡֵΪREQUIRED����ʹ�õ��÷���������
	// REQUIRES_NEW�������Լ������񣬵��õ����񷽷������񱻹���.
	// 2. ʹ��isolationָ������ĸ��뼶����õ�ȡֵΪREAD_COMMITTED
	// 3. Ĭ�������Spring������ʽ��������е�����ʱ�쳣���лع���Ҳ����ͨ����Ӧ�����Խ������á�ͨ�������ȥĬ��ֵ���ɡ�
	// 4.
	// ʹ��readOnlyָ�������Ƿ�Ϊֻ������ʾ�������ֻ��ȡ���ݵ����������ݣ��������԰������ݿ������Ż������������һ��ֻ��ȡ���ݿ�ķ�����Ӧ����readOnly=true
	// 5. ʹ��timeoutָ��ǿ�ƻع�֮ǰ�������ռ�õ�ʱ�䡣
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, noRollbackFor = {
			UserAccountException.class }, readOnly = false, timeout = 3)
	@Override
	public void purchase(String username, String isbn) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		// 1. ��ȡ��ĵ���
		int price = bookShopDao.findBookPriceByIsbn(isbn);

		// 2. ������Ŀ��
		bookShopDao.updateBookStock(isbn);

		// 3. �����û����
		bookShopDao.updateUserAccount(username, price);
	}
}
