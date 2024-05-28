package pl.bodzioch.damian.currencies.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public record Name(String name) {
}
