package enhance.framework.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author futao
 * @since 2022/8/3
 */
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * 是否是正式环境
     */
    private static boolean isProd;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        MyApplicationContextInitializer.setIsProd(applicationContext.getEnvironment().acceptsProfiles("prod"));
        log.info("当前是否为正式环境：{}", MyApplicationContextInitializer.isProd());
    }

    private static void setIsProd(boolean isProd) {
        MyApplicationContextInitializer.isProd = isProd;
    }

    public static boolean isProd() {
        return MyApplicationContextInitializer.isProd;
    }
}
