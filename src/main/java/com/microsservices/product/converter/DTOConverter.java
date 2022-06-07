package com.microsservices.product.converter;


import com.example.shoppingclient.dto.CategoryDTO;
import com.example.shoppingclient.dto.ProductDTO;
import com.microsservices.product.model.Category;
import com.microsservices.product.model.Product;

public class DTOConverter {

    public static CategoryDTO convert(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }
    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        if(product.getCategory() != null){
            productDTO.setCategoryDTO(DTOConverter.convert(product.getCategory()));
        }
        return productDTO;
    }
}
