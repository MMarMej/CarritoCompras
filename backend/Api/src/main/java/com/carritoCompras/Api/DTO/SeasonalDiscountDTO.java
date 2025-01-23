
package com.carritoCompras.Api.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SeasonalDiscountDTO {
    private Long id;
    private ProductDTO product;
    private double discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
