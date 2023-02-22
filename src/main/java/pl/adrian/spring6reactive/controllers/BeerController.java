package pl.adrian.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adrian.spring6reactive.model.BeerDTO;
import pl.adrian.spring6reactive.services.BeerService;
import reactor.core.publisher.Flux;

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
}
