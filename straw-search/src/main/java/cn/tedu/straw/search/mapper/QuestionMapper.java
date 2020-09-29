package cn.tedu.straw.search.mapper;

import cn.tedu.straw.search.vo.QuestionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {
    List<QuestionVO>findQuestion();
}
