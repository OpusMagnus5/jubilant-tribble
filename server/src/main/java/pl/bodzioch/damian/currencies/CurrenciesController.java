package pl.bodzioch.damian.currencies;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
class CurrenciesController {

    private final CurrenciesService currenciesService;

    @PostMapping("/get-current-currency-value-command")
    @ResponseStatus(HttpStatus.OK)
    GetCurrentCurrencyValueResponse getCurrentCurrencyValue(@Valid @RequestBody GetCurrentCurrencyValueRequest request) {
        Double currencyRate = currenciesService.getCurrencyRate(request.name(), request.currency());
        return new GetCurrentCurrencyValueResponse(currencyRate);
    }

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    GetCurrenciesRequestsResponse getCurrenciesRequests() {
        List<CurrenciesRequestDto> allRequests = currenciesService.getAllRequests();
        return new GetCurrenciesRequestsResponse(allRequests);
    }
}
