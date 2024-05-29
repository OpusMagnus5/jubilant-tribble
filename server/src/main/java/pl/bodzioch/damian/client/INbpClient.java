package pl.bodzioch.damian.client;

import java.util.Optional;

public interface INbpClient {
    Optional<Double> getExchangeRate(String currency);
}
