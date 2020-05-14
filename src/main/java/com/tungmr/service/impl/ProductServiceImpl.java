package com.tungmr.service.impl;

import com.tungmr.entity.ProductEntity;
import com.tungmr.model.Product;
import com.tungmr.repository.ProductDAO;
import com.tungmr.service.ProductService;
import com.tungmr.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> findAll() {
        List<ProductEntity> temp = productDAO.findAll();
        List<Product> res = new ArrayList<Product>();
        for (ProductEntity entity : temp) {
            res.add(ProductUtils.entity2DTO(entity));
        }
        return res;
    }

    public Product getProductById(Long productId) {
        return ProductUtils.entity2DTO(productDAO.getProductById(productId));
    }

    public Product updateProduct(Product product) {
        return ProductUtils.entity2DTO(productDAO.updateProduct(ProductUtils.dto2Entity(product)));
    }

    public Product createProduct(Product product) {
        return ProductUtils.entity2DTO(productDAO.createProduct(ProductUtils.dto2Entity(product)));
    }

    @Override
    public Product checkProductExisted(String productName, String categoryName, String supplierName) {
        return ProductUtils.entity2DTO(productDAO.checkProductExisted(productName, categoryName, supplierName));
    }
}
