package ie.revenue.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		
		// Read Spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		// Get bean from container
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
		// Coach alphaCoach = context.getBean("theSillyNannies", SoccerCoach.class);
		// Coach betaCoach = context.getBean("swimCoach", SwimCoach.class);
		
		// Call methods on bean
		System.out.println(theCoach.getClass().getSimpleName() + ": " + theCoach.getDailyWorkOut());
		// System.out.println(alphaCoach.getClass().getSimpleName() + ": " + alphaCoach.getDailyWorkOut());
		// System.out.println(betaCoach.getClass().getSimpleName() + ": " + betaCoach.getDailyWorkOut());
		
		System.out.println( theCoach.getDailyFortune() );
		// System.out.println( alphaCoach.getDailyFortune() );
		// System.out.println( betaCoach.getDailyFortune() );
		
		System.out.println("Email address: " + theCoach.getEmail() );
		System.out.println("Email address: " + theCoach.getTeam() );
		
		// Close context
		context.close();

	}

}
