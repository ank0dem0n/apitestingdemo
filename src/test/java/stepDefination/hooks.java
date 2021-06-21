package stepDefination;

import io.cucumber.java.Before;

public class hooks {
	@Before("@Deleteplaceapi")
	public void beforescenario() {
		//code
		System.out.println("HOOKS EXECUTED");
	}

}
