package com.futao.springboot.enhance.framework.api.desensitization.processor.impl;

import com.futao.springboot.enhance.framework.api.desensitization.processor.DesensitizationProcessor;
import org.apache.commons.lang3.StringUtils;

/**
 * 邮箱脱敏
 *
 * @author futaosmile@gmail.com
 */
public class EmailProcessor implements DesensitizationProcessor {

    private static final String EMAIL_REG = "(^[\\w\u4e00-\u9fa5])[^@]*(@.*$)";
    private static final String REPLACE = "$1****$2";

    @Override
    public String desensitize(String target) {
        if (StringUtils.isBlank(target)) {
            return null;
        }
        return target.replaceAll(EMAIL_REG, REPLACE);
    }
}
