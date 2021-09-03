package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
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










