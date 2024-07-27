USE billing_system;

CREATE TABLE `COMPANY` (
        `company_id` BINARY(16) NOT NULL PRIMARY KEY,
        `cuit` varchar(255) NOT NULL,
        `direction` varchar(255) DEFAULT NULL,
        `email` varchar(255) NOT NULL,
        `name` varchar(255) DEFAULT NULL,
        `phone` varchar(255) DEFAULT NULL,
        UNIQUE KEY `COMPANY_unique_key_cuit` (`cuit`),
        UNIQUE KEY `COMPANY_unique_key_email` (`email`)
) -- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `CUSTOMER` (
        `customer_id` BINARY(16) NOT NULL PRIMARY KEY,
        `email` varchar(255) NOT NULL,
        `name` varchar(255) DEFAULT NULL,
        `password` varchar(255) NOT NULL,
        `company_id` BINARY(16) DEFAULT NULL,
        UNIQUE KEY `CUSTOMER_unique_key_email` (`email`),
        UNIQUE KEY `CUSTOMER_unique_key_company_id` (`company_id`),
        CONSTRAINT `CUSTOMER_foreign_key_company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) -- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `PRODUCT` (
       `product_id` BINARY(16) NOT NULL PRIMARY KEY,
       `description` varchar(255) DEFAULT NULL,
       `name` varchar(255) NOT NULL,
       `price` double NOT NULL
) -- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `INVOICE` (
       `invoice_id` BINARY(16) NOT NULL PRIMARY KEY,
       `date` TIMESTAMP NOT NULL,
       `discount` INT DEFAULT NULL,
       `invoice_voucher` ENUM('BILL','CASH','REFERENCE') DEFAULT NULL,
       `invoiced` bit(1) NOT NULL,
       `paid` bit(1) NOT NULL,
       `total` double NOT NULL,
       `type` ENUM('A','B','C') DEFAULT NULL,
       `buyer_company_id` BINARY(16) DEFAULT NULL,
       `seller_company_id` BINARY(16) DEFAULT NULL,
       KEY `INVOICE_foreign_key_buyer_company_id` (`buyer_company_id`),
       KEY `INVOICE_foreign_key_seller_company_id` (`seller_company_id`),
       CONSTRAINT `INVOICE_foreign_key_buyer_company_id` FOREIGN KEY (`buyer_company_id`) REFERENCES `COMPANY` (`company_id`),
       CONSTRAINT `INVOICE_foreign_key_seller_company_id` FOREIGN KEY (`seller_company_id`) REFERENCES `COMPANY` (`company_id`)
) -- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `INVOICE_PRODUCT` (
        `id` binary(16) NOT NULL,
        `amount` double DEFAULT NULL,
        `invoice_id` binary(16) DEFAULT NULL,
        `product_id` binary(16) DEFAULT NULL,
        PRIMARY KEY (`id`),
        KEY `INVOICE_PRODUCT_foreign_key_invoice_id` (`invoice_id`),
        KEY `INVOICE_PRODUCT_foreign_key_product_id` (`product_id`),
        CONSTRAINT `INVOICE_PRODUCT_foreign_key_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
        CONSTRAINT `INVOICE_PRODUCT_foreign_key_invoice_id` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`)
) -- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
