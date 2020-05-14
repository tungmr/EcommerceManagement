package com.tungmr.validator;

import com.tungmr.model.Product;
import com.tungmr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProductValidator implements Validator {

    @Autowired
    private ProductService productService;

    public boolean supports(Class<?> aClass) {
        return this.getClass().isAssignableFrom(aClass.getClass());
    }

    public void validate(Object o, Errors errors) {
        Product product = (Product) o;
        checkRequiredField(product, errors);

        checkInvalidField(product, errors);
        checkUniqueField(product, errors);
    }


    private void checkRequiredField(Product product, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category.categoryName", "error.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "supplier.supplierName", "error.required");

    }


    private void checkInvalidField(Product product, Errors errors) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m;
        if (!product.getProductName().isEmpty()) {
            m = p.matcher(product.getProductName());
            if (m.find()) {
                errors.rejectValue("productName", "invalid.special");
            }
        }

        if (product.getCategory() != null) {
            if (!product.getCategory().getCategoryName().isEmpty()) {
                m = p.matcher(product.getCategory().getCategoryName());
                if (m.find()) {
                    errors.rejectValue("category.categoryName", "invalid.special");
                }
            }
        }

        if (product.getSupplier() != null) {
            if (!product.getSupplier().getSupplierName().isEmpty()) {
                m = p.matcher(product.getSupplier().getSupplierName());
                if (m.find()) {
                    errors.rejectValue("supplier.supplierName", "invalid.special");
                }
            }
        }
    }


    private void checkUniqueField(Product product, Errors errors) {
        if (product.getProductName() != null && product.getSupplier() != null && product.getCategory() != null) {
            if (!product.getSupplier().getSupplierName().isEmpty() && !product.getProductName().isEmpty() && !product.getCategory().getCategoryName().isEmpty()) {
                Product check = productService.checkProductExisted(product.getProductName(), product.getCategory().getCategoryName(), product.getSupplier().getSupplierName());
                if (check != null) {

                    if (product.getProductId() != null && check.getProductId() != null && !product.getProductId().equals(check.getProductId())) {
                        errors.rejectValue("productName", "error.duplicated");
                    } else if (check.getProductId() != null) {
                        errors.rejectValue("productName", "error.duplicated");

                    }
                }
            }
        }
    }
}
