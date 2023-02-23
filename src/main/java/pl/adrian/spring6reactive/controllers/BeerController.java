package pl.adrian.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
        return beerService.getBeerById(beerId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> createNewBeer(@Validated @RequestBody BeerDTO beerDTO) {
        return beerService.createBeer(beerDTO)
                .map(beer -> ResponseEntity.created(
                        UriComponentsBuilder.fromHttpUrl("http://localhost:8080" + BEER_PATH + "/" +
                                beer.getId())
                                .build().toUri())
                        .build());
    }

    @PutMapping("/{beerId}")
    public Mono<ResponseEntity<Void>> updateExistingBeer(@PathVariable Integer beerId,
                                                         @Validated @RequestBody BeerDTO beerDTO) {
        return beerService.updateBeer(beerId, beerDTO)
                .map(savedBeer -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{beerId}")
    Mono<ResponseEntity<Void>> deleteBeer(@PathVariable Integer beerId) {
        return beerService.deleteBeer(beerId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
