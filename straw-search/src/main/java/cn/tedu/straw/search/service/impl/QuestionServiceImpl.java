package cn.tedu.straw.search.service.impl;

import cn.tedu.straw.search.mapper.QuestionMapper;
import cn.tedu.straw.search.service.IQuestionService;
import cn.tedu.straw.search.vo.QuestionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Value("${project.question.query.page-size}")
    private int pageSize;

    @Override
    public PageInfo<QuestionVO> getQuestionsFromDataBase(int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<QuestionVO> question = questionMapper.findQuestion();
        return new PageInfo<>(question);
    }
}
