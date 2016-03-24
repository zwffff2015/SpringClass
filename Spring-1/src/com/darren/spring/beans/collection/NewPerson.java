package com.darren.spring.beans.collection;

import java.util.Map;
import com.darren.spring.beans.Car;

public class NewPerson {

	private String name;
	private int age;
	
	private Map<String,Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Map<String, Car> getCars() {
		return cars;
	}

	public void setCars(Map<String, Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", car=" + cars + "]";
	}
	
}
