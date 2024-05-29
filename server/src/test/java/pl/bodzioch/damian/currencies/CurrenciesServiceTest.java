package pl.bodzioch.damian.currencies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bodzioch.damian.client.INbpClient;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrenciesServiceTest {

    private static final String VALID_NAME = "Jubilant Tribble";
    private static final String EUR_CURRENCY = "EUR";
    private static final Double CURRENCY_RATE = 4.2529;

    @Mock
    private INbpClient nbpClient;
    private final TestCurrencyRepository currencyRepository = new TestCurrencyRepository();
    private CurrenciesService currenciesService;

    @BeforeEach
    void setUp() {
        currenciesService = new CurrenciesService(currencyRepository, nbpClient);
    }

    @Test
    void Get_currency_rate_When_currency_exists_Should_return_currency() {
        when(nbpClient.getExchangeRate(anyString())).thenReturn(Optional.of(CURRENCY_RATE));
        Double currencyRate = currenciesService.getCurrencyRate(VALID_NAME, EUR_CURRENCY);
        assertEquals(CURRENCY_RATE, currencyRate);
    }

    @Test
    void Get_currency_rate_When_currency_not_exists_Should_throw_exception() {
        when(nbpClient.getExchangeRate(anyString())).thenReturn(Optional.empty());
        assertThrows(CurrencyNotFoundException.class, () -> currenciesService.getCurrencyRate(VALID_NAME, EUR_CURRENCY));
    }

    @Test
    void Get_currency_rate_When_currency_returned_Should_exists_response_entry() {
        when(nbpClient.getExchangeRate(anyString())).thenReturn(Optional.of(CURRENCY_RATE));
        Double currencyRate = currenciesService.getCurrencyRate(VALID_NAME, EUR_CURRENCY);
        assertEquals(CURRENCY_RATE, currencyRate);

        List<CurrenciesRequest> requests = currencyRepository.getAll();
        assertEquals(1, requests.size());

        CurrenciesRequest request = requests.getFirst();
        assertEquals(CURRENCY_RATE, request.getExchangeRate());
        assertEquals(EUR_CURRENCY, request.getCurrency());
        assertEquals(VALID_NAME, request.getName());
    }

    @Test
    void Get_currency_rate_When_currency_not_exists_Should_exists_response_entry() {
        when(nbpClient.getExchangeRate(anyString())).thenReturn(Optional.empty());
        assertThrows(CurrencyNotFoundException.class, () -> currenciesService.getCurrencyRate(VALID_NAME, EUR_CURRENCY));

        List<CurrenciesRequest> requests = currencyRepository.getAll();
        assertEquals(1, requests.size());

        CurrenciesRequest request = requests.getFirst();
        assertNull(request.getExchangeRate());
        assertEquals(EUR_CURRENCY, request.getCurrency());
        assertEquals(VALID_NAME, request.getName());
    }
}
