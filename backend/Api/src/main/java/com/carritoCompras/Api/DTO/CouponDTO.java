
package com.carritoCompras.Api.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CouponDTO {
    private Long id;
    private String code;
    private double discountPercentage;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private boolean used;

   
}
