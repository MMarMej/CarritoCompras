
package com.carritoCompras.Api.DTO;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private ProductDTO product;
    private int quantity;
    private BigDecimal price;

   
}
