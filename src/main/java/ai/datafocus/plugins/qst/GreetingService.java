package ai.datafocus.plugins.qst;

import javax.enterprise.context.Dependent;

@Dependent
public class GreetingService {
	public void sayHello(String name, String rabbitUrl) {
		System.out.println("Hello " + name + "!" + rabbitUrl);
	}
}
