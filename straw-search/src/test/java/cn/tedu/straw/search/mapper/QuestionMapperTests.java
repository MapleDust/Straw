package cn.tedu.straw.search.mapper;

import cn.tedu.straw.search.vo.QuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class QuestionMapperTests {
    @Resource
    QuestionMapper mapper;

    @Test
    void findQuestion(){
        List<QuestionVO> question = mapper.findQuestion();
        for (QuestionVO questionVO : question) {
            log.debug(">>>{}",questionVO);
        }
    }
}
