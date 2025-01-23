Table users {
    id SERIAL [pk]
    name VARCHAR(255)
    email VARCHAR(255) [unique]
}

Table products {
    id SERIAL [pk]
    name VARCHAR(255)
    price DECIMAL(10, 2)
    stock INT [default: 0]
}

Table cart {
    id SERIAL [pk]
    user_id INT
    created_at TIMESTAMP [default: `CURRENT_TIMESTAMP`]
    updated_at TIMESTAMP [default: `CURRENT_TIMESTAMP`]
}

Table cart_items {
    id SERIAL [pk]
    cart_id INT
    product_id INT
    quantity INT [note: 'CHECK (quantity > 0)']
    price DECIMAL(10, 2)
}

Table coupons {
    id SERIAL [pk]
    code VARCHAR(50) [unique]
    discount_percentage DECIMAL(5, 2) [note: 'CHECK (discount_percentage > 0 AND discount_percentage <= 100)']
    valid_from TIMESTAMP
    valid_until TIMESTAMP
    used BOOLEAN [default: false]
}

Table seasonal_discounts {
    id SERIAL [pk]
    product_id INT
    discount_percentage DECIMAL(5, 2) [note: 'CHECK (discount_percentage > 0 AND discount_percentage <= 100)']
    start_date TIMESTAMP
    end_date TIMESTAMP
}

Table applied_coupons {
    id SERIAL [pk]
    cart_id INT
    coupon_id INT
    applied_at TIMESTAMP [default: `CURRENT_TIMESTAMP`]
}

Ref: cart.user_id > users.id [delete: cascade]
Ref: cart_items.cart_id > cart.id [delete: cascade]
Ref: cart_items.product_id > products.id [delete: restrict]
Ref: seasonal_discounts.product_id > products.id [delete: cascade]
Ref: applied_coupons.cart_id > cart.id [delete: cascade]
Ref: applied_coupons.coupon_id > coupons.id [delete: restrict]


