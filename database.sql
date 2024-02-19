CREATE DATABASE learn_java_persistence_api;

USE learn_java_persistence_api;

# ====== CUSTOMERS ========== #

CREATE TABLE customers
(
    id VARCHAR(255)NOT NULL PRIMARY KEY ,
    name VARCHAR(100)NOT NULL
)ENGINE InnoDB;

DELETE FROM customers WHERE id = 1;

# tambah column age and married customer
ALTER TABLE customers
    ADD COLUMN age TINYINT;

ALTER TABLE customers
    ADD COLUMN married BOOLEAN;

SELECT * FROM customers;

# menambahkan column email
ALTER TABLE customers
ADD COLUMN primary_email VARCHAR(150);

# TAMBAH COLUMN TYPE ENUM
ALTER TABLE customers
ADD COLUMN type VARCHAR(50);

# ====== CATEGORIES ========== #
CREATE TABLE categories
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,
    description VARCHAR(500)
) ENGINE InnoDB;

SELECT * FROM categories;

#TAMBAH DATE AND TIME
ALTER  TABLE  categories
ADD COLUMN create_at TIMESTAMP;

ALTER  TABLE  categories
ADD COLUMN update_at TIMESTAMP;

# ====== IMAGES ========== #
CREATE TABLE images
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,
    description TEXT,
    image BLOB
) ENGINE InnoDB;

DELETE FROM images;
DROP TABLE images;

SELECT * FROM images;

# ====== MEMBERS ========== #
CREATE TABLE members
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    email VARCHAR(150) NOT NULL ,
    title VARCHAR(100),
    first_name VARCHAR(100),
    middel_name VARCHAR(100),
    last_name VARCHAR(100)
) ENGINE InnoDB;

ALTER TABLE members
CHANGE COLUMN middel_name middle_name VARCHAR(100);
# delete value per index
DELETE FROM members WHERE id = 4;
# mengembalikan ke index 1 lagi
ALTER TABLE members AUTO_INCREMENT = 1;


DELETE FROM members;
DROP TABLE members;

SELECT * FROM members;