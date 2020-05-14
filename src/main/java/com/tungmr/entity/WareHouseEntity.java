package com.tungmr.entity;

import com.tungmr.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class WareHouseEntity {

    private Long wareHouseId;
    private ProductEntity product;
    private Long quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    public Long getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(Long wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    @ManyToOne
    @JoinColumn(name = "product")
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @Basic
    @Column(name = "quantiry")
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
