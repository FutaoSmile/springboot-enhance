package com.futao.springboot.enhance.framework.api.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * @author  futaosmile@gmail.com
 * @date 2021/9/1
 */
@Documented
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AssertEnumValidator.class})
public @interface AssertEnum {
    Class<? extends Enum> enumClazz();

    String getValueMethodName();

    String message() default "超出取值范围";

    // 以下两行为固定模板
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
