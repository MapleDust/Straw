package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.vo.AnswerVO;
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
public interface AnswerMapper extends BaseMapper<Answer> {
    List<AnswerVO>findByQuestionId(Integer questionId);
}
