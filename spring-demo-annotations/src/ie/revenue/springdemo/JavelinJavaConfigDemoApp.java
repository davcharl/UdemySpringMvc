package ie.revenue.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavelinJavaConfigDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		Coach thisOldCoach = context.getBean("javelinCoach", Coach.class);
		
		System.out.println(thisOldCoach.getDailyWorkOut());
		System.out.println(thisOldCoach.getDailyFortune());

	}

}
