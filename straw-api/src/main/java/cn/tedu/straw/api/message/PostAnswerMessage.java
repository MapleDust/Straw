package cn.tedu.straw.api.message;

import cn.tedu.straw.api.dto.PostAnswerDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PostAnswerMessage implements Serializable {
    private PostAnswerDTO postAnswerDTO;
    private Integer userId;
    private String userNickName;
}
