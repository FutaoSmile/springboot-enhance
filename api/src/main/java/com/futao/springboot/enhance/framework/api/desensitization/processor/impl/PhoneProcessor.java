package com.futao.springboot.enhance.framework.api.desensitization.processor.impl;

import com.futao.springboot.enhance.framework.api.desensitization.processor.DesensitizationProcessor;
import org.apache.commons.lang3.StringUtils;

/**
 * 11位手机号脱敏
 *
 * @author futaosmile@gmail.com
 * @date 2022/7/18
 * @since 2022/7/18
 */
public class PhoneProcessor implements DesensitizationProcessor {

    private static final String IC_CARD_REG = "(.{3}).*(.{4})";
    private static final String REPLACE = "$1****$2";

    @Override
    public String desensitize(String target) {
        if (StringUtils.isBlank(target)) {
            return null;
        }
        return target.replaceAll(IC_CARD_REG, REPLACE);
    }
}
