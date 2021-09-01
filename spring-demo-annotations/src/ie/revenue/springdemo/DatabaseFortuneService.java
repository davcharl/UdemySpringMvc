package ie.revenue.springdemo;

import org.springframework.stereotype.Component;

@Component
public class DatabaseFortuneService implements FortuneService{

	@Override
	public String getFortune() {
		return "db random service";
	}

}