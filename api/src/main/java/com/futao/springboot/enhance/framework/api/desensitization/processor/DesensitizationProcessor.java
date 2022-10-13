package com.futao.springboot.enhance.framework.api.desensitization.processor;

/**
 * 数据脱敏处理器接口
 *
 * @author futaosmile@gmail.com
 * @date 2022/7/18
 * @since 2022/7/18
 */
public interface DesensitizationProcessor {
    /**
     * 数据脱敏实现
     *
     * @param target 需要脱敏的数据
     * @return 脱敏后的数据
     */
    String desensitize(String target);
}
