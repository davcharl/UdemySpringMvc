package ie.revenue.springdemo;

import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService {
	
	public String getFortune() {
		return "Today will be your lucky day!";
	}

}
