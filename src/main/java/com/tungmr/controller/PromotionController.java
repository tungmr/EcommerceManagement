package com.tungmr.controller;

import com.tungmr.constants.Constants;
import com.tungmr.model.ProductPromotion;
import com.tungmr.model.Promotion;
import com.tungmr.service.PromotionService;
import com.tungmr.validator.PromotionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionValidator promotionValidator;

    @RequestMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    public Map<String, Object> getAllPromotion() {
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

    @RequestMapping(value = "/{promotionId}", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    public Map<String, Object> getPromotionById(@PathVariable(name = "promotionId") Long promotionId) {
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


    @RequestMapping(value = "/create-or-update", method = RequestMethod.POST)
    public ResponseEntity<Promotion> createOrUpdatePromotion(@RequestBody Promotion promotion, @RequestParam(name = "action") String action, BindingResult bindingResult) {
        ResponseEntity<Promotion> rs = null;
        try {
            promotionValidator.validate(promotion, bindingResult);
            if (!bindingResult.hasErrors()) {
                if (!action.isEmpty() && action.equals(Constants.CREATE_UPDATE_ACTION)) {
                    if (promotion.getPromotionId() != null) {
                        Promotion dto = promotionService.updatePromotion(promotion);
                        rs = new ResponseEntity<>(dto, HttpStatus.OK);
                    } else {
                        Promotion dto = promotionService.createPromotion(promotion);
                        rs = new ResponseEntity<>(dto, HttpStatus.CREATED);
                    }
                }
            } else {
                rs = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rs = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return rs;
    }

    @RequestMapping(value = "/get-promotion-by-productId", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    public Map<String, Object> getPromotionByProductId(@RequestParam(name = "productId") Long productId,
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
