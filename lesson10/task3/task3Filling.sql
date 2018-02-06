use libraryErmak;

insert into book (title, publicationDate, price, description, onStock) values 
('1984', '2016-12-20', 10, 'desc2', true),
('game of thrones', '2014-12-20', 20, 'song of ice and fire', false);

insert into orders (name, date, totalAmount, status) values
('order1', '2018-01-01', 10, 'completed');

insert into orderBook (bookId, orderId) values
(1, 1)
