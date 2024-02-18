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

# ====== CATEGORIES ========== #
CREATE TABLE categories
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,
    description VARCHAR(500)
) ENGINE InnoDB;

SELECT * FROM categories;

# ALTER  TABLE  categories
# DROP COLUMN age;

# ALTER  TABLE  categories
#    DROP COLUMN married;


