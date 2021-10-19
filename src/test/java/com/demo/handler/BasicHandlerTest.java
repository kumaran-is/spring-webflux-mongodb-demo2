package com.demo.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@DirtiesContext
public class BasicHandlerTest {
	
	@Autowired
    WebTestClient webTestClient;

    @Test
    public void flux(){

        Flux<Integer> intergerFlux = webTestClient.get().uri("/api/v1/basic/functional/flux")
	        .accept(MediaType.APPLICATION_JSON)
	        .exchange()
	        .expectStatus().isOk()
	        .returnResult(Integer.class)
	        .getResponseBody();

        StepVerifier.create(intergerFlux)
	        .expectSubscription()
	        .expectNext(1)
	        .expectNext(2)
	        .expectNext(3)
	        .expectNext(4)
	        .verifyComplete();
    }
    
    @Test
    public void mono(){

        Integer expectedValue = Integer.valueOf(1);

        webTestClient.get().uri("/api/v1/basic/functional/mono")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Integer.class)
            .consumeWith((response) -> {
                assertEquals(expectedValue, response.getResponseBody());
            });

    }


}
