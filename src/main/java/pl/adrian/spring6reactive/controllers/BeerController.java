package pl.adrian.spring6reactive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adrian.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;

import static pl.adrian.spring6reactive.controllers.BeerController.BEER_PATH;

@RestController
@RequestMapping(BEER_PATH)
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";

    @GetMapping
    public Flux<BeerDTO> listBeers() {
        return Flux.just(BeerDTO.builder().id(1).build(),
                BeerDTO.builder().id(2).build());
    }
}
