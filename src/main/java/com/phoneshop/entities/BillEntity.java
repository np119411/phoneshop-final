package com.phoneshop.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "total_quantity")
    private int totalQuantity;
    
    @Column(name = "total_price")
    private long totalPrice;
    
    @Column(name = "full_name", columnDefinition = "VARCHAR(50)")
    private String fullName;
    
    @Column(name = "address", columnDefinition = "VARCHAR(100)")
    private String address;
    
    @Column(name = "phone", columnDefinition = "VARCHAR(20)")
    private String phone;
    
    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;
    
    @Column(name = "status", columnDefinition = "VARCHAR(20)")
    private String status;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailOrderEntity> detailBillEntities = new ArrayList<>();
}
