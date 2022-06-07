package com.microsservices.product.service;

import com.example.shoppingclient.dto.ProductDTO;
import com.example.shoppingclient.exception.CategoryNotFoundException;
import com.example.shoppingclient.exception.ProductNotFoundException;
import com.microsservices.product.converter.DTOConverter;
import com.microsservices.product.model.Product;
import com.microsservices.product.repository.CategoryRepository;
import com.microsservices.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategory(Long categoryId){
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null){
            return DTOConverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO){
        boolean existsCategory =  categoryRepository.existsById(productDTO.getCategoryDTO().getId());
        if(!existsCategory){
            throw new CategoryNotFoundException();
        }
        Product product = productRepository.save(Product.convert(productDTO));
        return DTOConverter.convert(product);
    }

    public void delete (long ProductId) throws ProductNotFoundException{
        Optional<Product> product = productRepository.findById(ProductId);
        product.ifPresent(value -> productRepository.delete(value));
        throw new ProductNotFoundException();
    }
}
