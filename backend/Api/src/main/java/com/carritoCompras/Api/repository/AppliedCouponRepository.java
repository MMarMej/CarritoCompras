package com.carritoCompras.Api.repository;

import com.carritoCompras.Api.model.AppliedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AppliedCouponRepository extends JpaRepository<AppliedCoupon, Long> {
    List<AppliedCoupon> findByCartId(Long cartId);
}
