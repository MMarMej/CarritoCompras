package com.carritoCompras.Api.repository;

import com.carritoCompras.Api.model.SeasonalDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeasonalDiscountRepository extends JpaRepository<SeasonalDiscount, Long> {
    List<SeasonalDiscount> findByProductId(Long productId);
}
