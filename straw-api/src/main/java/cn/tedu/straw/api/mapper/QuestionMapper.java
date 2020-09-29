package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 查询"热点问题"的列表
     */
    List<QuestionListItemVO> findHotList();

    List<QuestionVO>findMyQuestions(Integer userId);

    QuestionDetailVO findById(Integer id);
}