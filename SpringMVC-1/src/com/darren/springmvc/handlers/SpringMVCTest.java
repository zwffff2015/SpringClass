package com.darren.springmvc.handlers;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.darren.springmvc.entities.User;

@SessionAttributes(value = { "user" }, types = { String.class })
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	private static final String SUCCESS = "success";

	@RequestMapping("/testRedirect")
	public String testRedirect() {
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/testView")
	public String testView() {
		System.out.println("testView");
		return "helloView";
	}
	
	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver() {
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}
	/**
	 * 1. ��@ModelAttribute��ǵķ���������ÿ��Ŀ�귽��ִ��֮ǰ��SpringMVC���ã�
	 * 2. @ModelAttributeע��Ҳ����������Ŀ�귽��POJO���͵���Σ���value����ֵ�����µ����ã�
	 * 		1). SpringMVC��ʹ��value����ֵ��implicitModel�в��Ҷ�Ӧ�Ķ������������ֱ�Ӵ��뵽Ŀ�귽��������С�
	 * 		2). SpringMVC����valueΪkey��POJO���͵Ķ���Ϊvalue�����뵽request�С�
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		System.out.println("modelAttribute method");
		if (id != null) {
			// ģ������ݿ��л�ȡ����
			User user = new User(1, "Tom", "aaa", "tom@163.com", 12);
			System.out.println("�����ݿ��л�ȡһ������" + user);

			map.put("user", user);
		}
	}

	/**
	 * �������̣�
	 * 1. ִ��@ModelAttributeע�����εķ����������ݿ���ȡ�����󣬰Ѷ�����뵽��Map�С���Ϊuser
	 * 2. SpringMVC��Map��ȡ��User���󣬲��ѱ����������������User����Ķ�Ӧ���ԡ�
	 * 3. SpringMVC������������Ŀ�귽���Ĳ�����
	 * 
	 * ע�⣺��@ModelAttribute���εķ����У����뵽Mapʱ�ļ���Ҫ��Ŀ�귽��������͵ĵ�һ����ĸСд���ַ���һ�£�
	 * 
	 * SpringMVCȷ��Ŀ�귽��POJO������εĹ���
	 * 1. ȷ��һ��key��
	 * 		1). ��Ŀ�귽����POJO���͵Ĳ���û��ʹ��@ModelAttribute��Ϊ���Σ���keyΪPOJO������һ����ĸ��Сд��
	 * 		2). ��ʹ����@ModelAttribute�����Σ���keyΪ@ModelAttributeע���value����ֵ��
	 * 2. ��implicitModel�в���key��Ӧ�Ķ��������ڣ�����Ϊ��δ���
	 * 		1). ����@ModelAttribute��ǵķ�������Map�б��������key�͵�һ��ȷ����keyһ�£�����ȡ����
	 * 3. ��implicitModel�в�����key��Ӧ�Ķ������鵱ǰ��Handler�Ƿ�ʹ��@SessionAttributesע�����Σ�
	 * 	��ʹ���˸�ע�⣬��@SessionAttributesע���value����ֵ�а�����key������HttpSession������ȡkey����Ӧ��valueֵ��
	 *  ��������ֱ�Ӵ��뵽Ŀ�귽��������С������������׳��쳣��
	 * 4. ��Handlerû�б�ʶ@SessionAttributesע���@SessionAttributesע���valueֵ�в�����key�����ͨ������������POJO���͵Ĳ���������
	 *  ΪĿ�귽���Ĳ���
	 * 5. SpringMVC���Key��POJO���͵Ķ��󱣴浽implicitModel�У������ᱣ�浽request�С�
	 * 
	 * Դ������������̣�
	 * 1. ����@ModelAttributeע�����εķ�����ʵ���ϰ�ModelAttribute������Map�е����ݷ�����implicitModel�С�
	 * 2. ��������������Ŀ�������ʵ���ϸ�Ŀ�����������WebDataBinder�����Target���ԡ�
	 * 		1). ����WebDataBinder����
	 * 			a. ȷ��objectName���ԣ������˵�attrName����ֵΪ""����objectNameΪ������һ����ĸСд��
	 * 				*ע�⣺attrName����Ŀ�귽����POJO����ʹ����ModelAttribute�����Σ���attrNameֵ��ΪModelAttribute��value����ֵ
	 * 			b. ȷ��target����
	 * 				> ��implicitModel�в���attrName��Ӧ������ֵ�������ڣ���OK��
	 * 				> *�粻���ڣ�����֤��ǰHandler�Ƿ�ʹ����@SessionAttributes�������Σ���ʹ���ˣ����Դ�Session�л�ȡattrName����Ӧ������ֵ��
	 * 					��session��û�ж�Ӧ������ֵ�����׳����쳣��
	 * 				> ��Handlerû��ʹ��@SessionAttributes�������Σ���@SessionAttributes��û��ʹ��valueֵָ����key��attrName��ƥ�䣬
	 * 					��ͨ�����䴴����POJO����
	 * 		2). SpringMVC�ѱ����������������WebDataBinder�Ķ�Ӧ���ԡ�
	 * 		3). *SpringMVC���WebDataBinder��attrName��target����implicitModel,��������request������С�
	 * 		4). ��WebDataBinder��target��Ϊ�������ݸ�Ŀ�귽������Ρ�
	 * @param user
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("�޸ģ�" + user);
		return SUCCESS;
	}

	/**
	 * @SessionAttributes���˿���ͨ��������ָ����Ҫ�ŵ��Ự�е������⣨ʵ����ʹ�õ���value����ֵ����
	 * ������ͨ��ģ�����ԵĶ�������ָ����Щģ��������Ҫ�ŵ��Ự�У�ʵ����ʹ�õ���types����ֵ��
	 * 
	 * ע�⣺��ע��ֻ�ܷ���������棬���������η�����
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		User user = new User("Tom", "aaa", "tom@163.com", 15);
		map.put("user", user);
		map.put("city", "Tianjin");
		return SUCCESS;
	}

	/**
	 * Ŀ�귽���������Map���ͣ�ʵ����Ҳ������Model���ͻ�ModelMap���ͣ��Ĳ���
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("Tom", "John", "Mike"));
		return SUCCESS;
	}

	/**
	 * Ŀ�귽���ķ���ֵ������ModelAndView���͡� ���п��԰�����ͼ��ģ����Ϣ
	 * 
	 * SpringMVC���ModelAndView��model�����ݷ��뵽request������С�
	 * 
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);

		// ���ģ�����ݵ�ModelAndView�С�
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}

	/**
	 * ����ʹ��Servletԭ����API��ΪĿ�귽���Ĳ�������֧���������� HttpServletRequest HttpServletResponse
	 * HttpSession java.security.Principal Locale InputStream OutputStream
	 * Reader Writer
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/testServletAPI")
	public void testServletAPI(HttpServletRequest request, HttpServletResponse response, Writer out)
			throws IOException {
		System.out.println("testServletAPI, " + request + ", " + response);
		out.write("Hellp springmvc");
	}

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
