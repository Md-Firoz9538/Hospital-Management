package com.infy.hospitalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class HospitalManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);
	}


//	@Configuration
//	public static class MessageSourceConfig {
//		@Bean
//		public ResourceBundleMessageSource messageSource() {
//			ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//			source.setBasename("ValidateMessages");
//			source.setDefaultEncoding("UTF-8");
//			return source;
//		}
//	}


}
