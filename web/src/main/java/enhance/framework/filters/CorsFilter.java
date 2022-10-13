package enhance.framework.filters;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * mini小程序接口跨域过滤器
 *
 * @author futaosmile@gmail.com
 * @date 2021/10/26
 */
@Slf4j
@WebFilter(urlPatterns = "/*", description = "跨域过滤器")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("跨域filter初始化,{}", JSON.toJSONString(filterConfig));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader("origin"));
            response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "86400");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization,content-type");
            if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            filterChain.doFilter(servletRequest, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("跨域filter销毁");
    }
}
