CREATE TABLE COMPANY (
    company_id uuid NOT NULL PRIMARY KEY,
    cuit varchar(255) NOT NULL UNIQUE,
    address jsonb DEFAULT NULL,
    email varchar(255) NOT NULL UNIQUE,
    name varchar(255) DEFAULT NULL,
    phone jsonb DEFAULT NULL
);

CREATE TABLE CUSTOMER (
    customer_id uuid NOT NULL PRIMARY KEY,
    email varchar(255) NOT NULL UNIQUE,
    name jsonb DEFAULT NULL,
    password varchar(255) NOT NULL,
    company_id uuid UNIQUE DEFAULT NULL ,
    CONSTRAINT CUSTOMER_foreign_key_company_id FOREIGN KEY (company_id) REFERENCES company (company_id)
);

CREATE TABLE PRODUCT (
    product_id uuid NOT NULL PRIMARY KEY,
    description varchar(255) DEFAULT NULL,
    name varchar(255) NOT NULL,
    price decimal(10,2) NOT NULL,
    count INT DEFAULT NULL
);

CREATE TABLE INVOICE (
    invoice_id uuid NOT NULL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    discount INT DEFAULT NULL,
    invoice_voucher varchar(30) DEFAULT NULL,
    invoiced boolean NOT NULL,
    paid boolean NOT NULL,
    price decimal(10,2) NOT NULL,
    currency varchar(10) NOT NULL,
    category varchar(2) DEFAULT NULL,
    buyer_company_id uuid DEFAULT NULL,
    seller_company_id uuid DEFAULT NULL,
    CONSTRAINT INVOICE_foreign_key_buyer_company_id FOREIGN KEY (buyer_company_id) REFERENCES COMPANY (company_id),
    CONSTRAINT INVOICE_foreign_key_seller_company_id FOREIGN KEY (seller_company_id) REFERENCES COMPANY (company_id),
    CONSTRAINT INVOICE_category_restriction CHECK ( category in ('A', 'B', 'C', 'M', 'T', 'E')),
    CONSTRAINT INVOICE_voucher_restriction CHECK ( INVOICE.invoice_voucher in ('REFERENCE', 'BILL', 'CASH'))
);

CREATE TABLE INVOICE_PRODUCT (
    invoice_id uuid DEFAULT NULL,
    product_id uuid DEFAULT NULL,
    count INT DEFAULT NULL,
    constraint primary_key primary key (invoice_id, product_id),
    CONSTRAINT INVOICE_PRODUCT_foreign_key_product_id FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT INVOICE_PRODUCT_foreign_key_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoice (invoice_id)
);

-- drop table INVOICE_PRODUCT;
-- drop table PRODUCT;
-- drop table INVOICE;
-- drop table CUSTOMER;
-- drop table COMPANY;