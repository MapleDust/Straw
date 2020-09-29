package cn.tedu.straw.api.controller;


import cn.tedu.straw.api.dto.PostAnswerDTO;
import cn.tedu.straw.api.dto.PostCommentDTO;
import cn.tedu.straw.api.ex.ParameterValidationException;
import cn.tedu.straw.api.service.ICommentService;
import cn.tedu.straw.commons.model.Comment;
import cn.tedu.straw.commons.security.LoginUserInfo;
import cn.tedu.straw.commons.vo.R;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/v1/comments")
public class CommentController {
    @Resource
    private ICommentService commentService;

    @RequestMapping("/post")
    public R<Comment> post(@Valid PostCommentDTO postCommentDTO,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal LoginUserInfo loginUserInfo) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            throw new ParameterValidationException(message);
        }
        Comment comment = commentService.post(postCommentDTO, loginUserInfo.getId(), loginUserInfo.getNickname());
        return R.ok(comment);
    }

}
