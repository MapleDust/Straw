package cn.tedu.straw.gateway.mapper;

import cn.tedu.straw.commons.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void findByUsername() {
        String username = "13800138000";
        User user = mapper.findByUsername(username);
        log.debug("findByUsername, username={}, result={}", username, user);
    }

}
