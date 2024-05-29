package pl.bodzioch.damian.client;

import java.io.Serializable;
import java.util.List;

record NbpExchangeRatesResponse(
        List<NpbRatesData> rates
) implements Serializable {
}
