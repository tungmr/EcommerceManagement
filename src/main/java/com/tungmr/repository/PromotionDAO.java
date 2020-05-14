package com.tungmr.repository;

import com.tungmr.entity.PromotionEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PromotionDAO {

    List<PromotionEntity> findAll();

    PromotionEntity getPromotionById(Long promotionId);

    PromotionEntity updatePromotion(PromotionEntity entity);

    PromotionEntity createPromotion(PromotionEntity entity);

    PromotionEntity checkPromotionExisted(String promotionName, Long productId);

    PromotionEntity getPromotionByProductId(Long productId, Long promotionId);
}
