package ie.revenue.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TennisCoach implements Coach, DisposableBean {
	
	@Autowired
	@Qualifier("happyFortuneService")
	private FortuneService fortuneService;

	// Setter injection
	/*
	@Autowired
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	*/
	
	//Define our default constructor - optional
	public TennisCoach() {
		System.out.println("TennisCoach: Inside default contstructor.");
	}
	
	// Define my init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println("TennisCoach: inside doMyStartupStuff.");
	}
	
	/*
	// This is replaced by the destroy method overriding the DisposableBean
	// Define my destroy method
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println("TennisCoach: inside doMyCleanupStuff.");
	}
	*/
	
	
	@Override
	public String getDailyWorkOut() {
		return "Practice your backhand volley.";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("TennisCoach: inside the destroy method.");
		
	}

}
