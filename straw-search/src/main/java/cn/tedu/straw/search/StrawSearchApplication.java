package cn.tedu.straw.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("cn.tedu.straw.search.mapper")
@EnableScheduling
public class StrawSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawSearchApplication.class, args);
    }

}
