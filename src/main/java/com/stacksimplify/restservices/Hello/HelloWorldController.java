package com.stacksimplify.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	public ResourceBundleMessageSource messageSource;

	//@RequestMapping(method=RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloWorld2")
	public String helloWorld(){
		return "Hello World";
	}
	
	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Raka","Banerjee","Leeds");
	}
	
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language",required = false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
