package com.tungmr.controller;

import com.tungmr.constants.Constants;
import com.tungmr.model.Product;
import com.tungmr.service.ProductService;
import com.tungmr.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping(value = "/product/getAll", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAllProduct() {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            List<Product> data = productService.findAll();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_SUCCESS);
            rs.put(Constants.DATA_KEY, data);

        } catch (Exception e) {
            e.printStackTrace();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_ERROR);
        }

        return rs;

    }

    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> createOrUpdateProduct(@RequestBody Product product, @RequestParam(name = "action") String action, BindingResult bindingResult) {
        Map<String, Object> rs = new HashMap<String, Object>();

        try {
            productValidator.validate(product, bindingResult);
            if (!bindingResult.hasErrors()) {
                if (!action.isEmpty() && action.equals(Constants.CREATE_UPDATE_ACTION)) {
                    if (product.getProductId() != null) {
                        Product dto = productService.updateProduct(product);
                        rs.put(Constants.STATUS_KEY, Constants.NOTIFY_UPDATE_SUCCESS);
                        rs.put(Constants.DATA_KEY, dto);

                    } else {
                        Product dto = productService.createProduct(product);
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

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getProductById(@PathVariable(name = "productId") Long productId) {
        Map<String, Object> rs = new HashMap<String, Object>();
        try {
            Product data = productService.getProductById(productId);
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_SUCCESS);
            rs.put(Constants.DATA_KEY, data);

        } catch (Exception e) {
            e.printStackTrace();
            rs.put(Constants.STATUS_KEY, Constants.NOTIFY_ERROR);
        }
        return rs;
    }


}
