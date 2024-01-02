CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "category" VARCHAR(10),
    brand VARCHAR(255),
    size VARCHAR(10),
    price NUMERIC(10, 2) DEFAULT 99999999,
    "description" TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE "image" (
    id SERIAL PRIMARY KEY,
    "path" VARCHAR(255) NOT NULL,
    product_id INT REFERENCES product(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE style (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE product_style (
    product_id INT REFERENCES product(id),
    style_id INT REFERENCES style(id),
    PRIMARY KEY (product_id, style_id)
);
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    "password" VARCHAR(32) NOT NULL,
    email VARCHAR(50) NOT NULL,
    "role" INT DEFAULT 0
);
CREATE TABLE "order" (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES "user"(id),
    quantity INT DEFAULT 0,
    bill NUMERIC(10, 2) DEFAULT 0,
    "status" VARCHAR(10) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE order_product (
    order_id INT REFERENCES "order"(id),
    product_id INT REFERENCES product(id),
    PRIMARY KEY (order_id, product_id)
);