package com.phoneshop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detail_bill")
public class DetailBillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @Column(name = "product_name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String productName;
    
    @Column(name = "cost", nullable = false) // Giá gốc
    private long cost;
    
    @Column(name = "price") // Giá bán
    private long price;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "bill_id", nullable = false)
    private BillEntity billEntity;

	public DetailBillEntity(int quantity, String productName, long cost, long price, BillEntity billEntity) {
		super();
		this.quantity = quantity;
		this.productName = productName;
		this.cost = cost;
		this.price = price;
		this.billEntity = billEntity;
	}
    
    
}
