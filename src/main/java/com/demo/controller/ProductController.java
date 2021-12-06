package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.demo.dto.ProductDTO;
import com.demo.service.ProductService;

@RestController
// @RequestMapping("/api/v1/products")
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_NDJSON_VALUE)
public class ProductController {

	
	 @Autowired
	 private ProductService service;
	 
    //@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	 //@GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
	@GetMapping
	public Flux<ProductDTO> getProducts(){
	    return service.getProducts();
	}
	
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<ProductDTO>> getProduct(@PathVariable String id){
	    return service.getProduct(id)
	    	   .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
               .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/price-range")
	public Flux<ProductDTO> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max")double max){
	    return service.getProductInRange(min,max);
	}
	
	
	@PostMapping
	public Mono<ResponseEntity<ProductDTO>> saveProduct(@RequestBody Mono<ProductDTO> productDtoMono){
	    return service.saveProduct(productDtoMono)
	    		 .map((item) -> new ResponseEntity<>(item, HttpStatus.CREATED));
	}
	
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<ProductDTO>> updateProduct(@RequestBody Mono<ProductDTO> productDtoMono,@PathVariable String id){
	    return service.updateProduct(productDtoMono,id)
	    		.map((item) -> new ResponseEntity<>(item, HttpStatus.OK));
	}
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id)
        		.map((item) -> ResponseEntity.noContent().build());
    }



}
