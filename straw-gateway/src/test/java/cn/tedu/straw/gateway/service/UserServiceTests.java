package cn.tedu.straw.gateway.service;

import cn.tedu.straw.gateway.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTests {

    @Autowired
    IUserService service;

    @Test
    void getUserLoginDetails() {
        String username = "13800138001";
        UserLoginVO userLoginVO = service.getUserLoginDetails(username);
        log.debug("getUserLoginDetails >>> {}", userLoginVO);
    }

}
