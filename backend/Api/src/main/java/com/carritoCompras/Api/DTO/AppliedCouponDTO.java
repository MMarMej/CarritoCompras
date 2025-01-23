
package com.carritoCompras.Api.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppliedCouponDTO {
    private Long id;
    private CartDTO cart;
    private CouponDTO coupon;
    private LocalDateTime appliedAt;


}
