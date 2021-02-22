
CREATE USER 'food'@'localhost' IDENTIFIED BY 'food';

GRANT ALL PRIVILEGES ON * . * TO 'food'@'localhost';

#
# Starting with MySQL 8.0.4, the MySQL team changed the 
# default authentication plugin for MySQL server 
# from mysql_native_password to caching_sha2_password.
#
# The command below will make the appropriate updates for your user account.
#
# See the MySQL Reference Manual for details: 
# https://dev.mysql.com/doc/refman/8.0/en/caching-sha2-pluggable-authentication.html
#
ALTER USER 'food'@'localhost' IDENTIFIED WITH mysql_native_password BY 'food';


-- -----------------------------------------------------
-- Schema full-menu
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `full-menu`;

CREATE SCHEMA `full-menu`;
USE `full-menu` ;

-- -----------------------------------------------------
-- Table `full-menu`.`recipes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-menu`.`recipes` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `food_type` VARCHAR(10) DEFAULT NULL,
  `head_intake` INT(11) DEFAULT NULL,
   `date_created` DATETIME(6) DEFAULT NULL,
  `instructions` VARCHAR(500) DEFAULT NULL,
  `ingredients` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;


INSERT INTO recipes (`name`,`description`,`food_type`,`head_intake`,`date_created`,`instructions`,`ingredients`) VALUES ('Pani poori','Indian snack','veg',1,'21-02-2021 19:37','Boil Patato. Mesh it. Add salt chilli and masala.Take puris add mesh potato into it. Serve with flavoured pani.','Potatao.Water.Salt.Masala.puri.flavoured water');
INSERT INTO recipes (`name`,`description`,`food_type`,`head_intake`,`date_created`,`instructions`,`ingredients`) VALUES ('Dum Aloo','A north Indian dish','veg',5,'20-02-2021 17:15','Take potato.Boil it. Crush it and mix it with red chilli, salt and garam masala.cook it on light flame for 15 minutes after adding water','potato.water.red chilli.salt.garam masala');
INSERT INTO recipes (`name`,`description`,`food_type`,`head_intake`,`date_created`,`instructions`,`ingredients`) VALUES ('Idli','Soth Indian Breakfast','veg',1,'21-02-2021 21:02','Take Rice. Blend it with water until thick batter is ready. Steam the batter and serve it with coconut chatni','Rice.Coconut.Dhaniya.Water');
