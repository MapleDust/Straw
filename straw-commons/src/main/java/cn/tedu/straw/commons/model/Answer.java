package cn.tedu.straw.commons.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@TableName("answer")
@Accessors(chain = true)
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 回答内容
     */
    @TableField("content")
    private String content;

    /**
     * 点赞数量
     */
    @TableField("count_of_likes")
    private Integer countOfLikes;

    /**
     * 回答问题的用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 回答者用户名
     */
    @TableField("user_nick_name")
    private String userNickName;

    /**
     * 对应的问题id
     */
    @TableField("question_id")
    private Integer questionId;

    /**
     * 回答时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 是否采纳
     */
    @TableField("status_of_accept")
    private Integer statusOfAccept;


}
