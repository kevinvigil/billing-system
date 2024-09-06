INSERT INTO company (company_id, cuit, address, email, name, phone) VALUES
('0da5b779-832e-4c97-8080-ddb08de8a309', '20-12345678-1', '{"country": "Argentina", "state": "Buenos Aires", "city": "Calle Falsa", "zip": "123"}', 'company1@example.com', 'Company One', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "1234567890"}'),
('5b7eac09-48ef-4b5e-9710-9ce715c90ae3', '30-23456789-2', '{"country": "Argentina", "state": "Buenos Aires", "city": "Avenida Siempre Viva", "zip": "456"}', 'company2@example.com', 'Company Two', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "0987654321"}'),
('6af58f1e-9ba1-477d-a828-53f00285fe9e', '40-34567890-3', '{"country": "Argentina", "state": "Buenos Aires", "city": "Boulevard Industrias", "zip": "789"}', 'company3@example.com', 'Company Three', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "1122334455"}'),
('70b2e59e-b791-49c6-9363-82b2de9b1b0f', '50-45678901-4', '{"country": "Argentina", "state": "Buenos Aires", "city": "Ruta 40", "zip": "50"}', 'company4@example.com', 'Company Four', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "6677889900"}'),
('912c5b60-1747-48bb-a6c5-ff97e13ae0a7', '60-56789012-5', '{"country": "Argentina", "state": "Buenos Aires", "city": "Calle Comercio", "zip": "101"}', 'company5@example.com', 'Company Five', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "5544332211"}'),
('a357c1fa-ca4f-4e41-8acd-78dcfea4c1ab', '70-67890123-6', '{"country": "Argentina", "state": "Buenos Aires", "city": "Avenida Central", "zip": "202"}', 'company6@example.com', 'Company Six', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "7788990011"}'),
('b55eb91d-90f5-41c0-8d13-32cb647486db', '80-78901234-7', '{"country": "Argentina", "state": "Buenos Aires", "city": "Pasaje Industrial", "zip": "303"}', 'company7@example.com', 'Company Seven', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "9988776655"}'),
('e66d2dba-d1cd-4196-8b74-efedb5c84dbf', '90-89012345-8', '{"country": "Argentina", "state": "Buenos Aires", "city": "Calle Principal", "zip": "404"}', 'company8@example.com', 'Company Eight', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "1122445566"}'),
('ec6b9e7b-0f3f-4a52-aa6d-e02976332bce', '20-90123456-9', '{"country": "Argentina", "state": "Buenos Aires", "city": "Avenida Empresa", "zip": "505"}', 'company9@example.com', 'Company Nine', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "3344556677"}'),
('f13546d3-d1dd-440c-809b-ae6b17fc4934', '30-01234567-0', '{"country": "Argentina", "state": "Buenos Aires", "city": "Calle Oficinas", "zip": "606"}', 'company10@example.com', 'Company Ten', '{"countryCode": "AR", "zoneCode": "11", "phoneNumber": "5566778899"}');

INSERT INTO customer (customer_id ,email, name, password, company_id) VALUES
('fe90545c-bc05-44c3-8a53-e6f554d05a82','customer1@example.com',  '{"firstName": "Customer", "secondName": "One", "surname": "One"}', '1231231234', '0da5b779-832e-4c97-8080-ddb08de8a309'),
('eba614cf-dd3a-4592-9eaf-e8427b83c42e','customer2@example.com',  '{"firstName": "Customer", "secondName": "Two", "surname": "Two"}', '4564564567', '5b7eac09-48ef-4b5e-9710-9ce715c90ae3'),
('c294b957-78fa-41e6-987a-2f38d1c99911','customer3@example.com',  '{"firstName": "Customer", "secondName": "Three", "surname": "Three"}', '7897897890', '6af58f1e-9ba1-477d-a828-53f00285fe9e'),
('a43d843b-68da-42c0-b17a-8a91c4f6bf0f','customer4@example.com',  '{"firstName": "Customer", "secondName": "Four", "surname": "Four"}', '1010101010', '70b2e59e-b791-49c6-9363-82b2de9b1b0f'),
('8fbd2c6e-2e6f-4ad0-bd20-d7fa43d0e551','customer5@example.com',  '{"firstName": "Customer", "secondName": "Five", "surname": "Five"}', '1111111111', '912c5b60-1747-48bb-a6c5-ff97e13ae0a7'),
('8fba0f09-a767-4a59-a877-41aa3006e703','customer6@example.com',  '{"firstName": "Customer", "secondName": "Six", "surname": "Six"}', '2222222222', 'a357c1fa-ca4f-4e41-8acd-78dcfea4c1ab'),
('7f76cc53-955d-4d62-9a6b-b3e297fe44d9','customer7@example.com',  '{"firstName": "Customer", "secondName": "Seven", "surname": "Seven"}', '3333333333', 'b55eb91d-90f5-41c0-8d13-32cb647486db'),
('5b3d07e3-dd8c-4ddb-a832-40cbe5d41ab5','customer8@example.com',  '{"firstName": "Customer", "secondName": "Eight", "surname": "Eight"}', '4444444444', 'e66d2dba-d1cd-4196-8b74-efedb5c84dbf'),
('359e2d5d-805e-47bd-9f58-4a221bfd7419','customer9@example.com',  '{"firstName": "Customer", "secondName": "Nine", "surname": "Nine"}', '5555555555', 'ec6b9e7b-0f3f-4a52-aa6d-e02976332bce'),
('2d250e89-5fe7-40da-a6e9-c299e003c59e','customer10@example.com', '{"firstName": "Customer", "secondName": "Ten", "surname": "Ten"}', '6666666666', 'f13546d3-d1dd-440c-809b-ae6b17fc4934');

INSERT INTO product (product_id,description, name, price) VALUES
('f06748be-ccc3-4d59-b4c2-518dc5e4d7a6','Product Description 1', 'Product One', 100.00),
('e3509328-842f-454d-9652-33d4ea8daeaa','Product Description 2', 'Product Two', 200.00),
('aea3977a-5636-452c-b368-aa6fdb4c35d8','Product Description 3', 'Product Three', 300.00),
('aaf4bb93-3d13-42b7-ae9d-365cbc4be17a','Product Description 4', 'Product Four', 400.00),
('9202f765-4a71-497e-958f-569e2a7c63f3','Product Description 5', 'Product Five', 500.00),
('75aa51e1-202f-4d34-a3ed-c2ae0b1bc89c','Product Description 6', 'Product Six', 600.00),
('64fa97ca-6261-42c8-8819-5affc647f4fe','Product Description 7', 'Product Seven', 700.00),
('40e09314-8301-44c8-97a6-0b55693da8f9','Product Description 8', 'Product Eight', 800.00),
('30456b8d-41e7-4746-8a5f-44a9ed2a1c90','Product Description 9', 'Product Nine', 900.00),
('1dfb9621-0fb5-44a3-9a90-0b870b2fda98','Product Description 10', 'Product Ten', 1000.00);

INSERT INTO invoice (invoice_id ,date, discount, currency, invoice_voucher, invoiced, paid, price, category, buyer_company_id, seller_company_id) VALUES
('04c7d09a-0b61-4b79-bf44-f79271eaeeea',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'CASH',true,false, 1000.00, 'A', '0da5b779-832e-4c97-8080-ddb08de8a309', '0da5b779-832e-4c97-8080-ddb08de8a309'),
('09d69173-9286-467e-b54b-c7e197c6f149',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'REFERENCE',false,true, 2000.00, 'B', '5b7eac09-48ef-4b5e-9710-9ce715c90ae3', '5b7eac09-48ef-4b5e-9710-9ce715c90ae3'),
('21be3cd1-95a7-4571-bcca-7c81ccc8f1fe',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'REFERENCE',true,true, 1500.00, 'C', '6af58f1e-9ba1-477d-a828-53f00285fe9e', '6af58f1e-9ba1-477d-a828-53f00285fe9e'),
('5770ffa9-afc0-44f1-bb99-7041b07363e2',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'CASH',true,false, 2500.00, 'A', '70b2e59e-b791-49c6-9363-82b2de9b1b0f', '70b2e59e-b791-49c6-9363-82b2de9b1b0f'),
('971ce1cb-303a-43b7-bdc3-619277a7946b',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'REFERENCE',false,false, 3000.00, 'B', '912c5b60-1747-48bb-a6c5-ff97e13ae0a7', '912c5b60-1747-48bb-a6c5-ff97e13ae0a7'),
('a5bf57d9-ae0e-483d-b2ff-185139cd7cf3',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'CASH',true,true, 3500.00, 'C', 'a357c1fa-ca4f-4e41-8acd-78dcfea4c1ab', 'a357c1fa-ca4f-4e41-8acd-78dcfea4c1ab'),
('b20d2eab-f424-4718-b3bc-f10a38d31008',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'REFERENCE',false,true, 4000.00, 'A', 'b55eb91d-90f5-41c0-8d13-32cb647486db', 'b55eb91d-90f5-41c0-8d13-32cb647486db'),
('c9032022-09d2-4863-9ac1-f486b34be429',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'BILL',true,false, 4500.00, 'B', 'e66d2dba-d1cd-4196-8b74-efedb5c84dbf', 'e66d2dba-d1cd-4196-8b74-efedb5c84dbf'),
('c97e2d4e-8ea5-432a-b1ae-6d71a4ad36c0',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'REFERENCE',false,true, 5000.00, 'C', 'ec6b9e7b-0f3f-4a52-aa6d-e02976332bce', 'ec6b9e7b-0f3f-4a52-aa6d-e02976332bce'),
('cc5ced3f-8252-43df-9839-ad3f24aa88a0',TO_TIMESTAMP('1478063369'), 0, 'ARS', 'BILL',true,true, 5500.00, 'A', 'f13546d3-d1dd-440c-809b-ae6b17fc4934', 'f13546d3-d1dd-440c-809b-ae6b17fc4934');

INSERT INTO invoice_product (invoiceproduct_id ,count, invoice_id, product_id) VALUES
('f60c5d8f-7f4a-4665-bfa9-6a741ce82365', 10, ('04c7d09a-0b61-4b79-bf44-f79271eaeeea'), ('f06748be-ccc3-4d59-b4c2-518dc5e4d7a6')),
('ed9e9383-6072-45a4-b28c-f12b29fee51b', 20, ('09d69173-9286-467e-b54b-c7e197c6f149'), ('e3509328-842f-454d-9652-33d4ea8daeaa')),
('e7fef357-28a6-4acf-a9fc-e9fbf8abe9bd', 15, ('21be3cd1-95a7-4571-bcca-7c81ccc8f1fe'), ('aea3977a-5636-452c-b368-aa6fdb4c35d8')),
('e4eebc5e-7916-44ae-a05e-733831be8c55', 25, ('5770ffa9-afc0-44f1-bb99-7041b07363e2'), ('aaf4bb93-3d13-42b7-ae9d-365cbc4be17a')),
('ce2e51df-1a0f-4944-b491-bb063122cd53', 30, ('971ce1cb-303a-43b7-bdc3-619277a7946b'), ('9202f765-4a71-497e-958f-569e2a7c63f3')),
('c4a5e578-761f-4b9b-88be-6abd1b9e2621', 35, ('a5bf57d9-ae0e-483d-b2ff-185139cd7cf3'), ('75aa51e1-202f-4d34-a3ed-c2ae0b1bc89c')),
('aa4683b8-e9d4-40ca-b35e-084a0352d294', 40, ('b20d2eab-f424-4718-b3bc-f10a38d31008'), ('64fa97ca-6261-42c8-8819-5affc647f4fe')),
('861afa9c-0f2e-4af2-b27e-cc22e4b831e5', 45, ('c9032022-09d2-4863-9ac1-f486b34be429'), ('40e09314-8301-44c8-97a6-0b55693da8f9')),
('719a5cb8-e2f4-415c-9afa-53b2e64ba754', 50, ('c97e2d4e-8ea5-432a-b1ae-6d71a4ad36c0'), ('30456b8d-41e7-4746-8a5f-44a9ed2a1c90')),
('49539afe-2710-4538-997d-9183d824e3fd', 55, ('cc5ced3f-8252-43df-9839-ad3f24aa88a0'), ('1dfb9621-0fb5-44a3-9a90-0b870b2fda98'));

select * from company;
select * from customer;
select * from product;
select price from invoice;
select * from invoice_product;
