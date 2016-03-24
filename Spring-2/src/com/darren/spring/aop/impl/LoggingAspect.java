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

//�����������Ϊһ�����棺��Ҫ�Ѹ�����뵽IOC�����С�������Ϊһ������
/**
 * ����ʹ��@Orderע��ָ����������ȼ���ֵԽС�����ȼ�Խ��
 * @author DarrenZeng
 *
 */
@Order(2)
@Aspect
@Component
public class LoggingAspect {

	/**
	 * ����һ�����������������������ʽ��һ��أ��÷������ٲ���Ҫ���������Ĵ��롣
	 * ʹ��@Pointcut�������������ʽ
	 * ���������ֱ֪ͨ��ʹ�÷����������õ�ǰ���������ʽ
	 */
	@Pointcut("execution(* com.darren.spring.aop.impl.*.*(int,int))")
	public void declareJoinPointExpression() {
		
	}
	
	// �����÷�����һ��ǰ��֪ͨ����Ŀ�귽����ʼ֮ǰִ��
	/*
	 * ��com.darren.spring.aop.impl��������������ÿһ��������ʼ֮ǰִ��һ�δ���
	 */
	@Before("declareJoinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " begins with " + args);
	}

	// ����֪ͨ: ��Ŀ�귽��ִ�к������Ƿ����쳣����ִ�е�֪ͨ��
	// �ں���֪ͨ�л����ܷ���Ŀ�귽��ִ�еĽ��
	@After("declareJoinPointExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("The method " + methodName + " ends");
	}

	/**
	 * �ڷ�������������ִ�еĴ��� ����֪ͨ�ǿ��Է��ʵ������ķ���ֵ�ģ�
	 */
	@AfterReturning(value = "declareJoinPointExpression()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with " + result);
	}

	/**
	 * ��Ŀ�귽�������쳣ʱ��ִ�еĴ��롣 ���Է��ʵ��쳣�����ҿ���ָ���ڳ����ض��쳣ʱ��ִ��֪ͨ����
	 */
	@AfterThrowing(value = "declareJoinPointExpression()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs execution:" + ex);
	}

	/**
	 * ����֪ͨ��ҪЯ��ProceedingJoinPoint���͵Ĳ�����
	 * ����֪ͨ�����ڶ�̬�����ȫ���̣�ProceedingJoinPoint���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽����
	 * �һ���֪ͨ�����з���ֵ������ֵ��ΪĿ�귽���ķ���ֵ
	 */
	//@Around("execution(* com.darren.spring.aop.impl.*.*(int,int))")
	public Object aroundMethod(ProceedingJoinPoint pjd) {

		Object result = null;
		String methodName = pjd.getSignature().getName();

		// ִ��Ŀ�귽��
		try {
			// ǰ��֪ͨ
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
			result = pjd.proceed();
			// ����֪ͨ
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			// �쳣֪ͨ
			System.out.println("The method " + methodName + " occurs execution:" + e);
			throw new RuntimeException();
		}

		// ����֪ͨ
		System.out.println("The method " + methodName + " ends");
		return result;
	}
}
