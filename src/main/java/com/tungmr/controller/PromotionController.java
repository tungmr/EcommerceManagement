package com.tungmr.controller;

import com.tungmr.constants.Constants;
import com.tungmr.model.Product;
import com.tungmr.model.ProductPromotion;
import com.tungmr.model.Promotion;
import com.tungmr.service.PromotionService;
import com.tungmr.validator.PromotionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionValidator promotionValidator;

    @RequestMapping(value = "/promotion/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllPromotion() {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            List<Promotion> data = promotionService.findAll();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_SUCCESS);
            rs.put(Constants.DATA_KEY, data);

        } catch (Exception e) {
            e.printStackTrace();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_ERROR);
        }
        return rs;

    }

    @RequestMapping(value = "/promotion/{promotionId}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPromotionById(@PathVariable(name = "promotionId") Long promotionId) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            Promotion data = promotionService.getPromotionById(promotionId);
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_SUCCESS);
            rs.put(Constants.DATA_KEY, data);

        } catch (Exception e) {
            e.printStackTrace();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_ERROR);
        }

        return rs;

    }


    @RequestMapping(value = "/promotion/create-or-update", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> createOrUpdatePromotion(@RequestBody Promotion promotion, @RequestParam(name = "action") String action, BindingResult bindingResult) {
        Map<String, Object> rs = new HashMap<String, Object>();

        try {
            promotionValidator.validate(promotion, bindingResult);
            if (!bindingResult.hasErrors()) {
                if (!action.isEmpty() && action.equals(Constants.CREATE_UPDATE_ACTION)) {
                    if (promotion.getPromotionId() != null) {
                        Promotion dto = promotionService.updatePromotion(promotion);
                        rs.put(Constants.STATUS_KEY, Constants.NOTIFY_UPDATE_SUCCESS);
                        rs.put(Constants.DATA_KEY, dto);

                    } else {
                        Promotion dto = promotionService.createPromotion(promotion);
                        rs.put(Constants.STATUS_KEY, Constants.NOTIFY_CREATE_SUCCESS);
                        rs.put(Constants.DATA_KEY, dto);
                    }
                }
            } else {
                rs.put(Constants.STATUS_KEY, Constants.NOTIFY_ERROR_FIELD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_ERROR);
        }

        return rs;
    }

    @RequestMapping(value = "/promotion/get-promotion-by-productId", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getPromotionByProductId(@RequestParam(name = "productId") Long productId,
                                                @RequestParam(name = "promotionId") Long promotionId) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            ProductPromotion productPromotion = promotionService.getPromotionForProduct(productId, promotionId);
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_SUCCESS);
            rs.put(Constants.DATA_KEY, productPromotion);

        } catch (Exception e) {
            e.printStackTrace();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_ERROR);
        }
        return rs;
    }

}
