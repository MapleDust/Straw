package cn.tedu.straw.api.schedule;

import cn.tedu.straw.api.cache.QuestionCache;
import cn.tedu.straw.api.service.IQuestionService;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class QuestionCacheSchedule {
    @Resource
    private IQuestionService questionService;

    @Scheduled(fixedRate = 1 * 60 * 1000)
    public void loadHotQuestionCache() {
        QuestionCache.getQuestions().clear();
        List<QuestionListItemVO> questions = questionService.getHotList();
        QuestionCache.setQuestions(questions);
    }
}
