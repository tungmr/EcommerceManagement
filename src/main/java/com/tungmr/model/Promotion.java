package com.tungmr.model;


import java.sql.Time;
import java.sql.Timestamp;

public class Promotion {

    private Long promotionId;
    private String promotionName;
    private Product product;
    private Integer discount;
    private Boolean active;
    private Long maxQuantity;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long quantity;

    public Promotion() {
    }

    public Promotion(Long promotionId, String promotionName, Product product, Integer discount, Boolean active, Long maxQuantity, Timestamp startTime, Timestamp endTime, Long quantity) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.product = product;
        this.discount = discount;
        this.active = active;
        this.maxQuantity = maxQuantity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.quantity = quantity;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
