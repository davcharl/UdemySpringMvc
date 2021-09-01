package ie.revenue.springdemo;

public class JavelinCoach implements Coach {

	private FortuneService fortuneService;
	
	public JavelinCoach(FortuneService myFortuneService) {
		this.fortuneService = myFortuneService;
	}
	
	@Override
	public String getDailyWorkOut() {
		return "Practice run up and stop.";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
