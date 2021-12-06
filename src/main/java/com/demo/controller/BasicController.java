package com.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping(value = "/api/v1/basic", produces = MediaType.APPLICATION_NDJSON_VALUE)
public class BasicController {
	
	   //  @GetMapping(value = "/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
	   @GetMapping("/flux")
	    public Flux<Integer> returnFlux(){

	        return Flux.just(1,2,3,4)
	             //   .delayElements(Duration.ofSeconds(1))
	                .log();

	    }
	   
	    @GetMapping(value = "/fluxstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    public Flux<Long> returnFluxStream(){

	        return Flux.interval(Duration.ofSeconds(1))
	                .log();

	    }

	    @GetMapping("/mono")
	    public Mono<Integer> returnMono(){

	        return Mono.just(1)
	                .log();

	    }

}
