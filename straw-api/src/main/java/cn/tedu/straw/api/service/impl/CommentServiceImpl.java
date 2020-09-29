package cn.tedu.straw.api.service.impl;

import cn.tedu.straw.api.dto.PostAnswerDTO;
import cn.tedu.straw.api.dto.PostCommentDTO;
import cn.tedu.straw.api.ex.InsertException;
import cn.tedu.straw.api.mapper.CommentMapper;
import cn.tedu.straw.api.service.ICommentService;
import cn.tedu.straw.commons.model.Comment;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public Comment post(PostCommentDTO postCommentDTO, Integer userId, String userNickName) {
        //创建Comment对象
        Comment comment=new Comment()
        //补全userId对象<<<参数
                .setUserId(userId)
        //补全userNickName<<<参数
                .setUserNickName(userNickName)
        //answerId<<<
                .setAnswerId(postCommentDTO.getAnswerId())
                .setContent(postCommentDTO.getContent())
                .setCreatedTime(LocalDateTime.now());
        int rows = commentMapper.insert(comment);
        if (rows!=1){
            throw new InsertException("发表评论失败！服务正忙！");
        }
        return comment;
    }
}
