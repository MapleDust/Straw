package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.vo.AnswerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class AnswerMapperTest {
    @Resource
    AnswerMapper mapper;
    @Test
    void a(){
        List<AnswerVO> byQuestionId = mapper.findByQuestionId(13);
        for (AnswerVO answerVO : byQuestionId) {
            log.error("answer{}",answerVO);
        }
    }
}
