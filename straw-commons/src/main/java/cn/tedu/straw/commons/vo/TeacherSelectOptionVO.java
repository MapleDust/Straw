package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeacherSelectOptionVO {

    private Integer id;
    private String nickname;

}
