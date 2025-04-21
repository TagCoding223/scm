package com.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.services.EmailService;

@SpringBootTest
class ScmApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService emailService;

	@Test
	void sendEmailTest(){
		// This is use to test mail service without running complete project
		emailService.sendEmail(
			"codespacev.c22.3@gmail.com", 
			"Just Testing!", 
			"This email creating and sending for testing."
		);
	}

}
