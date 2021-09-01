package ie.revenue.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("theSillyNannies")
public class SoccerCoach implements Coach {
	

	private FortuneService fortuneService;
	
	// Constructor Injection
	@Autowired
	public SoccerCoach(@Qualifier("randomFortuneService") FortuneService myFortuneService) {
		this.fortuneService = myFortuneService;
	}
	
	@Override
	public String getDailyWorkOut() {
		return "Take ten practice penalties.";
	}
	
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
