package com.tungmr.utils;

import com.tungmr.entity.ProductEntity;
import com.tungmr.model.Product;

public class ProductUtils {

    public static ProductEntity dto2Entity(Product product) {
        ProductEntity entity = new ProductEntity();

        if(product!=null){

            entity.setProductId(product.getProductId());
            entity.setCategory(CategoryUtils.dto2Entity(product.getCategory()));
            entity.setProductName(product.getProductName());
            entity.setSupplier(SupplierUtils.dto2Entity(product.getSupplier()));
            entity.setPrice(product.getPrice());

        }
        return entity;
    }

    public static Product entity2DTO(ProductEntity entity) {
        Product dto = new Product();

        if (entity != null) {
            dto.setProductId(entity.getProductId());
            dto.setProductName(entity.getProductName());
            dto.setCategory(CategoryUtils.entity2DTO(entity.getCategory()));
            dto.setSupplier(SupplierUtils.entity2DTO(entity.getSupplier()));
            dto.setPrice(entity.getPrice());
        }

        return dto;
    }
}
