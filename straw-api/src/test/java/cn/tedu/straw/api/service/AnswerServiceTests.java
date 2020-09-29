package cn.tedu.straw.api.service;

import cn.tedu.straw.api.dto.PostAnswerDTO;
import cn.tedu.straw.api.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class AnswerServiceTests {
    @Resource
    IAnswerService service;

    @Test
    void post() {
        try {
            PostAnswerDTO postAnswerDTO = new PostAnswerDTO()
                    .setQuestionId(1)
                    .setContent("测试1号问题的第1个答案");
            Integer userId = 8;
            String userNickName = "天才";
            service.post(postAnswerDTO, userId, userNickName);
            log.debug("OK !!!");
        } catch (ServiceException e) {
            log.debug("error !!!");
            log.debug("type={}", e.getClass().getName());
            log.debug("message={}", e.getMessage());
        }
    }
}
