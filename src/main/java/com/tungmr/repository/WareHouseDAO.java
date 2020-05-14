package com.tungmr.repository;

import com.tungmr.entity.WareHouseEntity;

public interface WareHouseDAO {

    WareHouseEntity createWareHouseForProduct(Long productId, Long quantity);

    WareHouseEntity findWareHouseByProductId(Long productId);

    WareHouseEntity findWareHouseById(Long wareHouseId);

    WareHouseEntity updateWareHouse(WareHouseEntity wareHouseEntity);
}
