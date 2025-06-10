package back.vybz.user_info_read_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserInfoReadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInfoReadServiceApplication.class, args);
	}

}
