package cn.tedu.straw.search.service;

import cn.tedu.straw.search.vo.QuestionVO;
import com.github.pagehelper.PageInfo;

public interface IQuestionService {
    PageInfo<QuestionVO>getQuestionsFromDataBase(int pageSize);
}
