package ie.revenue.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		
		// Load Spring container as configured in the XML config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Retrieve the bean
		Coach theCoach = context.getBean("swimCoach", Coach.class);
		Coach alphaCoach = context.getBean("swimCoach", Coach.class);
		
		// Load methods from bean
		boolean result = (theCoach == alphaCoach);
		
		System.out.println("Both instances reference the same memory object: " +  result);
		System.out.println("theCoach is at memory location: " + theCoach);
		System.out.println("alphaCoach is at memory location: " + alphaCoach);
		
		System.out.println("theCoach fortune: " + theCoach.getDailyFortune() );
		System.out.println("alphaCoach fortune: " + theCoach.getDailyFortune() );
		
		// Close context
		context.close();

	}

}
