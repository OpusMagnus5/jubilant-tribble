package pl.bodzioch.damian.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
class NbpClientConfig {

    private final List<ClientHttpRequestInterceptor> interceptors = List.of(
            logRequestInterceptor(),
            logResponseInterceptor()
    );

    private static final String BASE_PATH = "http://api.nbp.pl/api";

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                .baseUrl(BASE_PATH)
                .requestInterceptors(interceptors -> interceptors.addAll(this.interceptors))
                .defaultStatusHandler(new ClientResponseErrorHandler())
                .build();
    }

    private ClientHttpRequestInterceptor logRequestInterceptor() {
        return (request, body, execution) -> {
            log.info("{} {}", request.getMethod(), request.getURI());
            log.info("Headers: {}", request.getHeaders());
            log.info("Body: {}", new String(body, Charset.defaultCharset()));
            return execution.execute(request, body);
        };
    }

    private ClientHttpRequestInterceptor logResponseInterceptor() {
        return (request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            byte[] bodyBytes = IOUtils.toByteArray(response.getBody());
            BufferingClientHttpResponseWrapper responseWrapper = new BufferingClientHttpResponseWrapper(response, bodyBytes);
            log.info("{} {}", request.getMethod(), request.getURI());
            log.info("Status: {}", response.getStatusCode().value());
            log.info("Headers: {}", response.getHeaders());
            log.info("Body: {}", new String(bodyBytes, Charset.defaultCharset()));
            return responseWrapper;
        };
    }
}
