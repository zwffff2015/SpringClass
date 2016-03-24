package com.darren.spring.beans.factory;

import java.util.HashMap;
import java.util.Map;

/*
 * ʵ������������ʵ�������ķ�����������Ҫ�������������ٵ��ù�����ʵ������������bean��ʵ��
 */
public class InstanceCarFactory {
	private Map<String, Car> cars=null;
	
	public InstanceCarFactory() {
		cars=new HashMap<String,Car>();
		cars.put("audi", new Car("audi",300000));
		cars.put("ford", new Car("ford",400000));
	}
	
	//ʵ����������
	public Car getCar(String name) {
		return cars.get(name);
	}
}
