package com.tungmr.service;

import com.tungmr.model.WareHouse;

public interface WareHouseService {

    WareHouse createWareHouseForProduct(Long productId, Long quantity);

    WareHouse findWareHouseByProductId(Long productId);

    WareHouse findWareHouseById(Long wareHouseId);

    WareHouse updateWareHouse(WareHouse wareHouse);
}
