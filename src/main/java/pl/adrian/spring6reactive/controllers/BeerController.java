package pl.adrian.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.adrian.spring6reactive.model.BeerDTO;
import pl.adrian.spring6reactive.services.BeerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static pl.adrian.spring6reactive.controllers.BeerController.BEER_PATH;

@RestController
@RequestMapping(BEER_PATH)
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    public static final String BEER_PATH = "/api/v2/beer";

    @GetMapping
    public Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping("/{beerId}")
    public Mono<BeerDTO> getBeerById(@PathVariable Integer beerId) {
        return beerService.getBeerById(beerId);
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> createNewBeer(@RequestBody BeerDTO beerDTO) {
        return beerService.createBeer(beerDTO)
                .map(beer -> ResponseEntity.created(
                        UriComponentsBuilder.fromHttpUrl("http://localhost:8080" + BEER_PATH + "/" +
                                beer.getId())
                                .build().toUri())
                        .build());
    }
}
