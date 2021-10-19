package com.demo.utils;

import org.springframework.beans.BeanUtils;

import com.demo.dto.ProductDTO;
import com.demo.model.Product;

public class AppUtils {


    public static ProductDTO entityToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public static Product dtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}