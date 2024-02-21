CREATE DATABASE learn_java_persistence_api;

USE learn_java_persistence_api;

# ====== CUSTOMERS ========== #

CREATE TABLE customers
(
    id   VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
) ENGINE InnoDB;

DELETE
FROM customers
WHERE id = 1;

# tambah column age and married customer
ALTER TABLE customers
    ADD COLUMN age TINYINT;

ALTER TABLE customers
    ADD COLUMN married BOOLEAN;

SELECT *
FROM customers;

# menambahkan column email
ALTER TABLE customers
    ADD COLUMN primary_email VARCHAR(150);

# TAMBAH COLUMN TYPE ENUM
ALTER TABLE customers
    ADD COLUMN type VARCHAR(50);

# ====== CATEGORIES ========== #
CREATE TABLE categories
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500)
) ENGINE InnoDB;

SELECT *
FROM categories;

#TAMBAH DATE AND TIME
ALTER TABLE categories
    ADD COLUMN create_at TIMESTAMP;

ALTER TABLE categories
    ADD COLUMN update_at TIMESTAMP;

# ====== IMAGES ========== #
CREATE TABLE images
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    image       BLOB
) ENGINE InnoDB;

DELETE
FROM images;
DROP TABLE images;

SELECT *
FROM images;

# ====== MEMBERS ========== #
CREATE TABLE members
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(150) NOT NULL,
    title       VARCHAR(100),
    first_name  VARCHAR(100),
    middel_name VARCHAR(100),
    last_name   VARCHAR(100)
) ENGINE InnoDB;

ALTER TABLE members
    CHANGE COLUMN middel_name middle_name VARCHAR(100);
# delete value per index
DELETE
FROM members
WHERE id = 4;
# mengembalikan ke index 1 lagi
ALTER TABLE members
    AUTO_INCREMENT = 1;


DELETE
FROM members;
DROP TABLE members;

SELECT *
FROM members;

# ====== DEPARTMENT ID ========== #
CREATE TABLE departments
(
    company_id    VARCHAR(100) NOT NULL,
    department_id VARCHAR(100) NOT NULL,
    name          VARCHAR(100) NOT NULL,
    PRIMARY KEY (company_id, department_id)
) ENGINE InnoDB;

SELECT *
FROM departments;

# ====== HOBBIES ========== #
CREATE TABLE hobbies
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT          NOT NULL,
    name      VARCHAR(150) NOT NULL,
    FOREIGN KEY fk_member_hobbies (member_id) REFERENCES members (id)
) ENGINE InnoDB;

DELETE
FROM hobbies;

ALTER TABLE hobbies
    AUTO_INCREMENT = 1;

SELECT *
FROM hobbies;

# ====== SKILL ========== #

CREATE TABLE skills
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT          NOT NULL,
    name      VARCHAR(100) NOT NULL,
    value     INT          NOT NULL,
    FOREIGN KEY fk_members_skills (member_id) REFERENCES members (id),
    CONSTRAINT skills_unique UNIQUE (member_id, name)
) ENGINE InnoDB;

SELECT *
FROM skills;

# ====== CREDENTIALS ========== #
CREATE TABLE credentials
(
    id       VARCHAR(100) NOT NULL PRIMARY KEY,
    email    VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL
) ENGINE InnoDB;

DROP TABLE users;

DELETE
FROM users;

SELECT *
FROM credentials;

# ====== USER ========== #
CREATE TABLE users
(
    id   VARCHAR(100) NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
) ENGINE InnoDB;

SELECT *
FROM users;

# ====== WALLET ========== #
CREATE TABLE wallet
(
    id      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    balance BIGINT       NOT NULL,
    FOREIGN KEY fk_users_wallet (user_id) REFERENCES users (id)
) ENGINE InnoDB;

DROP TABLE wallet;
SELECT *
FROM wallet;

# ====== BRAND ========== #
CREATE TABLE brands
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    name        VARCHAR(100),
    description VARCHAR(500)
) ENGINE InnoDB;

SELECT *
FROM brands;
# ====== PRODUCTS ========== #
CREATE TABLE products
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    brand_id    VARCHAR(100) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    price       BIGINT       NOT NULL,
    description VARCHAR(500),
    FOREIGN KEY fk_brands_products (brand_id) REFERENCES brands (id)
) ENGINE InnoDB;

DROP TABLE products;

-- Mengubah tipe data kolom dari INT menjadi VARCHAR
ALTER TABLE products
MODIFY id VARCHAR(100);


ALTER TABLE products
    ADD COLUMN price BIGINT;

SELECT *
FROM products;

# ====== USER LIKE PRODUCT ========== #
CREATE TABLE users_like_products
(
    user_id VARCHAR(100) NOT NULL ,
    product_id VARCHAR(100) NOT NULL ,
    FOREIGN KEY fk_users_to_users_like_products (user_id) REFERENCES users(id),
    FOREIGN KEY fk_products_to_users_like_products (product_id) REFERENCES products(id),
    PRIMARY KEY (user_id, product_id)
) ENGINE InnoDB;

SELECT * FROM users_like_products;

# ====== EMPLOYEE ========== #
CREATE TABLE employees
(
    id VARCHAR(100) NOT NULL  PRIMARY KEY ,
    type VARCHAR(100) NOT NULL ,
    name VARCHAR(100) NOT NULL ,
    total_manager INT,
    total_employee INT
) ENGINE InnoDB;

SELECT * FROM employees;

# ====== PAYMENTS ========== #

CREATE TABLE payments
(
    id     VARCHAR(100) NOT NULL PRIMARY KEY,
    amount BIGINT       NOT NULL
) ENGINE InnoDB;

SELECT * FROM payments;
DROP TABLE payments;

# ====== PAYMENTS GOPAY ========== #
CREATE TABLE payments_gopay
(
    id       VARCHAR(100) NOT NULL PRIMARY KEY,
    gopay_id VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_payments_gopay (id) REFERENCES payments (id)
) ENGINE InnoDB;

SELECT * FROM payments_gopay;

# ====== PAYMENET CREDIT CARD ========== #
CREATE TABLE payments_credit_card
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    masked_card VARCHAR(100) NOT NULL,
    bank        VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_payments_credit_card (id) REFERENCES payments (id)
) ENGINE InnoDB;

SELECT * FROM payments_credit_card;

# ====== TRANSACTION ========== #
CREATE TABLE transactions
(
    id VARCHAR(100) NOT NULL PRIMARY KEY ,
    balance BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL
)ENGINE InnoDB;

SELECT * FROM transactions;

# ====== TRANSACTION CREDIT ========== #
CREATE TABLE transactions_credit
(
    id VARCHAR(100) NOT NULL PRIMARY KEY ,
    balance BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    credit_amount BIGINT NOT NULL
)ENGINE InnoDB;

SELECT * FROM transactions_credit;

# ====== TRANSACTION DEBIT ========== #
CREATE TABLE transactions_debit
(
    id VARCHAR(100) NOT NULL PRIMARY KEY ,
    balance BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    debit_amount BIGINT NOT NULL
)ENGINE InnoDB;

SELECT * FROM transactions_debit;