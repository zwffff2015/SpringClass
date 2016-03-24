package com.darren.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	/**
	 * 1. ʹ��@RequestMappingע����ӳ�������URL
	 * 2. ����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ������InternalResourceViewResolver��ͼ���������������µĽ�����
	 * ͨ��prefix+returnval+suffix �����ķ�ʽ�õ�ʵ�ʵ�������ͼ��Ȼ�����ת������
	 * 
	 * /WEB-INF/views/success.jsp
	 * @return
	 */
	@RequestMapping("/helloworld")
	public String hello() {
		System.out.println("hello world");
		return "success";
	}
}
