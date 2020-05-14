package com.tungmr.validator;

import com.tungmr.model.Promotion;
import com.tungmr.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PromotionValidator implements Validator {

    @Autowired
    private PromotionService promotionService;

    public boolean supports(Class<?> aClass) {
        return this.getClass().isAssignableFrom(aClass.getClass());
    }

    public void validate(Object o, Errors errors) {

        Promotion promotion = (Promotion) o;
        checkRequiredField(promotion, errors);
        checkInvalidField(promotion, errors);
        checkUniqueField(promotion, errors);

    }

    private void checkRequiredField(Promotion promotion, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "promotionName", "error.required");
        //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "start", "error.required");
        //  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "end", "error.required");
        if (promotion.getStartTime() == null) {
            errors.rejectValue("startTime", "error.required");

        }
        if (promotion.getEndTime() == null) {
            errors.rejectValue("endTime", "error.required");

        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxQuantity", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "discount", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.productId", "error.required");

    }

    private void checkInvalidField(Promotion promotion, Errors errors) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m;
        if (!promotion.getPromotionName().isEmpty()) {
            m = p.matcher(promotion.getPromotionName());
            if (m.find()) {
                errors.rejectValue("promotionName", "invalid.special");
            }
        }

        if (promotion.getStartTime() != null && promotion.getEndTime() != null) {
            if (promotion.getStartTime().after(promotion.getEndTime())) {
                errors.rejectValue("startTime", "invalid.field");
            }
        }

        if (promotion.getMaxQuantity() != null && promotion.getMaxQuantity() <= 0) {
            errors.rejectValue("maxQuantity", "invalid.field");
        }

        if (promotion.getDiscount() != null) {
            if (promotion.getDiscount() <= 0 || promotion.getDiscount() > 100)
                errors.rejectValue("discount", "invalid.field");
        }


    }

    private void checkUniqueField(Promotion promotion, Errors errors) {

        if (promotion.getPromotionName() != null && promotion.getProduct() != null && promotion.getProduct().getProductId() != null) {
            Promotion promotionCheck = promotionService.checkPromotionExisted(promotion.getPromotionName(), promotion.getProduct().getProductId());
            if (promotionCheck != null) {
                if (promotion.getPromotionId() != null && promotionCheck.getPromotionId() != null && !promotion.getPromotionId().equals(promotionCheck.getPromotionId())) {
                    errors.rejectValue("promotionName", "error.duplicated");
                } else if (promotionCheck.getPromotionId() != null) {
                    errors.rejectValue("promotionName", "error.duplicated");

                }
            }
        }
    }
}
