package com.tungmr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tungmr.model.Category;
import com.tungmr.model.Product;
import com.tungmr.model.Supplier;
import com.tungmr.service.ProductService;
import com.tungmr.validator.ProductValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private ProductValidator productValidator;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();


    }

    @Test
    public void testListProduct() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L,"J7 Prime", new Category(), new Supplier(), 123456D));
        products.add(new Product(2L,"Macbook", new Category(), new Supplier(), 654321D));

        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.data", hasSize(2) ))
                .andExpect(jsonPath("$.status", is("Success") ));
    }

    @Test
    public void testCreateProduct() throws Exception{

        mockMvc.perform(   post("/product/create-or-update?action=create_or_update&quantity=100")
                .content(asJsonString(new Product(null,"J7 Primeeee", new Category(3L, null), new Supplier(2L, null), 123456D)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdateProduct() throws Exception{
        String path = asJsonString(new Product(1L,"J7 Prime", new Category(3L, null), new Supplier(2L, null), 123456D));
        mockMvc.perform(   post("/product/create-or-update?action=create_or_update&quantity=100")
                .content(asJsonString(path))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
