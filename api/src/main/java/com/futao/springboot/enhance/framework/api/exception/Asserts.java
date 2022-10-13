package com.futao.springboot.enhance.framework.api.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author futaosmile@gmail.com
 * @date 2022/7/2
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Asserts {
    public static void notNull(Object o, String errorMessage) {
        if (o == null) {
            throw LogicException.le(errorMessage);
        }
    }

    public static void notNull(Object o, String errorMessage, int code) {
        if (o == null) {
            throw LogicException.le(errorMessage, code);
        }
    }

    public static void notEmpty(Object o, String errorMessage) {
        if (o == null) {
            throw LogicException.le(errorMessage);
        }
        if (o instanceof Map && ((Map<?, ?>) o).isEmpty()) {
            throw LogicException.le(errorMessage);
        }
        if (o instanceof Collection && ((Collection<?>) o).isEmpty()) {
            throw LogicException.le(errorMessage);
        }
        if (o instanceof String && (((String) o).isEmpty() || ((String) o).trim().isEmpty())) {
            throw LogicException.le(errorMessage);
        }
    }

    public static void notEmpty(Object o, String errorMessage, int code) {
        if (o == null) {
            throw LogicException.le(errorMessage, code);
        }
        if (o instanceof Map && ((Map<?, ?>) o).isEmpty()) {
            throw LogicException.le(errorMessage, code);
        }
        if (o instanceof Collection && ((Collection<?>) o).isEmpty()) {
            throw LogicException.le(errorMessage, code);
        }
        if (o instanceof String && (((String) o).isEmpty() || ((String) o).trim().isEmpty())) {
            throw LogicException.le(errorMessage, code);
        }
    }

    public static void equals(Object o1, Object o2, String message) {
        if (!Objects.equals(o1, o2)) {
            throw LogicException.le(message);
        }
    }

    public static void equals(Object o1, Object o2, String message, int code) {
        if (!Objects.equals(o1, o2)) {
            throw LogicException.le(message, code);
        }
    }

    public static void notEquals(Object o1, Object o2, String message) {
        if (Objects.equals(o1, o2)) {
            throw LogicException.le(message);
        }
    }

    public static void notEquals(Object o1, Object o2, String message, int code) {
        if (Objects.equals(o1, o2)) {
            throw LogicException.le(message, code);
        }
    }

    public static void gte(int v1, int v2, String message) {
        if (v1 < v2) {
            throw LogicException.le(message);
        }
    }

    public static void truth(boolean condition, String message) {
        if (!condition) {
            throw LogicException.le(message);
        }
    }

    public static void truth(boolean condition, String message, int code) {
        if (!condition) {
            throw LogicException.le(message, code);
        }
    }
}
