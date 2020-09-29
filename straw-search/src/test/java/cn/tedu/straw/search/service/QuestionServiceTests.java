package cn.tedu.straw.search.service;

import cn.tedu.straw.search.vo.QuestionVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class QuestionServiceTests {
    @Resource
    IQuestionService service;

    @Test
    void a(){
        int pageNum=2;
        PageInfo<QuestionVO> questionsFromDataBase = service.getQuestionsFromDataBase(pageNum);
        log.debug(">>>{}",questionsFromDataBase);
    }
}
