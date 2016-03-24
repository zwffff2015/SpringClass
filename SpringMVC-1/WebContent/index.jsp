<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="springmvc/testPoo" method="post">
		username: <input type="text" name="username"/>
		<br/>
		password: <input type="password" name="password"/>
		<br/>
		email: <input type="text" name="email"/>
		<br/>
		age: <input type="text" name="age"/>
		<br/>
		city: <input type="text" name="address.city"/>
		<br/>
		province: <input type="text" name="address.province"/>
		<br/>
		<input type="submit" value="Submit Pojo"/>
	</form>
	<br /><br />
	
	<a href="springmvc/testCookieValue">Test CookieValue</a>
	<br /><br />
	
	<a href="springmvc/testRequestHeader">Test RequestHeader</a>
	<br /><br />
	
	<a href="springmvc/testRequestParam?username=撒旦法大赛&age=11">Test RequestParam</a>
	<br /><br />
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="Test Rest PUT"/>
	</form>
	<br /><br />
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="Test Rest DELETE"/>
	</form>
	<br /><br />
	
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="Test Rest POST"/>
	</form>
	<br /><br />
	
	<a href="springmvc/testRest/1">Test Rest GET</a>
	<br /><br />
	
	<a href="springmvc/testPathVariable/1">Test PathVariable</a>
	<br />
	<br />

	<a href="springmvc/testAntPath/msasf/abc">Test AntPath</a>
	<br />
	<br />

	<a href="springmvc/testParamsAndHeaders?username=darren&age=10">Test ParamsAndHeaders</a>
	<br />
	<br />

	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="submit"/>
	</form>

	<br />
	<br />
	<a href="springmvc/testMethod">Test Method</a>
	<br />
	<br />

	<a href="springmvc/testRequestMapping">Test RequestMapping</a>
	<br />
	<br />

	<a href="helloworld">Hello World</a>
</body>
</html>