package pl.adrian.spring6reactive.services;

import pl.adrian.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {

    Flux<BeerDTO> listBeers();
}
