package enhance.framework.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author futao
 * @since 2022/8/18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LongTimeExecutor {

    public static final Executor EXECUTOR = new ThreadPoolExecutor(
            4, 8, 1, TimeUnit.HOURS,
            new ArrayBlockingQueue<>(16), threadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );


    private static ThreadFactory threadFactory() {
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setDaemon(true);
        threadFactoryBuilder.setNameFormat("lt-async-%d");
        threadFactoryBuilder.setUncaughtExceptionHandler((t, e) -> log.error("线程{}，发生异常", t.getName(), e));
        return threadFactoryBuilder.build();
    }

}
