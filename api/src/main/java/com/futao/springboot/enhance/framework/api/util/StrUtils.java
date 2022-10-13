package com.futao.springboot.enhance.framework.api.util;

import java.util.UUID;

/**
 * @author ft
 * @date 2021/4/29
 */
public class StrUtils {
    private StrUtils() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
