
CREATE TABLE COMPANY (
    company_id uuid NOT NULL PRIMARY KEY,
    cuit varchar(255) NOT NULL UNIQUE,
    direction varchar(255) DEFAULT NULL,
    email varchar(255) NOT NULL UNIQUE,
    name varchar(255) DEFAULT NULL,
    phone varchar(255) DEFAULT NULL
);

CREATE TABLE CUSTOMER (
    customer_id uuid NOT NULL PRIMARY KEY,
    email varchar(255) NOT NULL UNIQUE,
    name varchar(255) DEFAULT NULL,
    password varchar(255) NOT NULL,
    company_id uuid UNIQUE DEFAULT NULL ,
    CONSTRAINT CUSTOMER_foreign_key_company_id FOREIGN KEY (company_id) REFERENCES company (company_id)
);

CREATE TABLE PRODUCT (
    product_id uuid NOT NULL PRIMARY KEY,
    description varchar(255) DEFAULT NULL,
    name varchar(255) NOT NULL,
    price decimal(10,2) NOT NULL
);

CREATE TABLE INVOICE (
    invoice_id uuid NOT NULL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    discount INT DEFAULT NULL,
    invoicevoucher varchar(30) DEFAULT NULL,
    invoiced boolean NOT NULL,
    paid boolean NOT NULL,
    total decimal(10,2) NOT NULL,
    type varchar(2) DEFAULT NULL,
    buyer_company_id uuid DEFAULT NULL,
    seller_company_id uuid DEFAULT NULL,
    CONSTRAINT INVOICE_foreign_key_buyer_company_id FOREIGN KEY (buyer_company_id) REFERENCES COMPANY (company_id),
    CONSTRAINT INVOICE_foreign_key_seller_company_id FOREIGN KEY (seller_company_id) REFERENCES COMPANY (company_id),
    CONSTRAINT INVOICE_type__restriction CHECK ( type in ('A', 'B', 'C', 'M', 'T', 'E')),
    CONSTRAINT INVOICE_type_restriction CHECK ( invoiceVoucher in ('REFERENCE', 'BILL', 'CASH'))
);

CREATE TABLE INVOICE_PRODUCT (
    invoiceProduct_id uuid NOT NULL PRIMARY KEY,
    amount INT DEFAULT NULL,
    invoice_id uuid DEFAULT NULL,
    product_id uuid DEFAULT NULL,
    CONSTRAINT INVOICE_PRODUCT_foreign_key_product_id FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT INVOICE_PRODUCT_foreign_key_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoice (invoice_id)
);

-- drop table INVOICE_PRODUCT;
-- drop table PRODUCT;
-- drop table INVOICE;
-- drop table CUSTOMER;
-- drop table COMPANY;