package com.darren.spring.aop.impl;

import java.util.List;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//把这个类声明为一个切面：需要把该类放入到IOC容器中、再声明为一个切面
/**
 * 可以使用@Order注解指定切面的优先级，值越小，优先级越高
 * @author DarrenZeng
 *
 */
@Order(2)
@Aspect
@Component
public class LoggingAspect {

	/**
	 * 定义一个方法，用于声明切入点表达式，一般地，该方法中再不需要添入其他的代码。
	 * 使用@Pointcut来声明切入点表达式
	 * 后面的其他通知直接使用方法名来引用当前的切入点表达式
	 */
	@Pointcut("execution(* com.darren.spring.aop.impl.*.*(int,int))")
	public void declareJoinPointExpression() {
		
	}
	
	// 声明该方法是一个前置通知：在目标方法开始之前执行
	/*
	 * 在com.darren.spring.aop.impl包下面的所有类的每一个方法开始之前执行一段代码
	 */
	@Before("declareJoinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " begins with " + args);
	}

	// 后置通知: 在目标方法执行后（无论是否发生异常），执行的通知。
	// 在后置通知中还不能访问目标方法执行的结果
	@After("declareJoinPointExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " ends");
	}

	/**
	 * 在方法正常结束后执行的代码 返回通知是可以访问到方法的返回值的！
	 */
	@AfterReturning(value = "declareJoinPointExpression()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with " + result);
	}

	/**
	 * 在目标方法出现异常时会执行的代码。 可以访问到异常对象；且可以指定在出现特定异常时再执行通知代码
	 */
	@AfterThrowing(value = "declareJoinPointExpression()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs execution:" + ex);
	}

	/**
	 * 环绕通知需要携带ProceedingJoinPoint类型的参数。
	 * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
	 * 且环绕通知必须有返回值，返回值即为目标方法的返回值
	 */
	//@Around("execution(* com.darren.spring.aop.impl.*.*(int,int))")
	public Object aroundMethod(ProceedingJoinPoint pjd) {

		Object result = null;
		String methodName = pjd.getSignature().getName();

		// 执行目标方法
		try {
			// 前置通知
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
			result = pjd.proceed();
			// 返回通知
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			// 异常通知
			System.out.println("The method " + methodName + " occurs execution:" + e);
			throw new RuntimeException();
		}

		// 后置通知
		System.out.println("The method " + methodName + " ends");
		return result;
	}
}
