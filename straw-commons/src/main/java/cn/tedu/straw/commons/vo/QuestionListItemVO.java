package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class QuestionListItemVO implements Serializable {
    private Integer id;
    private String title;
    private Integer status;
    private Integer hits;
}
