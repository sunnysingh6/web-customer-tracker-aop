package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger myLogger=Logger.getLogger(getClass().getName());
	//setup logger
	
	
	//setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage()
	{}
	
	//do the same thing for service package
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage()
	{}

	//do the same thing for dao package
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage()
	{}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow()
	{}
	//add before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint)
	{
		//display the method we are calling 
		myLogger.info("===> in @Before : calling method"+theJoinPoint.getSignature().toShortString());
		
		//display the arguments to the method
		
		//get the arguments
		Object args[]=theJoinPoint.getArgs();
		
		//loop thru and display arguments
		for(Object tempargs:args)
		{
			
			System.out.println("====> argument:"+ tempargs);
		}
		
	}
	
	
	//add after returning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint,Object theResult)
	{
		//display the method we are returning from
		myLogger.info("===> After Returning  method"+theJoinPoint.getSignature().toShortString());
		
		
		
		
		//display data required
		myLogger.info("=====>result"+theResult);
		
	}
	
	
	
	
	
	
	
	
}
