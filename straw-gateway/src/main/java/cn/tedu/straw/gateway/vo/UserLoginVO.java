package cn.tedu.straw.gateway.vo;

import cn.tedu.straw.commons.model.Permission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserLoginVO implements Serializable {

    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String phone;
    private Integer type;
    private Integer locked;
    private Integer enabled;
    private List<Permission> permissions;

}
