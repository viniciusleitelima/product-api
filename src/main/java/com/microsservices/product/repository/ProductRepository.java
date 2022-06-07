package com.microsservices.product.repository;

import com.microsservices.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Query(value = "select p.nome, p.preco, "
        + "p.productIdentifier, p.descricao "
        + "from product p "
        + "join category c on p.category.id = c.id "
        + "where c.id = :categoryId")
    public List<Product> getProductByCategory(@Param("categoryId") long categoryId);

    public Product findByProductIdentifier(String productIdentifier);
}
