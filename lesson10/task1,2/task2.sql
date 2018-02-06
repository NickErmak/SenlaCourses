/*task 2.1
select model, speed, hd from pc where price < 500*/
/*task 2.2
select distinct maker from product where type = 'Printer';*/
/*task 2.3
select model, ram, screen from laptop where price > 1000*/
/*task 2.4
SELECT * FROM printer WHERE color = 'y';*/
/*task 2.5
select model, speed, hd from pc where cd = '12x' or cd = '24x' and price < 600;*/
/*task 2.6
select laptop.speed, product.maker from laptop inner join product on product.model = laptop.model where hd >= 10;*/
/*task 2.7
select product.model, price from pc join product on pc.model = product.model
where product.maker like 'B%'
union
select product.model, price from printer join product on printer.model = product.model
where product.maker like 'B%'
union
select product.model, price from laptop join product on laptop.model = product.model
where product.maker like 'B%'*/
/*task 2.8
select maker from product where maker not in 
(select maker from product where type = 'Laptop')
and type = 'pc';*/
/*task 2.9
select distinct product.maker from pc 
join product on pc.model = product.model
where speed > 450*/
/*task 2.10
select model, price as 'max price' from printer 
where price = (select max(price) from printer);*/
/*task 2.11
select avg(speed) from pc*/
/*task 2.12
select avg(speed) from laptop where price > 1000*/
/*task 2.13
select avg(speed) from pc, product where pc.model = product.model and product.maker = 'SAMSUNG'*/
/*task 2.14
select speed, avg(price) from pc group by speed*/
/*task 2.15
select hd from pc 
group by hd 
having count(hd) > 1*/
/*task 2.16
select pc1.model as model1, pc2.model as model2, pc1.speed, pc1.ram from pc as pc1
inner join pc as pc2 on pc1.ram = pc2.ram and pc1.speed = pc2.speed
where pc1.code > pc2.code*/
/*task 2.17
select p.type, l.model, l.speed from laptop l, product p
where l.speed < (select min(speed) from pc)
and l.model = p.model*/
/*task 2.18
select product.maker, min(printer.price) from printer, product
where product.model = printer.model*/
/*task 2.19
select p.maker, avg(l.screen) from product p, laptop l
where p.model = l.model
group by p.maker*/
/*task 2.20
select maker, count(model) from product 
where type = 'pc' 
group by maker
having count(model) > 2*/
/*task 2.21
select maker, max(price) from pc, product 
where pc.model = product.model
group by maker*/
/*task 2.22
select speed, avg(price) from pc
where speed > 600
group by speed*/
/*task 2.23
select distinct maker from product p, pc
where p.model = pc.model
and pc.speed > 750
and p.maker in 
(select distinct maker from product p, laptop l
where p.model = l.model
and l.speed > 750)*/
/*task 2.24
select model from 
(select model, max(price) as price from laptop
union
select model, max(price) as price from pc
union
select model, max(price) as price from printer) as T
having max(price)*/
/*task 2.25
select maker, speed from pc, product
where pc.model = product.model
and maker in
(select maker from product 
where type = 'pc' 

and maker in
(select distinct maker from product
where type = 'printer'))

and ram = (select min(ram) from pc, product
where pc.model = product.model
and maker in
(select maker from product 
where type = 'pc' 
and maker in
(select distinct maker from product
where type = 'printer'))) 

and speed = (select max(speed) from pc, product
where pc.model = product.model
and maker in
(select maker from product 
where type = 'pc' 
and maker in
(select distinct maker from product
where type = 'printer'))
and ram = (select min(ram) from pc, product
where pc.model = product.model
and maker in
(select maker from product 
where type = 'pc' 
and maker in
(select distinct maker from product
where type = 'printer'))))*/