package com.tungmr.service;


import com.tungmr.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product getProductById(Long productId);

    Product updateProduct(Product product);

    Product createProduct(Product product);

    Product checkProductExisted(String productName, String categoryName, String supplierName);

}
