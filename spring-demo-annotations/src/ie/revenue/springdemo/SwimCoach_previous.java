package ie.revenue.springdemo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SwimCoach_previous implements Coach, DisposableBean {

	private FortuneService myFortune;
	@Autowired
	@Qualifier("fileFortuneService")
	private void setFortuneService(FortuneService fortuneService) {
		this.myFortune = fortuneService;
	}

	
	@Override
	public String getDailyWorkOut() {
		return "Do twenty laps breaststroke";
	}

	@Override
	public String getDailyFortune() {
		return myFortune.getFortune() ;
	}


	@Override
	public void destroy() throws Exception {
		System.out.println("SwimCoach: inside destroy method");
		
	}

}
