package com.example.Narrow.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Data;

@Data
@Entity
@Table(name="ORDERS")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Order implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ASSORTMENT_ID", referencedColumnName = "ID")
    private Assortment assortment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "ID")
    private Provider provider;
    @Column(name = "PRICE_PURCHASE_PLN")
    private BigDecimal pricePurchasePLN;
    @Column(name = "PRICE_PURCHASE_EUR")
    private BigDecimal pricePurchaseEUR;
    @Column(name = "QUANTITY_PURCHASE")
    private BigDecimal quantityPurchase;
    @Column(name = "VALUE_PURCHASE_PLN")
    private BigDecimal valuePurchasePLN;
    @Column(name = "VALUE_PURCHASE_EUR")
    private BigDecimal valuePurchaseEUR;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private Customer customer;
    @Column(name = "PRICE_SELL_PLN")
    private BigDecimal priceSellPLN;
    @Column(name = "PRICE_SELL_EUR")
    private BigDecimal priceSellEUR;
    @Column(name = "QUANTITY_SELL")
    private BigDecimal quantitySell;
    @Column(name = "VALUE_SELL_PLN")
    private BigDecimal valueSellPLN;
    @Column(name = "VALUE_SELL_EUR")
    private BigDecimal valueSellEUR;
    @Column(name = "PROFIT_PLN")
    private BigDecimal profitPLN;
    @Column(name = "PROFIT_EUR")
    private BigDecimal profitEUR;
    private String symbol;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    private int status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
    private Currency currency;
    private String comments;
    

}
