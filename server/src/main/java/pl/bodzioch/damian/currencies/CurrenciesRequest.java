package pl.bodzioch.damian.currencies;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.generator.EventType;
import pl.bodzioch.damian.common.GeneratedUuidValue;
import pl.bodzioch.damian.currencies.valueobject.Currency;
import pl.bodzioch.damian.currencies.valueobject.ExchangeRate;
import pl.bodzioch.damian.currencies.valueobject.Name;
import pl.bodzioch.damian.currencies.valueobject.RequestDate;

import java.util.UUID;

@Entity(name = "currencies_request")
class CurrenciesRequest {

    @Id
    @GeneratedUuidValue(types = EventType.INSERT)
    UUID id;
    Currency currency;
    Name name;
    RequestDate date;
    @AttributeOverride(name = "rate", column = @Column(name = "exchange_rate"))
    ExchangeRate exchangeRate;
}
