package pl.szymonchowaniec.fortunecookieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FortuneCookieServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FortuneCookieServiceApplication.class, args);
    }
}
