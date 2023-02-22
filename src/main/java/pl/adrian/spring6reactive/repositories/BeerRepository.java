package pl.adrian.spring6reactive.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pl.adrian.spring6reactive.domain.Beer;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
