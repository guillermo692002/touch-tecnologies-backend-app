package biz.touchtechnologies.backendchallanege;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendChallanegeApplication implements CommandLineRunner {

	Logger log4j = LogManager.getLogger(BackendChallanegeApplication.class);
	org.slf4j.Logger loggerSlf = LoggerFactory.getLogger(BackendChallanegeApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BackendChallanegeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
