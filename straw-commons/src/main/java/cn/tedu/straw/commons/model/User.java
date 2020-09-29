package cn.tedu.straw.commons.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author tedu.cn
 * @since 2020-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField("day_of_birth")
    private LocalDate dayOfBirth;

    /**
     * 电话号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 所属班级id
     */
    @TableField("class_id")
    private Integer classId;

    /**
     * 注册时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 账号是否可用，0-否，1-是
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 账号是否锁定，0-否，1-是
     */
    @TableField("locked")
    private Integer locked;

    /**
     * 0-学生，1-老师
     */
    @TableField("type")
    private Integer type;

    /**
     * 自我介绍
     */
    @TableField("self_introduction")
    private String selfIntroduction;


}
