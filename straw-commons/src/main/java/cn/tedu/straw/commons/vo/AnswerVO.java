package cn.tedu.straw.commons.vo;

import cn.tedu.straw.commons.model.Comment;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class AnswerVO implements Serializable {
    private Integer id;
    private String content;
    private Integer countOfLikes;
    private Integer userId;
    private String userNickName;
    private LocalDateTime createdTime;
    private Integer statusOfAccept;
    private List<Comment> comments;
}
