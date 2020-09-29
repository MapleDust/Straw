package cn.tedu.straw.api;

import com.google.gson.Gson;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("cn.tedu.straw.api.mapper")
@EnableKafka//开启Kafka
@EnableScheduling//计划任务
@EnableRedisHttpSession//Session
public class StrawApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawApiApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public Gson gson(){
        return new Gson();
    }
}
