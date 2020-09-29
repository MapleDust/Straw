package cn.tedu.straw.api.ex;

/**
 * 手机号码冲突（已经被占用）的异常
 */
public class PhoneDuplicateException extends ServiceException {

    public PhoneDuplicateException() {
    }

    public PhoneDuplicateException(String message) {
        super(message);
    }

    public PhoneDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneDuplicateException(Throwable cause) {
        super(cause);
    }

    public PhoneDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
