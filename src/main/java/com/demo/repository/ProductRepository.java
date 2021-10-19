package com.demo.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.dto.ProductDTO;
import com.demo.model.Product;

import reactor.core.publisher.Flux;


@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    Flux<ProductDTO> findByPriceBetween(Range<Double> priceRange);
}
