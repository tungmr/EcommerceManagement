package com.tungmr.service.impl;

import com.tungmr.entity.ProductEntity;
import com.tungmr.entity.WareHouseEntity;
import com.tungmr.model.WareHouse;
import com.tungmr.repository.WareHouseDAO;
import com.tungmr.service.WareHouseService;
import com.tungmr.utils.WareHouseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WareHouseServiceImpl implements WareHouseService {

    @Autowired
    private WareHouseDAO wareHouseDAO;

    @Override
    public WareHouse createWareHouseForProduct(Long productId, Long quantity) {
        return WareHouseUtils.entity2DTO(wareHouseDAO.createWareHouseForProduct(productId, quantity));
    }

    @Override
    public WareHouse findWareHouseByProductId(Long productId) {
        return WareHouseUtils.entity2DTO(wareHouseDAO.findWareHouseByProductId(productId));
    }

    @Override
    public WareHouse findWareHouseById(Long wareHouseId) {
        return WareHouseUtils.entity2DTO(wareHouseDAO.findWareHouseById(wareHouseId));
    }

    @Override
    public WareHouse updateWareHouse(WareHouse wareHouse) {
        return WareHouseUtils.entity2DTO(wareHouseDAO.updateWareHouse(WareHouseUtils.dto2Entity(wareHouse)));
    }
}
