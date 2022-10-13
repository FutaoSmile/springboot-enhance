package com.futao.springboot.enhance.framework.api.annos;

import java.lang.annotation.*;

/**
 * 需要超管权限
 *
 * @author futaosmile@gmail.com
 * @date 2022/2/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequireSuperAdmin {

}
