package pl.bodzioch.damian.currencies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bodzioch.damian.client.INbpClient;

import java.util.List;

@Service
@RequiredArgsConstructor
class CurrenciesService {

    private final ICurrenciesRepository currenciesRepository;
    private final INbpClient nbpClient;

    Double getCurrencyRate(String name, String currency) {
        CurrenciesRequest request = saveRequest(name, currency);
        Double exchangeRate = nbpClient.getExchangeRate(currency)
                .orElseThrow(() -> new CurrencyNotFoundException("Currency " + currency + " not found!"));
        request.setExchangeRate(exchangeRate);
        currenciesRepository.save(request);
        return exchangeRate;
    }

    List<CurrenciesRequestDto> getAllRequests() {
        return currenciesRepository.getAll().stream()
                .map(CurrenciesRequestDto::new)
                .toList();
    }

    private CurrenciesRequest saveRequest(String name, String currency) {
        CurrenciesRequest request = new CurrenciesRequest(currency, name);
        return currenciesRepository.save(request);
    }
}
