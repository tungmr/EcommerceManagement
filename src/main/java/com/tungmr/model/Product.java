package com.tungmr.model;


public class Product {

    private Long productId;
    private String productName;
    private Category category;
    private Supplier supplier;
    private Double price;

    public Product() {
    }

    public Product(Long productId, String productName, Category category, Supplier supplier, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.supplier = supplier;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
