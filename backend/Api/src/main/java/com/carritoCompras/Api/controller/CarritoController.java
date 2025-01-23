package com.carritoCompras.Api.controller;

import com.carritoCompras.Api.model.CartItem;
import com.carritoCompras.Api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CarritoController {

    @Autowired
    private CartService cartService;

    // Endpoint to add a product to the cart
    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok("Product added to cart");
    }

    // Endpoint to remove a product from the cart
    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok("Product removed from cart");
    }

    // Endpoint to update the quantity of a product in the cart
    @PutMapping("/{cartId}/update/{productId}")
    public ResponseEntity<String> updateProductQuantity(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        cartService.updateProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok("Product quantity updated");
    }

    // Endpoint to get all items in the cart
    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        List<CartItem> cartItems = cartService.getCartItems(cartId);
        return ResponseEntity.ok(cartItems);
    }

    // Endpoint to get the total of the cart
    @GetMapping("/{cartId}/total")
    public ResponseEntity<BigDecimal> getCartTotal(@PathVariable Long cartId) {
        BigDecimal total = cartService.getCartTotal(cartId);
        return ResponseEntity.ok(total);
    }

    // Endpoint to apply a coupon to the cart
    @PostMapping("/{cartId}/apply-coupon")
    public ResponseEntity<String> applyCouponToCart(@PathVariable Long cartId, @RequestParam String couponCode) {
        cartService.applyCouponToCart(cartId, couponCode);
        return ResponseEntity.ok("Coupon applied to cart");
    }
}
