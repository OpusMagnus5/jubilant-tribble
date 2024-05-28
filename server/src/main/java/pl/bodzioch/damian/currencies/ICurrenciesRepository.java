package pl.bodzioch.damian.currencies;

import java.util.List;

interface ICurrenciesRepository {

    CurrenciesRequest save(CurrenciesRequest currenciesRequest);

    List<CurrenciesRequest> getAll();
}
