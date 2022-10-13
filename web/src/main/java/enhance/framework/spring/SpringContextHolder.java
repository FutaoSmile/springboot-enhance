package enhance.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @program:
 * @description: 获取bean对象的工具类
 * @author: 陈安
 * @create: 2022-03-31 19:01
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setAppContext(applicationContext);
    }

    private static void setAppContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 判断是否激活任意一个profile
     *
     * @param profiles
     * @return
     */
    public static boolean anyProfileActive(String... profiles) {
        Assert.notNull(applicationContext, "容器还未初始化完成，请使用MyApplicationContextInitializer类获取环境信息");
        return applicationContext.getEnvironment().acceptsProfiles(profiles);
    }

    /**
     * 判断是否激活正式环境
     *
     * @return
     */
    public static boolean isProdProfileActive() {
        Assert.notNull(applicationContext, "容器还未初始化完成，请使用MyApplicationContextInitializer类获取环境信息");
        return applicationContext.getEnvironment().acceptsProfiles("prod");
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
