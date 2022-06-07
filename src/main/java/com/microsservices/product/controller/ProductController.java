package com.microsservices.product.controller;

import com.example.shoppingclient.dto.ProductDTO;
import com.example.shoppingclient.exception.ProductNotFoundException;
import com.microsservices.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<ProductDTO> getProducts(){
        return productService.getAll();
    }

    @GetMapping("/product/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId){
        return productService.getProductByCategory(categoryId);
    }

    @GetMapping("/product/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier){
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping("/product")
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO productDTO){

        return productService.save(productDTO);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Long id) throws ProductNotFoundException {
        productService.delete(id);
    }
}
