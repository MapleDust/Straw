package cn.tedu.straw.api.service;

import cn.tedu.straw.api.dto.PostAnswerDTO;
import cn.tedu.straw.commons.model.Answer;
import cn.tedu.straw.commons.vo.AnswerVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface IAnswerService extends IService<Answer> {
    /**
     * 发表答案
     * */
    void post(PostAnswerDTO postAnswerDTO,Integer userId,String userNickName);
    /**
     * 显示答案列表
     * */
    List<AnswerVO> getAnswerList(Integer questionId);
}
