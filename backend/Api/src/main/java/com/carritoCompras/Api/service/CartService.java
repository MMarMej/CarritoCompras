package com.carritoCompras.Api.service;

import com.carritoCompras.Api.exceptions.InvalidCouponException;
import com.carritoCompras.Api.exceptions.ResourceNotFoundException;
import com.carritoCompras.Api.model.*;
import com.carritoCompras.Api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private SeasonalDiscountRepository seasonalDiscountRepository;
    @Autowired
    private CouponRepository couponRepository;

    

    @Autowired
    private AppliedCouponRepository appliedCouponRepository;

    // Método para agregar producto al carrito
    public void addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(BigDecimal.valueOf(product.getPrice().doubleValue()));
        }
        cartItemRepository.save(cartItem);
    }

    // Método para eliminar producto del carrito
    public void removeProductFromCart(Long cartId, Long productId) {
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
        }
    }

    // Método para actualizar la cantidad de un producto en el carrito
    public void updateProductQuantity(Long cartId, Long productId, int quantity) {
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);
        if (cartItem != null) {
            if (quantity == 0) {
                cartItemRepository.delete(cartItem);
            } else {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
            }
        }
    }

    // Método para ver el contenido del carrito
    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    // Método para calcular el subtotal dinámicamente
    private BigDecimal calculateSubtotal(CartItem cartItem) {
        return cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    // Método para calcular el total del carrito
    public BigDecimal getCartTotal(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        return cartItems.stream()
                .map(this::calculateSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Método para obtener el descuento de temporada actual para un producto
    public BigDecimal getSeasonalDiscount(Product product) {
        LocalDateTime now = LocalDateTime.now();
        SeasonalDiscount discount = seasonalDiscountRepository.findByProductId(product.getId())
                .stream()
                .filter(d -> d.getStartDate().isBefore(now) && d.getEndDate().isAfter(now))
                .findFirst()
                .orElse(null);

        if (discount != null) {
            return discount.getDiscountPercentage();
        }
        return BigDecimal.ZERO;
    }

    // Método para calcular el subtotal dinámicamente con descuentos de temporada
    public BigDecimal calculateSubtotalDiscount(CartItem cartItem) {
        BigDecimal price = cartItem.getPrice();
        BigDecimal discountPercentage = getSeasonalDiscount(cartItem.getProduct());
        if (discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
            price = price.subtract(price.multiply(discountPercentage.divide(BigDecimal.valueOf(100))));
        }
        return price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
    // Método para aplicar un cupón
    public void applyCouponToCart(Long cartId, String couponCode) {
        Coupon coupon = couponRepository.findByCode(couponCode);
        if (coupon != null && !coupon.getUsed() && coupon.getValidFrom().isBefore(LocalDateTime.now()) && coupon.getValidUntil().isAfter(LocalDateTime.now())) {
            AppliedCoupon appliedCoupon = new AppliedCoupon();
            appliedCoupon.setCart(cartRepository.findById(cartId).orElseThrow());
            appliedCoupon.setCoupon(coupon);
            appliedCouponRepository.save(appliedCoupon);
            coupon.setUsed(true);
            couponRepository.save(coupon);
        } else {
            throw new InvalidCouponException("Invalid or expired coupon");
        }
    }
}
