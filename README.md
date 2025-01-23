
# Proyecto de Base de Datos para Carrito de Compras

Este proyecto contiene la estructura de una base de datos para gestionar un carrito de compras en línea, con tablas para usuarios, productos, carritos, cupones de descuento, y descuentos de temporada.

## Descripción de la Base de Datos

La base de datos consta de las siguientes tablas:

### 1. **Tabla `users`**  
Almacena la información de los usuarios del sistema.
- `id`: Identificador único del usuario (clave primaria).
- `name`: Nombre del usuario.
- `email`: Correo electrónico del usuario (único).

### 2. **Tabla `products`**  
Contiene los productos disponibles en el sistema.
- `id`: Identificador único del producto (clave primaria).
- `name`: Nombre del producto.
- `price`: Precio del producto.
- `stock`: Cantidad de unidades disponibles en inventario.

### 3. **Tabla `cart`**  
Representa el carrito de compras de un usuario.
- `id`: Identificador único del carrito (clave primaria).
- `user_id`: Referencia al usuario que posee el carrito.
- `created_at`: Fecha de creación del carrito.
- `updated_at`: Fecha de la última actualización del carrito.

### 4. **Tabla `cart_items`**  
Almacena los productos agregados a un carrito.
- `id`: Identificador único del ítem en el carrito (clave primaria).
- `cart_id`: Referencia al carrito al que pertenece el ítem.
- `product_id`: Referencia al producto agregado al carrito.
- `quantity`: Cantidad del producto agregada al carrito.
- `price`: Precio del producto en el momento de la compra.

### 5. **Tabla `coupons`**  
Contiene los cupones de descuento disponibles.
- `id`: Identificador único del cupón (clave primaria).
- `code`: Código del cupón (único).
- `discount_percentage`: Porcentaje de descuento.
- `valid_from`: Fecha de inicio de validez del cupón.
- `valid_until`: Fecha de caducidad del cupón.
- `used`: Estado del cupón (si ha sido usado).

### 6. **Tabla `seasonal_discounts`**  
Contiene los descuentos de temporada aplicados a productos específicos.
- `id`: Identificador único del descuento (clave primaria).
- `product_id`: Referencia al producto que recibe el descuento.
- `discount_percentage`: Porcentaje de descuento aplicado.
- `start_date`: Fecha de inicio del descuento.
- `end_date`: Fecha de finalización del descuento.

### 7. **Tabla `applied_coupons`**  
Registra los cupones que han sido aplicados a un carrito de compras.
- `id`: Identificador único de la aplicación del cupón (clave primaria).
- `cart_id`: Referencia al carrito al que se le aplica el cupón.
- `coupon_id`: Referencia al cupón aplicado.
- `applied_at`: Fecha y hora en que se aplicó el cupón.

### 8. **Índices**  
Se han creado los siguientes índices para mejorar el rendimiento de las consultas:
- `idx_cart_user`: Índice sobre el campo `user_id` en la tabla `cart`.
- `idx_cart_items_cart`: Índice sobre el campo `cart_id` en la tabla `cart_items`.
- `idx_seasonal_discounts_product`: Índice sobre el campo `product_id` en la tabla `seasonal_discounts`.

## Diagrama de la Base de Datos

A continuación, se muestra el diagrama de la base de datos que representa las tablas y sus relaciones:

![Diagrama de la Base de Datos](Database/diagrama_base_datos.png)

El diagrama de la base de datos se encuentra en la carpeta **Database** de este repositorio.

## Instrucciones para Uso

1. **Clona este repositorio** a tu máquina local:
   ```bash
   git clone <URL-del-repositorio>
   ```

2. **Conéctate a tu base de datos** utilizando la herramienta de tu preferencia (como TablePlus, DBeaver, o MySQL Workbench).

3. **Ejecuta el archivo SQL** para crear las tablas y la estructura de la base de datos:
   - Importa o ejecuta los scripts SQL en tu base de datos para crear las tablas y las relaciones.
   
4. **Consulta y administra los datos**:
   - Puedes realizar consultas a las tablas, insertar datos y probar el sistema según tus necesidades.

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue estos pasos:
1. Haz un fork del repositorio.
2. Crea una rama nueva para tu feature (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz un commit (`git commit -am 'Añadir nueva característica'`).
4. Haz push a tu rama (`git push origin feature/nueva-caracteristica`).
5. Crea un pull request.

---

Este archivo README proporciona una descripción completa de la base de datos y cómo utilizarla, incluyendo las tablas, relaciones y el diagrama. Además, proporciona instrucciones claras sobre cómo usar la base de datos en tu propio entorno.

Si necesitas más detalles o ayuda adicional, no dudes en contactar.
