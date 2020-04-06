package com.haili.basic.exception;

import com.haili.framework.exception.ExceptionCatch;
import com.haili.framework.model.response.CommonCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomExceptionCatch extends ExceptionCatch {
    static {
        //除了CustomException以外的异常类型及对应的错误代码在这里定义,，如果不定义则统一返回固定的错误信息
        builder.put(MethodArgumentNotValidException.class, CommonCode.UNAUTHORISE);
    }
}
