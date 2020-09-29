package cn.tedu.straw.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PostAnswerDTO implements Serializable {

    @NotNull(message = "发表答案失败！问题的id不允许为空！")
    private Integer questionId;
    @NotBlank(message = "发表答案失败！答案不允许为空！")
    private String content;

}
