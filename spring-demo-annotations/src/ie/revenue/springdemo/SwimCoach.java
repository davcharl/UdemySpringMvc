package ie.revenue.springdemo;

import org.springframework.beans.factory.annotation.Value;

public class SwimCoach implements Coach {

	private FortuneService fortuneService;
	
	@Value("${foo.email}")
	private String email;
	@Value("${foo.team}")
	private String team;
	
	public String getEmail() {
		return email;
	}
	public String getTeam() {
		return team;
	}


	
	public SwimCoach(FortuneService theFortuneService) {
		this.fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkOut() {
		return "Do 50m freestyle in under 20 seconds.";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
