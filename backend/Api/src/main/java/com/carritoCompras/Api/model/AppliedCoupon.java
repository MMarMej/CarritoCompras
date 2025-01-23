package com.carritoCompras.Api.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class AppliedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Cart cart;
    
    @ManyToOne
    private Coupon coupon;
    
    private LocalDateTime appliedAt = LocalDateTime.now();
}



