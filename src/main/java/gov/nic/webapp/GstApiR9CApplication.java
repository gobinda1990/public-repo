package gov.nic.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.Retryable;

import gov.nic.service.AuthenticationVerification;

@SpringBootApplication(scanBasePackages = { "gov.nic" })
@Retryable
public class GstApiR9CApplication {
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}

	final static Logger logger = Logger.getLogger(GstApiR9CApplication.class);

	public static void main(String[] args) {
//		SpringApplication.run(GstApiR9CApplication.class, args);
		logger.info("Enter into main method:--");
		ApplicationContext act = SpringApplication.run(GstApiR9CApplication.class, args);
		AuthenticationVerification auth = act.getBean(AuthenticationVerification.class);
		auth.gstDataConsume();
		logger.info("Exit from main method:--");
		System.exit(0);
	}

}
