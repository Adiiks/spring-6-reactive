package pl.adrian.spring6reactive.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.adrian.spring6reactive.model.BeerDTO;
import pl.adrian.spring6reactive.repositories.BeerRepositoryTest;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testListBeers() {
        webTestClient.get().uri(BeerController.BEER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody().jsonPath("$", hasSize(3));
    }

    @Test
    void testGetById() {
        webTestClient.get().uri(BeerController.BEER_PATH + "/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody(BeerDTO.class);
    }

    @Test
    void testCreateBeer() {
        webTestClient.post().uri(BeerController.BEER_PATH)
                .body(Mono.just(BeerRepositoryTest.getBeer()), BeerDTO.class)
                .header("Content-type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080" + BeerController.BEER_PATH + "/4");
    }

    @Test
    void testUpdateBeer() {
        webTestClient.put().uri(BeerController.BEER_PATH + "/1")
                .body(Mono.just(BeerRepositoryTest.getBeer()), BeerDTO.class)
                .header("Content-type", "application/json")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteBeer() {
        webTestClient.delete().uri(BeerController.BEER_PATH + "/1")
                .exchange()
                .expectStatus().isNoContent();
    }
}