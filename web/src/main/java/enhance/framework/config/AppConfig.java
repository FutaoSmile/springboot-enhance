package enhance.framework.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author futaosmile@gmail.com
 * @date 2022/7/25
 * @since 2022/7/25
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectionRequestTimeout(10000);
        requestFactory.setReadTimeout(10000);
        requestFactory.setConnectTimeout(10000);
        return new RestTemplate(requestFactory);
    }
}
