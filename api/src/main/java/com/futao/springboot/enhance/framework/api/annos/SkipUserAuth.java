package com.futao.springboot.enhance.framework.api.annos;

import java.lang.annotation.*;

/**
 * 不需要登录
 *
 * @author futaosmile@gmail.com
 * @date 2021/10/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SkipUserAuth {
}
