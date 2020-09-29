package cn.tedu.straw.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PostCommentDTO implements Serializable {
    @NotNull(message = "发表评论失败！答案的id不允许为空！")
    private Integer answerId;
    @NotBlank(message = "发表评论失败！评论不允许为空")
    private String content;
}
