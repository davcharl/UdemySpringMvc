package ie.revenue.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		// Read Spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Get bean from container
		Coach theCoach = context.getBean("tennisCoach", TennisCoach.class);
		Coach alphaCoach = context.getBean("theSillyNannies", SoccerCoach.class);
		Coach betaCoach = context.getBean("swimCoach", SwimCoach.class);
		
		// Call methods on bean
		System.out.println(theCoach.getClass().getSimpleName() + ": " + theCoach.getDailyWorkOut());
		System.out.println(alphaCoach.getClass().getSimpleName() + ": " + alphaCoach.getDailyWorkOut());
		System.out.println(betaCoach.getClass().getSimpleName() + ": " + betaCoach.getDailyWorkOut());
		
		System.out.println( theCoach.getDailyFortune() );
		System.out.println( alphaCoach.getDailyFortune() );
		System.out.println( betaCoach.getDailyFortune() );
				
		// Close context
		context.close();

	}

}
