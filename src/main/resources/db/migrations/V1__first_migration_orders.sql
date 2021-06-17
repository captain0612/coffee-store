CREATE TABLE IF NOT EXISTS `items` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20),
    `quantity` int,
    `price` int
);

insert into items (name, quantity, price) values ('Cookies',100,10);
insert into items (name, quantity, price) values ('Coffee',100,15);
insert into items (name, quantity, price) values ('Pastry',100,50);
insert into items (name, quantity, price) values ('Bun',100,10);