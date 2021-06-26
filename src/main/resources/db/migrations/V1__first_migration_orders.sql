CREATE TABLE IF NOT EXISTS `items` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20),
    `quantity` int,
    `price` int
);

CREATE TABLE IF NOT EXISTS `orders` (

    `order_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY

);
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` int unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int unsigned NOT NULL,
   `name` varchar(20),
  `quantity` int,
  `create_date_time` DATETIME,
  `update_date_time` DATETIME,
  PRIMARY KEY (`cart_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
);

insert into items (name, quantity, price) values ('Cookies',100,10);
insert into items (name, quantity, price) values ('Coffee',100,15);
insert into items (name, quantity, price) values ('Pastry',100,50);
insert into items (name, quantity, price) values ('Bun',100,10);