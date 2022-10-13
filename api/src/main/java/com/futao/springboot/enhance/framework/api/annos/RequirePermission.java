package com.futao.springboot.enhance.framework.api.annos;

import java.lang.annotation.*;

/**
 * @author futaosmile@gmail.com
 * @date 2022/7/19
 * @since 2022/7/19
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermission {
    /**
     * authCode
     *
     * @return 要求的权限
     */
    String value();
}
