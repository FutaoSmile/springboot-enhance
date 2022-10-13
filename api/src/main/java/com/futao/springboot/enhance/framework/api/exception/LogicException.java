package com.futao.springboot.enhance.framework.api.exception;

import com.futao.springboot.enhance.framework.restful.ResponseCodes;
import lombok.Getter;

import java.util.function.Supplier;

/**
 * 逻辑异常
 *
 * futaosmile@gmail.com
 * @date 2021/8/17
 */
@Getter
public class LogicException extends RuntimeException {
    private final Integer code;

    private LogicException(String message, int code) {
        super(message);
        this.code = code;
    }

    public static LogicException le(String msg, int code) {
        return new LogicException(msg, code);
    }

    public static LogicException le(String msg) {
        return le(msg, ResponseCodes.LOGIC_FAIL);
    }

    public static void leThrow(String msg) {
        throw le(msg, ResponseCodes.LOGIC_FAIL);
    }

    public static Supplier<LogicException> les(String msg) {
        return () -> LogicException.le(msg);
    }

    public static Supplier<LogicException> les(String msg, int code) {
        return () -> LogicException.le(msg, code);
    }
}
