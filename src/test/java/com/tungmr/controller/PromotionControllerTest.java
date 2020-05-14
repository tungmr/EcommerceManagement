package com.tungmr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tungmr.model.Category;
import com.tungmr.model.Product;
import com.tungmr.model.Promotion;
import com.tungmr.model.Supplier;
import com.tungmr.service.PromotionService;
import com.tungmr.validator.PromotionValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PromotionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PromotionService promotionService;

    @Mock
    private PromotionValidator promotionValidator;

    @InjectMocks
    private PromotionController promotionController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(promotionController).build();
    }


    @Test
    public void testListPromotion() throws Exception {
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion());
        promotions.add(new Promotion());

        when(promotionService.findAll()).thenReturn(promotions);

        mockMvc.perform(get("/promotion/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.data", hasSize(2) ))
                .andExpect(jsonPath("$.status", is("Success") ));
    }

    @Test
    public void testCreatePromotion() throws Exception{

        mockMvc.perform(   post("/promotion/create-or-update?action=create_or_update&quantity=100")
                .content(asJsonString(new Promotion(null,"test", new Product(), 20, Boolean.TRUE,100L, new Timestamp(1589434145000L), new Timestamp(1592112547000L), 0L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetPromotion() throws Exception{
        mockMvc.perform(   get("/promotion/get-promotion-by-productId/?productId=14&promotionId=6")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
