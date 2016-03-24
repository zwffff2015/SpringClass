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
	 * Spring MVC 会按请求参数名和 POJO 属性名进行自动匹 配，自动为该对象填充属性值。 支持级联属性。 如：
	 * dept.deptId、dept.address.tel 等
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
	 * 了解：
	 * 
	 * @CookieValue：映射一个Cookie值，属性同@RequestParam
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
	 * 了解： 映射请求头信息 用法同@RequestParam
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
	 * @RequestParam来映射请求参数 value：请求参数的参数名 required：该参数是否必须，默认为true
	 *                      defaultValue：请求参数的默认值
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
	 * Rest 风格的URL。 以CRUD为例： 新增：/order POST 修改：/order/1 PUT 获取：/order/1 GET
	 * 删除：/order/1 DELETE
	 * 
	 * 如何发送PUT请求和DELETE请求呢？ 1.需要配置HiddenHttpMethodFilter 2.需要发送POST请求
	 * 3.需要在发生POST请求时携带一个name="_method"的隐藏域，值为DELETE或者PUT
	 * 
	 * 在SpringMVC的目标方法中如何得到id呢？ 使用@PathVariable注解
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
	 * @PathVariable 可以映射URL中的占位符到目标方法的参数中。
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
	 * 了解：可以使用params和headers来更加精确的映射请求，params和headers支持简单的表达式。
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
	 * 常用：使用method属性来指定请求方式
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}

	/**
	 * 1. @RequestMapping除了修饰方法，还可以来修饰类 2. 1). 类定义处：提供初步的请求映射信息。相对于 WEB 应用的根目 录
	 * 2). 方法处：提供进一步的细分映射信息。相对于类定义处的 URL。若类定义处未标注 @RequestMapping， 则方法处标记的 URL
	 * 相对于WEB 应用的根目录
	 * 
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
