package cn.tedu.straw.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StrawEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawEurekaServerApplication.class, args);
    }

}
