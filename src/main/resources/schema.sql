DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       email VARCHAR(255)
);

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL,
                          stock_quantity INT NOT NULL,
                          category VARCHAR(255)
);

CREATE TABLE carts (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT UNIQUE REFERENCES users(id)
);

CREATE TABLE cart_items (
                            id BIGSERIAL PRIMARY KEY,
                            cart_id BIGINT REFERENCES carts(id),
                            product_id BIGINT REFERENCES products(id),
                            quantity INT NOT NULL
);

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT REFERENCES users(id),
                        total_price DECIMAL(10, 2) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             order_id BIGINT REFERENCES orders(id),
                             product_id BIGINT REFERENCES products(id),
                             quantity INT NOT NULL,
                             price_at_purchase DECIMAL(10, 2) NOT NULL
);