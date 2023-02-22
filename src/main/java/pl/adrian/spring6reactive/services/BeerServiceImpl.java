package pl.adrian.spring6reactive.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adrian.spring6reactive.mappers.BeerMapper;
import pl.adrian.spring6reactive.model.BeerDTO;
import pl.adrian.spring6reactive.repositories.BeerRepository;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDto);
    }
}
