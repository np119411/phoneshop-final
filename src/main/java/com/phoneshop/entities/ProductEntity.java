package com.phoneshop.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.phoneshop.enums.ProductType;
import com.phoneshop.phones.PhoneDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100)")
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "image", columnDefinition = "TEXT")
	private String image;

	@Column(name = "color", columnDefinition = "VARCHAR(50)")
	private String color;

	@Transient
	@Column(name = "price") // Giá bán (không lưu vô db, chỉ dùng hiển thị)
	private long price;

	@Column(name = "cost", nullable = false) // Giá gốc
	private long cost;

	@Column(name = "brand", columnDefinition = "VARCHAR(50)")
	private String brand;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductType type;

	@Column(name = "quantity")
	private int quantity = 0;
	
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "phone_id")
	@JsonIgnore
	private ProductEntity phone; // Sản phần này (phone case) dành cho Phone nào

	@OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductEntity> phonCases; // Sản phẩm này (Phone) có thể sử dụng phone case nào
	
	public ProductEntity (PhoneDTO product) throws Exception {
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		this.id = Integer.valueOf(product.getID());
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.price = Integer.valueOf(String.valueOf(product.getPrice()));
        this.cost = Integer.valueOf(String.valueOf(product.getPriceDel()));
        this.createDate = formatter1.parse(String.valueOf(product.getCreateDate()));
        this.image = product.getImage();
        this.quantity = product.getQuantity();
    }

	public ProductEntity(String name, long cost, boolean isActive) {
		super();
		this.name = name;
		this.cost = cost;
		this.isActive = isActive;
	}
	
}