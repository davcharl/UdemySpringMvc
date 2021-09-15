package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc ) {
		// Print out the method we're advising on
		String methodLoc = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterThrowing on method: " + methodLoc);
		
		System.out.println("\n=====>>> The exeption is: " + theExc );
		 
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result)
	{

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterRetungin on method " + method);
		
		// print out the results of the method call
		System.out.println("\n\n=====>>> The result is: " + result);
		
		// add some post-processing functionality
		/*
		if(!result.isEmpty()) {
			Account temp = result.get(0);
			temp.setName("Daffy Duck");
		}
		*/
		
		// convert the account names to upper-case
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n\n=====>>> The UPPER result is: " + result);
	}
	
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		// loop through accounts and convert names to upper-case
		for(Account tempAccount:result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
	}


	@Before("com.luv2code.aopdemo.aspect.CommonAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinpoint) {		
		System.out.println("\n=====>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature)theJoinpoint.getSignature();
		System.out.println("\nMethod Signature: " + methodSig);
		/*
		Signature methodSig = theJoinpoint.getSignature();
		System.out.println( "\nsignature modifiers: " + methodSig.getModifiers() );
		System.out.println( "\nsignature toString: " + methodSig.toLongString() );
		*/
		
		// display the method arguments
		
		//// get args
		Object[] args = theJoinpoint.getArgs();
		
		//// loop thru args
		for(Object arg:args) {
			System.out.println(arg);
			
			if(arg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account)arg;
				
				System.out.println("account name: " + theAccount.getName() );
				System.out.println("account level: " + theAccount.getLevel() );
			}
		
		}
		
		
	}

}










