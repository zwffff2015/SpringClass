package com.darren.spring.tx.xml;

public interface BookShopDao {

	//������Ż�ȡ��ĵ���
	public int findBookPriceByIsbn(String isbn);

	//������Ŀ�棬ʹ��Ŷ�Ӧ�Ŀ��-1
	public void updateBookStock(String isbn);

	//�����û����ʺ���ʹusername��balance-price
	public void updateUserAccount(String username, int price);
}
