package pl.bodzioch.damian.currencies;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.generator.EventType;
import pl.bodzioch.damian.common.GeneratedUuidValue;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "currencies_request")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class CurrenciesRequest {

    @Id
    @GeneratedUuidValue(types = EventType.INSERT)
    private UUID id;
    private String currency;
    private String name;
    @CurrentTimestamp(event = EventType.INSERT, source = SourceType.VM)
    private LocalDateTime date;
    @Setter
    @Column(name = "exchange_rate")
    private Double exchangeRate;

    CurrenciesRequest(String currency, String name) {
        this.currency = currency;
        this.name = name;
    }
}
