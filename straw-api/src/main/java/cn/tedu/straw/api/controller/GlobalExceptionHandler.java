package cn.tedu.straw.api.controller;

import cn.tedu.straw.api.ex.*;
import cn.tedu.straw.commons.vo.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public R handleException(Throwable e) {
        if (e instanceof InviteCodeException) {
            return R.failure(R.State.ERR_INVITE_CODE, e);
        } else if (e instanceof PhoneDuplicateException) {
            return R.failure(R.State.ERR_PHONE_DUPLICATE, e);
        } else if (e instanceof InsertException) {
            return R.failure(R.State.ERR_INSERT_FAIL, e);
        } else if (e instanceof ParameterValidationException) {
            return R.failure(R.State.ERR_PARAMETER_VALIDATION, e);
        } else if (e instanceof ClassDisabledException) {
            return R.failure(R.State.ERR_CLASS_DISABLED, e);
        } else if (e instanceof FileEmptyException) {
            return R.failure(R.State.ERR_UPLOAD_FILE_EMPTY, e);
        } else if (e instanceof FileIOException) {
            return R.failure(R.State.ERR_UPLOAD_IO, e);
        } else if (e instanceof FileSizeException) {
            return R.failure(R.State.ERR_UPLOAD_FILE_SIZE, e);
        } else if (e instanceof FileStateException) {
            return R.failure(R.State.ERR_UPLOAD_FILE_STATE, e);
        } else if (e instanceof FileTypeException) {
            return R.failure(R.State.ERR_UPLOAD_FILE_TYPE, e);
        }else if (e instanceof QuestionNotFoundException) {
            return R.failure(R.State.ERR_NOTFOUND_QUESTION, e);
        } else {
            return R.failure(R.State.ERR_UNKNOWN, e);
        }
    }
}
