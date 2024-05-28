package pl.bodzioch.damian.currencies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class CurrenciesRepository implements ICurrenciesRepository {

    private final JpaCurrenciesRepository jpaCurrenciesRepository;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public CurrenciesRequest save(CurrenciesRequest currenciesRequest) {
        return jpaCurrenciesRepository.save(currenciesRequest);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<CurrenciesRequest> getAll() {
        return jpaCurrenciesRepository.findAll();
    }
}

@Repository
interface JpaCurrenciesRepository extends JpaRepository<CurrenciesRequest, UUID> {
}