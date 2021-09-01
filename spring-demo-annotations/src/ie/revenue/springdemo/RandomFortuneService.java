package ie.revenue.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService{

	private String[] fortunes = {"The journey is the reward",
								"Beware of the wolf in sheep's clothing",
								"Luck is created"};
	int length = fortunes.length;
	
	// Create a random number generator
	private Random myRandom = new Random();
	
	@Override
	public String getFortune() {
		
		int index = myRandom.nextInt(length);
		return fortunes[index];
	}

}
