package cn.tedu.straw.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class StudentRegisterDTO implements Serializable {

    @NotBlank(message = "注册失败！邀请码不允许为空！")
    private String inviteCode;
    @NotBlank(message = "注册失败！手机号码不允许为空！")
    private String phone;
    @NotBlank(message = "注册失败！昵称不允许为空！")
    private String nickname;
    @NotBlank(message = "注册失败！密码不允许为空！")
    private String password;

    private Integer gender;
    private LocalDate dayOfBirth;
    private String selfIntroduction;

}
