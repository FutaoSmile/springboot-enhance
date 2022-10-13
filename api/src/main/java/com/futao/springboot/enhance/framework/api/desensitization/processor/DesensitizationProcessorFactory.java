package com.futao.springboot.enhance.framework.api.desensitization.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂类
 *
 * @author futaosmile@gmail.com
 * @date 2022/7/18
 * @since 2022/7/18
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DesensitizationProcessorFactory {

    private static final Map<String, DesensitizationProcessor> CACHE = new HashMap<>();

    public static DesensitizationProcessor getDesensitizationProcessor(Class<? extends DesensitizationProcessor> aClass) {
        if (aClass == null) {
            return null;
        }
        String aClassName = aClass.getName();
        DesensitizationProcessor desensitizationProcessor = CACHE.get(aClassName);
        if (desensitizationProcessor == null) {
            try {
                DesensitizationProcessor desensitizationProcessorInstance = aClass.newInstance();
                CACHE.put(aClassName, desensitizationProcessorInstance);
                return desensitizationProcessorInstance;
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("实例化脱敏处理器失败", e);
                return null;
            }
        } else {
            return desensitizationProcessor;
        }
    }
}
