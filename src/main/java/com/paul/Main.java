package com.paul;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		CheckValue checkValue = (CheckValue) applicationContext.getBean("checkValue");

		checkValue.processPropertys();
	}
}
