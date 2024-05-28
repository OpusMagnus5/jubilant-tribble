package pl.bodzioch.damian.currencies.valueobject;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public record RequestDate(LocalDateTime time) {
}
