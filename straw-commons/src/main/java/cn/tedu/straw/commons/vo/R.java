package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 响应到客户端的JSON数据的封装类
 */
@Data
@Accessors(chain = true)
public class R<T> {

    /**
     * 响应状态码
     */
    private Integer state;
    /**
     * 出错时的错误提示信息
     */
    private String message;
    /**
     * 成功时响应给客户端的数据
     */
    private T data;

    /**
     * 操作成功
     *
     * @return 状态码已经标记为“成功”的对象
     */
    public static R ok() {
        return new R().setState(State.SUCCESS);
    }

    /**
     * 操作成功，且响应数据
     *
     * @param data 需要响应到客户端的数据
     * @param <T>  响应到客户端的数据的类型
     * @return 表示操作成功且封装了响应数据的对象
     */
    public static <T> R<T> ok(T data) {
        return R.ok().setData(data);
    }

    /**
     * 操作失败
     *
     * @param failureState 操作失败的状态码，取值推荐使用#link{R.State}
     * @param e            操作失败时抛出并被捕获的异常对象
     * @return 已经封装了操作失败的状态码、错误描述信息的对象
     * @see R.State
     */
    public static R failure(Integer failureState, Throwable e) {
        return new R().setState(failureState).setMessage(e.getMessage());
    }

    /**
     * 状态码
     */
    public static interface State {
        /**
         * 成功
         */
        int SUCCESS = 2000;
        /**
         * 邀请码错误
         */
        int ERR_INVITE_CODE = 4000;
        /**
         * 手机号码冲突
         */
        int ERR_PHONE_DUPLICATE = 4001;
        /**
         * 插入数据失败
         */
        int ERR_INSERT_FAIL = 4002;
        /**
         * 请求参数验证失败
         */
        int ERR_PARAMETER_VALIDATION = 4003;
        /**
         * 班级被禁用
         */
        int ERR_CLASS_DISABLED = 4004;
        int ERR_UPLOAD_FILE_EMPTY = 4005;
        int ERR_UPLOAD_FILE_SIZE = 4006;
        int ERR_UPLOAD_FILE_TYPE = 4007;
        int ERR_UPLOAD_IO = 4008;
        int ERR_UPLOAD_FILE_STATE = 4009;
        int ERR_NOTFOUND_QUESTION=4010;
        /**
         * 未知错误
         */
        int ERR_UNKNOWN = 9000;

    }

}
