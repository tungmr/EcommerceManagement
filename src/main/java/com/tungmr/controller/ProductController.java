package com.tungmr.controller;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.tungmr.constants.Constants;
import com.tungmr.model.Product;
import com.tungmr.service.ProductService;
import com.tungmr.validator.ProductValidator;
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
@RequestMapping(value = "/product")
public class ProductController {

    private final Logger LOG = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    public Map<String, Object> getAllProduct() {
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

    @RequestMapping(value = "/create-or-update", method = RequestMethod.POST)
    public ResponseEntity<Product> createOrUpdateProduct(@RequestBody Product product, @RequestParam(name = "action") String action,
                                                         @RequestParam(name = "quantity") Long quantity, BindingResult bindingResult) {
        ResponseEntity<Product> rs = null;
        try {
            productValidator.validate(product, bindingResult);
            if (!bindingResult.hasErrors()) {
                if (!action.isEmpty() && action.equals(Constants.CREATE_UPDATE_ACTION)) {
                    if (product.getProductId() != null) {
                        Product dto = productService.updateProduct(product);
                        rs = new ResponseEntity<Product>(dto, HttpStatus.OK);
                    } else if (quantity > 0L) {
                        Product dto = productService.createProduct(product, quantity);
                        rs = new ResponseEntity<Product>(dto, HttpStatus.CREATED);
                    } else {
                        rs = new ResponseEntity<Product>(HttpStatus.NOT_MODIFIED);

                    }
                }
            } else {
                rs = new ResponseEntity<Product>(HttpStatus.NOT_MODIFIED);

            }
        } catch (Exception e) {
            e.printStackTrace();
            rs = new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);

        }
        return rs;

    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Map<String, Object> getProductById(@PathVariable(name = "productId") Long productId) {
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
