package com.darren.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.darren.springmvc.entities.User;

@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	private static final String SUCCESS = "success";

	/**
	 * Spring MVC �ᰴ����������� POJO �����������Զ�ƥ �䣬�Զ�Ϊ�ö����������ֵ�� ֧�ּ������ԡ� �磺
	 * dept.deptId��dept.address.tel ��
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/testPoo")
	public String testPojo(User user) {
		System.out.println("testPojo: " + user);
		return SUCCESS;
	}

	/**
	 * �˽⣺
	 * 
	 * @CookieValue��ӳ��һ��Cookieֵ������ͬ@RequestParam
	 * 
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(value = "/testCookieValue")
	public String testCookieValue(@CookieValue(value = "JSESSIONID") String sessionId) {
		System.out.println("testCookieValue: sessionId: " + sessionId);
		return SUCCESS;
	}

	/**
	 * �˽⣺ ӳ������ͷ��Ϣ �÷�ͬ@RequestParam
	 * 
	 * @param val
	 * @return
	 */
	@RequestMapping(value = "/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value = "Accept-Language") String val) {
		System.out.println("testRequestHeader:" + val);
		return SUCCESS;
	}

	/**
	 * @RequestParam��ӳ��������� value����������Ĳ����� required���ò����Ƿ���룬Ĭ��Ϊtrue
	 *                      defaultValue�����������Ĭ��ֵ
	 * 
	 * @param un
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String un,
			@RequestParam(value = "age", required = false, defaultValue = "0") int age) {
		System.out.println("testRequestParam, username: " + un + ", age: " + age);
		return SUCCESS;
	}

	/**
	 * Rest ����URL�� ��CRUDΪ���� ������/order POST �޸ģ�/order/1 PUT ��ȡ��/order/1 GET
	 * ɾ����/order/1 DELETE
	 * 
	 * ��η���PUT�����DELETE�����أ� 1.��Ҫ����HiddenHttpMethodFilter 2.��Ҫ����POST����
	 * 3.��Ҫ�ڷ���POST����ʱЯ��һ��name="_method"��������ֵΪDELETE����PUT
	 * 
	 * ��SpringMVC��Ŀ�귽������εõ�id�أ� ʹ��@PathVariableע��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest PUT:" + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest DELETE:" + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest", method = RequestMethod.POST)
	public String testRest() {
		System.out.println("testRest POST:");
		return SUCCESS;
	}

	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("testRest GET:" + id);
		return SUCCESS;
	}

	/**
	 * @PathVariable ����ӳ��URL�е�ռλ����Ŀ�귽���Ĳ����С�
	 * @param id
	 * @return
	 */
	@RequestMapping("testPathVariable/{id}")
	public String testPathVariable(@PathVariable(value = "id") Integer id) {
		System.out.println("testPathVariable:" + id);
		return SUCCESS;
	}

	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}

	/**
	 * �˽⣺����ʹ��params��headers�����Ӿ�ȷ��ӳ������params��headers֧�ּ򵥵ı��ʽ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "testParamsAndHeaders", params = { "username", "age!=10" }, headers = {
			"Accept-Encoding:gzip, deflate, sdch" })
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}

	/**
	 * ���ã�ʹ��method������ָ������ʽ
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	/**
	 * 1. @RequestMapping�������η������������������� 2. 1). �ඨ�崦���ṩ����������ӳ����Ϣ������� WEB Ӧ�õĸ�Ŀ ¼
	 * 2). ���������ṩ��һ����ϸ��ӳ����Ϣ��������ඨ�崦�� URL�����ඨ�崦δ��ע @RequestMapping�� �򷽷�����ǵ� URL
	 * �����WEB Ӧ�õĸ�Ŀ¼
	 * 
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
