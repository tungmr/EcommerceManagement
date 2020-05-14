package com.tungmr.service;

import com.tungmr.model.ProductPromotion;
import com.tungmr.model.Promotion;

import java.util.List;

public interface PromotionService {

    List<Promotion> findAll();

    Promotion getPromotionById(Long promotionId);

    Promotion updatePromotion(Promotion promotion);

    Promotion createPromotion(Promotion promotion);

    Promotion checkPromotionExisted(String promotionName, Long productId);

    ProductPromotion getPromotionForProduct(Long productId, Long promotionId);

}
