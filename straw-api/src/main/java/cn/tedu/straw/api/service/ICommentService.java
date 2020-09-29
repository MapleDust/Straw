package cn.tedu.straw.api.service;

import cn.tedu.straw.api.dto.PostAnswerDTO;
import cn.tedu.straw.api.dto.PostCommentDTO;
import cn.tedu.straw.commons.model.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
public interface ICommentService extends IService<Comment> {
    /**
     * 发表评论
     *
     * @param postCommentDTO	评论数据
     * @param userId        	当前登录的用户id
     * @param userNickName  	当前登录的用户名\
     * @return 成功发表的评论数据
     */
    Comment post(PostCommentDTO postCommentDTO, Integer userId, String userNickName);
}
