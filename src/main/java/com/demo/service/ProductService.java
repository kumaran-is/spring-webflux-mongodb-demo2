package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.demo.utils.AppUtils;
import com.demo.dto.ProductDTO;
import com.demo.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	 @Autowired
	    private ProductRepository repository;


	    public Flux<ProductDTO> getProducts(){
	        return repository.findAll().map(AppUtils::entityToDTO);
	    }

	    public Mono<ProductDTO> getProduct(String id){
	        return repository.findById(id).map(AppUtils::entityToDTO);
	    }

	    public Flux<ProductDTO> getProductInRange(double min,double max){
	        return repository.findByPriceBetween(Range.closed(min,max));
	    }

	    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTOMono){

	      return  productDTOMono.map(AppUtils::dtoToEntity)
	                .flatMap(repository::insert)
	                .map(AppUtils::entityToDTO);
	    }

	    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDTOMono,String id){
	       return repository.findById(id)
	                .flatMap(p->productDTOMono.map(AppUtils::dtoToEntity)
	                .doOnNext(e->e.setId(id)))
	                .flatMap(repository::save)
	                .map(AppUtils::entityToDTO);

	    }

	    public Mono<Void> deleteProduct(String id){
	        return repository.deleteById(id);
	    }

}
