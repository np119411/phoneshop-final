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

import com.phoneshop.shopping.LineItem;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detail_order")
public class DetailOrderEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productEntity;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;

	public DetailOrderEntity(int quantity, ProductEntity productEntity) {
		super();
		this.quantity = quantity;
		this.productEntity = productEntity;
	}
    
}
