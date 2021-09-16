package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName() );
	
	@Around("execution(* com.luv2code.aopdemo.service.TrafficFortuneService.getFortune(..))")
	public Object aroundFortuneServiceAdvice( ProceedingJoinPoint theProceedingJoinPoint ) throws Throwable{
		
		// print out method that we are advising on
		String methodLoc = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AROUND on method: " + methodLoc);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = null;
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception exc) {
			// Log the exception
			myLogger.warning(exc.getMessage());
			
			// Give the user a custom message
			// result = "Major accident but no worries; your private AOP helicopter is on the way!";
			
			// re-throw the exception
			throw exc;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// return result
		long duration = end - begin;
		myLogger.info("\n=====>Duration(ms): " + duration);
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
		String methodLoc = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @After (finally) on method: " + methodLoc);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc ) {
		// Print out the method we're advising on
		String methodLoc = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + methodLoc);
		
		myLogger.info("\n=====>>> The exeption is: " + theExc );
		 
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result)
	{

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterRetungin on method " + method);
		
		// print out the results of the method call
		myLogger.info("\n\n=====>>> The result is: " + result);
		
		// add some post-processing functionality
		/*
		if(!result.isEmpty()) {
			Account temp = result.get(0);
			temp.setName("Daffy Duck");
		}
		*/
		
		// convert the account names to upper-case
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n\n=====>>> The UPPER result is: " + result);
	}
	
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		// loop through accounts and convert names to upper-case
		for(Account tempAccount:result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
	}


	@Before("com.luv2code.aopdemo.aspect.CommonAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinpoint) {		
		myLogger.info("\n=====>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature)theJoinpoint.getSignature();
		myLogger.info("\nMethod Signature: " + methodSig);
		/*
		Signature methodSig = theJoinpoint.getSignature();
		myLogger.info( "\nsignature modifiers: " + methodSig.getModifiers() );
		myLogger.info( "\nsignature toString: " + methodSig.toLongString() );
		*/
		
		// display the method arguments
		
		//// get args
		Object[] args = theJoinpoint.getArgs();
		
		//// loop thru args
		for(Object arg:args) {
			myLogger.info(arg.toString() );
			
			if(arg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account)arg;
				
				myLogger.info("account name: " + theAccount.getName() );
				myLogger.info("account level: " + theAccount.getLevel() );
			}
		
		}
		
		
	}

}










