package com.oktaexample.oktaoauth;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OktaoauthApplication {

	public static void main(String[] args) {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	    Locale.setDefault(Locale.US);
		SpringApplication.run(OktaoauthApplication.class, args);
		System.out.println(new Date());
	}

	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	    Locale.setDefault(Locale.US);
	}
	
	
}
