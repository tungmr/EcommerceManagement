package com.tungmr.utils;


import com.tungmr.entity.SupplierEntity;
import com.tungmr.model.Supplier;

public class SupplierUtils {

    public static SupplierEntity dto2Entity(Supplier supplier){
        SupplierEntity entity = new SupplierEntity();
        if(supplier!=null){
            entity.setSupplierId(supplier.getSupplierId());
            entity.setSupplierName(entity.getSupplierName());
        }
        return entity;
    }

    public static Supplier entity2DTO(SupplierEntity entity){
        Supplier dto = new Supplier();
        if (entity!=null){
            dto.setSupplierId(entity.getSupplierId());
            dto.setSupplierName(entity.getSupplierName());
        }
        return dto;
    }
}
