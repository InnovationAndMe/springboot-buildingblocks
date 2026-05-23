package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	//@RequestMapping(method=RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloWorld2")
	public String helloWorld(){
		return "Hello World";
	}
	
	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Raka","Banerjee","Leeds");
	}
}
