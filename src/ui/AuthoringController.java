package ui;

import voogasalad_overwatch.AuthorEnvironment;

public class AuthoringController {
	
	private AuthorEnvironment env;
	
	public AuthoringController(AuthorEnvironment environment) {
		env = environment;
	}
	
	public AuthorEnvironment getEnvironment() {
		return env;
	}

}
