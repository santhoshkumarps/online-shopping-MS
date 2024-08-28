package com.javalearn.product.service;

import com.javalearn.product.dto.ProductRequestDto;
import com.javalearn.product.dto.ProductResponseDto;
import com.javalearn.product.mapper.ProductMapper;
import com.javalearn.product.model.Product;
import com.javalearn.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    public void createProduct(ProductRequestDto productRequestDto){
        Product product = productMapper.mapProductRequestDtoToProduct(productRequestDto);

        if (Objects.nonNull(product) && !productRepository.existsByproductSkuCode(product.getProductSkuCode())){
            productRepository.save(product);
            log.info(" Product {} {} is saved successfully ",product.getID(),product.getProductSkuCode());
        }

        log.error("Product {} {} is not saved already exists ",product.getID(),product.getProductSkuCode());

    }

    public List<ProductResponseDto> getProducts(){

        List<Product> products =  productRepository.findAll();

        return products.stream()
               .map(product->productMapper.mapProductToProductResponseDto(product)).toList();
               //.map(product->mapToProductResponse(product)).toList(); /*can also use this*/

    }

}
