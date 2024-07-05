INSERT INTO `company` (`cuit`, `direction`, `email`, `name`, `phone`) VALUES
('20-12345678-1', 'Calle Falsa 123', 'company1@example.com', 'Company One', '1234567890'),
('30-23456789-2', 'Avenida Siempre Viva 456', 'company2@example.com', 'Company Two', '0987654321'),
('40-34567890-3', 'Boulevard Industrias 789', 'company3@example.com', 'Company Three', '1122334455'),
('50-45678901-4', 'Ruta 40 KM 50', 'company4@example.com', 'Company Four', '6677889900'),
('60-56789012-5', 'Calle Comercio 101', 'company5@example.com', 'Company Five', '5544332211'),
('70-67890123-6', 'Avenida Central 202', 'company6@example.com', 'Company Six', '7788990011'),
('80-78901234-7', 'Pasaje Industrial 303', 'company7@example.com', 'Company Seven', '9988776655'),
('90-89012345-8', 'Calle Principal 404', 'company8@example.com', 'Company Eight', '1122445566'),
('20-90123456-9', 'Avenida Empresa 505', 'company9@example.com', 'Company Nine', '3344556677'),
('30-01234567-0', 'Calle Oficinas 606', 'company10@example.com', 'Company Ten', '5566778899');

INSERT INTO `customer` (`direction`, `email`, `name`, `phone`) VALUES
('Calle de los Clientes 1', 'customer1@example.com', 'Customer One', '1231231234'),
('Avenida Clientes 2', 'customer2@example.com', 'Customer Two', '4564564567'),
('Boulevard Clientes 3', 'customer3@example.com', 'Customer Three', '7897897890'),
('Ruta Clientes 4', 'customer4@example.com', 'Customer Four', '1010101010'),
('Calle Comercio 5', 'customer5@example.com', 'Customer Five', '1111111111'),
('Avenida Central 6', 'customer6@example.com', 'Customer Six', '2222222222'),
('Pasaje Industrial 7', 'customer7@example.com', 'Customer Seven', '3333333333'),
('Calle Principal 8', 'customer8@example.com', 'Customer Eight', '4444444444'),
('Avenida Empresa 9', 'customer9@example.com', 'Customer Nine', '5555555555'),
('Calle Oficinas 10', 'customer10@example.com', 'Customer Ten', '6666666666');

INSERT INTO `product` (`description`, `name`, `price`) VALUES
('Product Description 1', 'Product One', 100.00),
('Product Description 2', 'Product Two', 200.00),
('Product Description 3', 'Product Three', 300.00),
('Product Description 4', 'Product Four', 400.00),
('Product Description 5', 'Product Five', 500.00),
('Product Description 6', 'Product Six', 600.00),
('Product Description 7', 'Product Seven', 700.00),
('Product Description 8', 'Product Eight', 800.00),
('Product Description 9', 'Product Nine', 900.00),
('Product Description 10', 'Product Ten', 1000.00);

INSERT INTO `user` (`email`, `name`, `password`, `company_id`) VALUES
('user1@example.com', 'User One', 'password1', 1),
('user2@example.com', 'User Two', 'password2', 2),
('user3@example.com', 'User Three', 'password3', 3),
('user4@example.com', 'User Four', 'password4', 4),
('user5@example.com', 'User Five', 'password5', 5),
('user6@example.com', 'User Six', 'password6', 6),
('user7@example.com', 'User Seven', 'password7', 7),
('user8@example.com', 'User Eight', 'password8', 8),
('user9@example.com', 'User Nine', 'password9', 9),
('user10@example.com', 'User Ten', 'password10', 10);

INSERT INTO `invoice` (`date`, `invoice_voucher`, `invoiced`, `paid`, `total`, `type`, `company_id`, `customer_id`) VALUES
('2023-01-01 10:00:00.123456', 'FACTURA', b'1', b'0', 1000.00, 'A', 11, 11),
('2023-02-01 11:00:00.234567', 'REMITO', b'0', b'1', 2000.00, 'B', 2, 2),
('2023-03-01 12:00:00.345678', 'FACTURA', b'1', b'1', 1500.00, 'C', 3, 3),
('2023-04-01 13:00:00.456789', 'REMITO', b'1', b'0', 2500.00, 'A', 4, 4),
('2023-05-01 14:00:00.567890', 'FACTURA', b'0', b'0', 3000.00, 'B', 5, 5),
('2023-06-01 15:00:00.678901', 'REMITO', b'1', b'1', 3500.00, 'C', 6, 6),
('2023-07-01 16:00:00.789012', 'FACTURA', b'0', b'1', 4000.00, 'A', 7, 7),
('2023-08-01 17:00:00.890123', 'REMITO', b'1', b'0', 4500.00, 'B', 8, 8),
('2023-09-01 18:00:00.901234', 'FACTURA', b'0', b'1', 5000.00, 'C', 9, 9),
('2023-10-01 19:00:00.012345', 'REMITO', b'1', b'1', 5500.00, 'A', 10, 10);

INSERT INTO `invoice_product` (`amount`, `invoice_id`, `product_id`) VALUES
(10, 1, 1), (20, 2, 2), (15, 3, 3), (25, 4, 4), (30, 5, 5), (35, 6, 6),
(40, 7, 7), (45, 8, 8), (50, 9, 9), (55, 10, 10);