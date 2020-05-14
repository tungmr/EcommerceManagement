package com.tungmr.utils;


import com.tungmr.entity.WareHouseEntity;
import com.tungmr.model.WareHouse;

public class WareHouseUtils {

    public static WareHouseEntity dto2Entity(WareHouse wareHouse) {
        WareHouseEntity entity = new WareHouseEntity();

        entity.setWareHouseId(wareHouse.getWareHouseId());
        entity.setQuantity(entity.getWareHouseId());
        if (wareHouse.getProduct() != null)
            entity.setProduct(ProductUtils.dto2Entity(wareHouse.getProduct()));
        return entity;
    }

    public static WareHouse entity2DTO(WareHouseEntity entity) {
        WareHouse dto = new WareHouse();
        dto.setWareHouseId(entity.getWareHouseId());
        dto.setQuantity(entity.getQuantity());
        dto.setProduct(ProductUtils.entity2DTO(entity.getProduct()));
        return dto;
    }
}
