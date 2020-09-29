package cn.tedu.straw.api.service;

import cn.tedu.straw.api.dto.PostQuestionDTO;
import cn.tedu.straw.api.ex.ServiceException;
import cn.tedu.straw.commons.vo.QuestionVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class QuestionServiceTests {

    @Autowired
    IQuestionService service;

    @Test
    void post() {
        try {
            PostQuestionDTO question = new PostQuestionDTO();
            question.setTitle("单元测试怎么用？");
            question.setContent("Junit4和Junit5差别大吗？");
            question.setTagIds(new Integer[]{1, 2, 3});
            question.setTeacherIds(new Integer[]{1, 3, 5});
            Integer userId = 12;
            String userNickName = "12号超人";
            Integer questionId = service.post(question, userId, userNickName);
            log.debug("post question ok, question id={}", questionId);
        } catch (ServiceException e) {
            log.debug("error, type={}", e.getClass().getName());
            log.debug("message={}", e.getMessage());
        }
    }
    @Test
    void getMyQuestions() {
        Integer userId = 10;
        int page = 1;
        PageInfo<QuestionVO> questionPageInfo = service.getMyQuestions(userId, page);
        log.debug("page info >>> {}", questionPageInfo);
    }
    @Test
    void a(){
        System.out.println(service.getQuestionDetail(2));
    }
}
