package cn.tedu.straw.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PostQuestionDTO implements Serializable {

    @NotBlank(message = "发布问题失败！问题的标题不允许为空！")
    private String title;
    @NotBlank(message = "发布问题失败！问题的正文不允许为空！")
    private String content;
    private Integer[] tagIds;
    private Integer[] teacherIds;

}
