package com.futao.springboot.enhance.framework.api.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.futao.springboot.enhance.framework.api.desensitization.ann.DesensitizationAnn;
import com.futao.springboot.enhance.framework.api.desensitization.processor.DesensitizationProcessor;
import com.futao.springboot.enhance.framework.api.desensitization.processor.DesensitizationProcessorFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 数据脱敏序列化器
 *
 * @author futaosmile@gmail.com
 * @date 2022/7/18
 * @since 2022/7/18
 */
@Slf4j
public class DesensitizationSerializer extends StdSerializer<String> implements ContextualSerializer {

    private transient DesensitizationProcessor desensitizationProcessor;

    protected DesensitizationSerializer() {
        // 支持的类型
        super(String.class);
    }

    /**
     * 创建上下文信息
     *
     * @param prov     Serializer provider to use for accessing config, other serializers
     * @param property Method or field that represents the property
     *                 (and is used to access value to serialize).
     *                 Should be available; but there may be cases where caller can not provide it and
     *                 null is passed instead (in which case impls usually pass 'this' serializer as is)
     * @return
     */
    @Override
    public JsonSerializer<String> createContextual(SerializerProvider prov, BeanProperty property) {
        DesensitizationAnn desensitizationAnn = property.getAnnotation(DesensitizationAnn.class);
        if (desensitizationAnn != null) {
            Class<? extends DesensitizationProcessor> value = desensitizationAnn.value();
            DesensitizationProcessor curDesensitizationProcessor = DesensitizationProcessorFactory.getDesensitizationProcessor(value);
            log.debug("cur processor is:{}", curDesensitizationProcessor);
            if (curDesensitizationProcessor == null) {
                log.error("无法获取{}对应的processor实例", value);
            } else {
                this.desensitizationProcessor = curDesensitizationProcessor;
            }
        }
        return this;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        log.debug("cur obj is:{}", this);
        if (this.desensitizationProcessor != null) {
            try {
                gen.writeObject(desensitizationProcessor.desensitize(value));
            } catch (Exception e) {
                log.error("自定义脱敏处理器数据脱敏失败", e);
                gen.writeObject(value);
            }
        } else {
            gen.writeObject(value);
        }
    }


}
