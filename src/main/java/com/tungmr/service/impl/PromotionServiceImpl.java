package com.tungmr.service.impl;

import com.tungmr.entity.PromotionEntity;
import com.tungmr.model.Product;
import com.tungmr.model.ProductPromotion;
import com.tungmr.model.Promotion;
import com.tungmr.repository.PromotionDAO;
import com.tungmr.service.ProductService;
import com.tungmr.service.PromotionService;
import com.tungmr.utils.PromotionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionDAO promotionDAO;

    @Autowired
    private ProductService productService;

    public List<Promotion> findAll() {
        List<PromotionEntity> temp = promotionDAO.findAll();
        List<Promotion> res = new ArrayList<Promotion>();
        for (PromotionEntity entity : temp) {
            res.add(PromotionUtils.entity2DTO(entity));
        }
        return res;
    }

    public Promotion getPromotionById(Long promotionId) {
        return PromotionUtils.entity2DTO(promotionDAO.getPromotionById(promotionId));
    }

    public Promotion updatePromotion(Promotion promotion) {
        return PromotionUtils.entity2DTO(promotionDAO.updatePromotion(PromotionUtils.dto2Entity(promotion)));
    }

    public Promotion createPromotion(Promotion promotion) {
        return PromotionUtils.entity2DTO(promotionDAO.createPromotion(PromotionUtils.dto2Entity(promotion)));
    }

    @Override
    public Promotion checkPromotionExisted(String promotionName, Long productID) {
        return PromotionUtils.entity2DTO(promotionDAO.checkPromotionExisted(promotionName, productID));
    }

    @Override
    public ProductPromotion getPromotionForProduct(Long productId, Long promotionId) {

        ProductPromotion res = new ProductPromotion();

        PromotionEntity promotionEntity = promotionDAO.getPromotionByProductId(productId, promotionId);
        Product product = productService.getProductById(productId);

        Promotion promotion = PromotionUtils.entity2DTO(promotionEntity);
        if (promotion.getPromotionId() != null) {
            res.setPromotion(promotion);
            res.setProduct(product);
            res.setPromotionPrice(product.getPrice());

            if (promotion.getActive()) {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                if (promotion.getMaxQuantity() > promotion.getQuantity() && now.before(promotion.getEndTime())) {
                    res.setPromotionPrice(product.getPrice() - (product.getPrice() * ((double) promotion.getDiscount() / 100)));
                    promotion.setQuantity(promotion.getQuantity() + 1);
                    if (promotion.getQuantity().equals(promotion.getMaxQuantity())) {
                        promotion.setActive(Boolean.TRUE);
                    }
                }
            }

        } else {

            res.setProduct(product);
            res.setPromotion(promotion);
            res.setPromotionPrice(product.getPrice());

        }
        updatePromotion(promotion);
        return res;
    }
}
