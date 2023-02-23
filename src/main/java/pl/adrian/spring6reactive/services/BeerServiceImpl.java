package pl.adrian.spring6reactive.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adrian.spring6reactive.domain.Beer;
import pl.adrian.spring6reactive.mappers.BeerMapper;
import pl.adrian.spring6reactive.model.BeerDTO;
import pl.adrian.spring6reactive.repositories.BeerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<BeerDTO> getBeerById(Integer beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> createBeer(BeerDTO beerDTO) {
        Beer beerToSave = beerMapper.beerDtoToBeer(beerDTO);

        return beerRepository.save(beerToSave)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setUpc(beerDTO.getUpc());
                    foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    foundBeer.setPrice(beerDTO.getPrice());
                    return foundBeer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDto);
    }
}
