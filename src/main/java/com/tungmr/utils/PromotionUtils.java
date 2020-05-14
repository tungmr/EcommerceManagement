package com.tungmr.utils;


import com.tungmr.entity.PromotionEntity;
import com.tungmr.model.Promotion;

public class PromotionUtils {

    public static PromotionEntity dto2Entity(Promotion promotion) {
        PromotionEntity entity = new PromotionEntity();

        if (promotion != null) {
            entity.setPromotionId(promotion.getPromotionId());
            entity.setPromotionName(promotion.getPromotionName());
            entity.setMaxQuantity(promotion.getMaxQuantity());
            entity.setActive(promotion.getActive());
            entity.setDiscount(promotion.getDiscount());
            entity.setStartTime(promotion.getStartTime());
            entity.setEndTime(promotion.getEndTime());
            entity.setProduct(ProductUtils.dto2Entity(promotion.getProduct()));
            entity.setQuantity(promotion.getQuantity());
        }


        return entity;
    }

    public static Promotion entity2DTO(PromotionEntity entity) {
        Promotion dto = new Promotion();

        if (entity != null) {
            dto.setPromotionId(entity.getPromotionId());
            dto.setPromotionName(entity.getPromotionName());
            dto.setMaxQuantity(entity.getMaxQuantity());
            dto.setActive(entity.getActive());
            dto.setDiscount(entity.getDiscount());
            dto.setStartTime(entity.getStartTime());
            dto.setEndTime(entity.getEndTime());
            dto.setProduct(ProductUtils.entity2DTO(entity.getProduct()));
            dto.setQuantity(entity.getQuantity());
        }
        return dto;
    }
}
