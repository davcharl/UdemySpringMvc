package ie.revenue.springdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class FileFortuneService implements FortuneService {

	private String fileFQDN = "O:\\Documents\\STS_Workspaces\\Udemy_Spring\\spring-demo-annotations\\src\\fortune-data.txt";
	private List<String> theFortunes = new ArrayList<String>();
	private Random myRandom = new Random();
	
	@PostConstruct
	public void doMyStartupStuff() {
		File fortuneDataFile = new File(fileFQDN);

		try (BufferedReader br = new BufferedReader(new FileReader(fortuneDataFile))) {
			String tempLine;
			System.out.println("FileFortuneService: inside doMyStartupStuff.");
			while ((tempLine = br.readLine()) != null) {
				theFortunes.add(tempLine);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public FileFortuneService() {
		
	}

	@Override
	public String getFortune() {
		int index = myRandom.nextInt(theFortunes.size());
		return theFortunes.get(index) ;
	}

}
