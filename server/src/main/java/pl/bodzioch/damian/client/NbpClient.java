package pl.bodzioch.damian.client;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class NbpClient implements INbpClient {

    private static final String EXCHANGE_RATES_PATH = "exchangerates";
    private static final String TABLES_PATH = "tables";
    private static final String AVG_FOREIGN_CURRENCIES_EXCHANGE_RATES_PATH = "A";
    private static final String RESPONSE_FORMAT_PARAM = "format";
    private static final String RESPONSE_JSON_FORMAT_PARAM_VALUE = "json";

    private final RestClient restClient;

    @Override
    public Optional<Double> getExchangeRate(String currency) {
        if (StringUtils.isBlank(currency)) {
            throw new EmptyCurrencyException("Currency cannot be empty!");
        }
        List<NbpExchangeRatesResponse> response = restClient.get()
                .uri(uriBuilder -> uriBuilder.pathSegment(EXCHANGE_RATES_PATH)
                        .pathSegment(TABLES_PATH)
                        .pathSegment(AVG_FOREIGN_CURRENCIES_EXCHANGE_RATES_PATH)
                        .queryParam(RESPONSE_FORMAT_PARAM, RESPONSE_JSON_FORMAT_PARAM_VALUE)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return Optional.ofNullable(response)
                .filter(res -> !res.isEmpty())
                .map(List::getFirst)
                .map(NbpExchangeRatesResponse::rates)
                .orElse(Collections.emptyList())
                .stream()
                .filter(rate -> currency.equals(rate.currencyCode()))
                .findAny()
                .map(NpbRatesData::exchangeRate);
    }
}
