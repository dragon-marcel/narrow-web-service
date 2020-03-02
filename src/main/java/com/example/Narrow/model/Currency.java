package com.example.Narrow.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Currency {
    public Currency() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    @Column(name = "CREATE_DATE")
    private String createDate;
    @Column(name = "SALES_PRICE")
    private BigDecimal salesPrice;
    @Column(name = "BUY_PRICE")
    private BigDecimal buyPrice;

}
