use task10Ermak;

DELETE FROM pc;
DELETE FROM laptop;
DELETE FROM printer;
DELETE FROM product;

insert into product (maker, model, type) values 
('SAMSUNG', 'samsung1001', 'PC'),
('SAMSUNG', 'samsung1002', 'PC'),
('SAMSUNG', 'samsung1003', 'PC'),
('ASUS', 'asus1003', 'PC'),
('ASUS', 'asus1004', 'PC'),
('HP', 'hp1004', 'PC'),
('Brother', 'brother10', 'PC'),

('LG', 'lg666', 'Laptop'),
('Apple', 'apple001', 'Laptop'),
('Apple', 'apple002', 'Laptop'),
('ASUS', 'asus1008', 'Laptop'),

('HP', 'hp00011', 'Printer'),
('HP', 'hp00451', 'Printer'),
('Brother', 'brother100', 'Printer');

insert into pc (code, model, speed, ram, hd, cd, price) values 
(172624354, 'samsung1001', 2048, 4096, 512, '12x', 550),
(172612725, 'samsung1002', 1024, 2048, 1024, '24x', 450),
(172612678, 'samsung1003', 4096, 2048, 1024, '24x', 650),
(172365689, 'asus1004', 512, 1024, 256, '8x', 350),
(172344521, 'asus1003', 1024, 2048, 1024, '24x', 480),
(172262521, 'hp1004', 4096, 1024, 1024, '24x', 480),
(172262231, 'brother10', 2048, 1024, 1024, '24x', 530);
    
insert into laptop (code, model, speed, ram, hd, price, screen) values 
(12345643, 'lg666', 2048, 4096, 512, 900, 15),  
(12345236, 'apple001', 4096, 8192, 1024, 1500, 18),
(12345234, 'apple001', 4096, 8192, 1024, 1500, 18),
(12345352, 'apple002', 512, 2028, 124, 500, 12),
(12378954, 'asus1008', 2048, 4096, 256, 450, 16);

insert into printer (code, model, color, type, price) values 
(1235768, 'hp00451', 'y', 'Laser', 200),    
(5465768, 'hp00011', 'y', 'Jet', 150),
(5465131, 'brother100', 'n', 'Matrix', 80);