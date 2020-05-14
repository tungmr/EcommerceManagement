package com.tungmr.repository;

import com.tungmr.entity.ProductEntity;

import java.util.List;

public interface ProductDAO {

    List<ProductEntity> findAll();

    ProductEntity getProductById(Long productId);

    ProductEntity updateProduct(ProductEntity entity);

    ProductEntity createProduct(ProductEntity entity);

    ProductEntity checkProductExisted(String productName, String categoryName, String supplierName);


}
