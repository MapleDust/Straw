package cn.tedu.straw.redis.tag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cn.tedu.straw.redis.tag.mapper")
public class StrawRedisTagApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawRedisTagApplication.class, args);
    }

    // RedisTemplate的泛型：
    // - 第1个类型是Key的类型
    // - 第2个类型是Value的类型
    // 凡是由Spring框架管理的对象，都可以声明为Spring框架调用的方法的参数
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置Key的序列化工具
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置Value的序列化工具，使用的是Jackson框架中的序列化工具
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置连接Redis服务器的连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        // 返回
        return redisTemplate;
    }

}
