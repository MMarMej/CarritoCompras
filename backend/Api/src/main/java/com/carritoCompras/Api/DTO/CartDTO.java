
package com.carritoCompras.Api.DTO;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CartDTO {
    private Long id;
    private UserDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CartItemDTO> cartItems;
    private List<AppliedCouponDTO> appliedCoupons;

 
}
