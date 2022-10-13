package enhance.framework.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.futao.springboot.enhance.framework.api.restful.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * futaosmile@gmail.com
 * @date 2021/8/17
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class RestResultWrapper implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof RestResult) {
            return o;
        }
        if (o instanceof String || aClass.isAssignableFrom(StringHttpMessageConverter.class)) {
            return JSON.toJSONString(RestResult.simpleSuccess(o), SerializerFeature.WriteNullListAsEmpty);
        }
        return RestResult.simpleSuccess(o);
    }
}
