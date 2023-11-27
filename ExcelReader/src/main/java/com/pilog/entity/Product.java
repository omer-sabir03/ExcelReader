package com.pilog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Product")
public class Product {
    @Id
    private Integer productId;
    private String productName;
    private String productDesc;
    private Double productPrice;
}
