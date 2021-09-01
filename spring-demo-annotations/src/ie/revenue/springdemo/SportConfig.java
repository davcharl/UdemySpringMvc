package ie.revenue.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// @ComponentScan("ie.revenue.springdemo")
@PropertySource("classpath:sport.properties")
@PropertySource("classpath:mylogger.properties")
public class SportConfig {
	
	/*
	@Bean
	public FortuneService happyFortuneService() {
		return new HappyFortuneService();
	}
	*/
	
	@Bean MyLoggerConfig myLoggerService() {
		return new MyLoggerConfig();
	}
	
	// Define a bean for out sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	@Bean
	public FortuneService crypticFortuneService() {
		return new CrypticFortuneService();
	}
	
	// Define the bean for our swim coach and inject the dependency
	// "swimCoach" will be the bean id
	// There is no component scanning here
	@Bean
	public Coach swimCoach() {
		SwimCoach mySwimCoach = new SwimCoach(sadFortuneService());
		
		return mySwimCoach;
	}
	
	@Bean
	public Coach javelinCoach() {
		JavelinCoach myJavelinCoach = new JavelinCoach(crypticFortuneService());
		
		return myJavelinCoach;
	}

}
