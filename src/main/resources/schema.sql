CREATE TABLE rajparad(
id LONG PRIMARY KEY AUTO_INCREMENT,
description VARCHAR(255),
color VARCHAR(255),
weight DOUBLE ,
quantity int
);


INSERT INTO rajparad ( description, color, weight, quantity) values ( 'car', 'orange', 23.0, 2);

INSERT INTO rajparad ( description, color, weight, quantity) values ( 'truck', 'white', 45.0, 3);

INSERT INTO rajparad ( description, color, weight, quantity) values ( 'bus', 'blue', 54.0, 4);

