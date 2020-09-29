package cn.tedu.straw.api.cache;

import cn.tedu.straw.commons.vo.QuestionListItemVO;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class QuestionCache {
    private static List<QuestionListItemVO> questions = new CopyOnWriteArrayList<>();

    public static List<QuestionListItemVO> getQuestions() {
        return questions;
    }

    public static void setQuestions(List<QuestionListItemVO> questions) {
        QuestionCache.questions = questions;
    }
}
