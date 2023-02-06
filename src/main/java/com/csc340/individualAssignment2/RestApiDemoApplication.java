package com.csc340.individualAssignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiDemoApplication.class, args);
        RestApiController rc=new RestApiController();
        rc.getAge("dallin");
        System.exit(0);
	}

}
