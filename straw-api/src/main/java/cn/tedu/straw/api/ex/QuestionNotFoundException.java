package cn.tedu.straw.api.ex;

public class QuestionNotFoundException extends ServiceException{
    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionNotFoundException(Throwable cause) {
        super(cause);
    }

    public QuestionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
