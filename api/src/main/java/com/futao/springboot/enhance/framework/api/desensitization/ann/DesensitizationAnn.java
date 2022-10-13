package com.futao.springboot.enhance.framework.api.desensitization.ann;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.futao.springboot.enhance.framework.api.desensitization.DesensitizationSerializer;
import com.futao.springboot.enhance.framework.api.desensitization.processor.DesensitizationProcessor;

import java.lang.annotation.*;

/**
 * 基于Jackson的数据脱敏注解
 *
 * @author futaosmile@gmail.com
 * @date 2022/7/18
 * @since 2022/7/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@JsonSerialize(using = DesensitizationSerializer.class)
@JacksonAnnotationsInside
public @interface DesensitizationAnn {
    /**
     * 数据脱敏处理器
     *
     * @return 数据脱敏处理器
     */
    Class<? extends DesensitizationProcessor> value();
}
