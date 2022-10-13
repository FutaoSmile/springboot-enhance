package com.futao.springboot.enhance.framework.api.desensitization.processor.impl;

import com.futao.springboot.enhance.framework.api.desensitization.processor.DesensitizationProcessor;
import org.apache.commons.lang3.StringUtils;

/**
 * 18位身份证数据脱敏处理器
 *
 * @author futaosmile@gmail.com
 * @date 2022/7/18
 * @since 2022/7/18
 */
public class IdCardProcessor implements DesensitizationProcessor {

    private static final String IC_CARD_REG = "(.{6}).*(.{4})";
    private static final String REPLACE = "$1********$2";

    @Override
    public String desensitize(String target) {
        if (StringUtils.isBlank(target)) {
            return null;
        }
        return target.replaceAll(IC_CARD_REG, REPLACE);
    }
}
