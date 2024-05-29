package pl.bodzioch.damian.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
class ClientResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(@NonNull ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) throws IOException {
        throw new NbpClientException("Error response from NBP API, status: " + response.getStatusCode());
    }
}
