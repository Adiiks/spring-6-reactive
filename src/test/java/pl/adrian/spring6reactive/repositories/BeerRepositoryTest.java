package pl.adrian.spring6reactive.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import pl.adrian.spring6reactive.config.DatabaseConfig;
import pl.adrian.spring6reactive.domain.Beer;

import java.math.BigDecimal;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveNewBeer() {
        Beer beerToSave = getBeer();

        beerRepository.save(beerToSave)
                .subscribe(System.out::println);
    }

    Beer getBeer() {
        return Beer.builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("1234566")
                .build();
    }
}