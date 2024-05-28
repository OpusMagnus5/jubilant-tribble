package pl.bodzioch.damian.currencies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CurrenciesService {

    private final ICurrenciesRepository currenciesRepository;

    CurrencyRateDto getCurrencyRate(String name, String currency) {

    }

    List<CurrenciesRequestDto> getAllRequests() {

    }
}
