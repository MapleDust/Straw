package cn.tedu.straw.api.service;

import cn.tedu.straw.api.dto.PostQuestionDTO;
import cn.tedu.straw.commons.model.Question;
import cn.tedu.straw.commons.vo.QuestionDetailVO;
import cn.tedu.straw.commons.vo.QuestionListItemVO;
import cn.tedu.straw.commons.vo.QuestionVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 发布问题
     *
     * @param postQuestionDTO 客户端提交到服务器端的“问题”数据
     * @param userId          当前登录的用户id
     * @param userNickname    当前登录的用户昵称
     * @return 新发布的问题的id
     */
    Integer post(PostQuestionDTO postQuestionDTO, Integer userId, String userNickname);

    List<QuestionListItemVO> getHotList();
    /**
     * 查询"我的问答"的列表
     * */
    PageInfo<QuestionVO> getMyQuestions(Integer userId, Integer page);

    QuestionDetailVO getQuestionDetail(Integer id);
}
