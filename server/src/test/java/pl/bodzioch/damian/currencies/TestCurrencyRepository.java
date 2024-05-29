package pl.bodzioch.damian.currencies;

import pl.bodzioch.damian.common.GeneratedUuidValue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class TestCurrencyRepository implements ICurrenciesRepository {

    ConcurrentHashMap<UUID, CurrenciesRequest> repository = new ConcurrentHashMap<>();

    @Override
    public CurrenciesRequest save(CurrenciesRequest currenciesRequest) {
        if (currenciesRequest.getId() == null) {
            setId(currenciesRequest);
        }
        repository.put(currenciesRequest.getId(), currenciesRequest);
        return repository.get(currenciesRequest.getId());
    }

    @Override
    public List<CurrenciesRequest> getAll() {
        return new ArrayList<>(repository.values());
    }

    private void setId(CurrenciesRequest currenciesRequest) {
        try {
            Field idField = Arrays.stream(currenciesRequest.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(GeneratedUuidValue.class))
                    .findFirst()
                    .orElseThrow(() -> new TestException("GeneratedUuidValue should be present in entity"));
            idField.setAccessible(true);
            idField.set(currenciesRequest, UUID.randomUUID());
        } catch (IllegalAccessException e) {
            throw new TestException(e);
        }
    }
}
