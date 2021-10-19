package com.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import  static org.mockito.Mockito.when;


import com.demo.controller.ProductController;
import com.demo.dto.ProductDTO;
import com.demo.service.ProductService;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class SpringWebfluxMongodbDemo2ApplicationTests {

	@Autowired
    private WebTestClient webTestClient;
    @MockBean
    private ProductService service;
    
    @Test
    public void addProductTest(){
		Mono<ProductDTO> productDTOMono=Mono.just(new ProductDTO("10","iphone 3",1,10000));
		when(service.saveProduct(productDTOMono)).thenReturn(productDTOMono);

		webTestClient.post().uri("/api/v1/products")
				.body(Mono.just(productDTOMono),ProductDTO.class)
				.exchange()
				.expectStatus().isOk();//200

	}
    
    @Test
	public void getProductsTest(){
		Flux<ProductDTO> productDTOFlux=Flux.just(new ProductDTO("102","mobile",1,10000),
				new ProductDTO("103","TV",1,50000));
		when(service.getProducts()).thenReturn(productDTOFlux);

		Flux<ProductDTO> responseBody = webTestClient.get().uri("/api/v1/products")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDTO.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new ProductDTO("102","mobile",1,10000))
				.expectNext(new ProductDTO("103","TV",1,50000))
				.verifyComplete();

	}
    
    @Test
	public void getProductTest(){
		Mono<ProductDTO> productDTOMono=Mono.just(new ProductDTO("102","mobile",1,10000));
		when(service.getProduct(any())).thenReturn(productDTOMono);

		Flux<ProductDTO> responseBody = webTestClient.get().uri("/api/v1/products/102")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ProductDTO.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getName().equals("mobile"))
				.verifyComplete();
	}
    
    @Test
	public void updateProductTest(){
		Mono<ProductDTO> productDTOMono=Mono.just(new ProductDTO("102","mobile",1,10000));
		when(service.updateProduct(productDTOMono,"102")).thenReturn(productDTOMono);

		webTestClient.put().uri("/api/v1/products/102")
				.body(Mono.just(productDTOMono),ProductDTO.class)
				.exchange()
				.expectStatus().isOk();//200
	}
    
    @Test
	public void deleteProductTest(){
    	given(service.deleteProduct(any())).willReturn(Mono.empty());
		webTestClient.delete().uri("/api/v1/products/102")
				.exchange()
				.expectStatus().isOk();//200
	}

}
