insert into `users` values("user","{noop}EasyBytes12345","1");
insert into `users` values("abhay","{bcrypt}$2a$12$PrBq7ZU2jbGfdQJY9T9xxuZPEYWTFMyRDbB6SSvdTUog7T82xUOZy","1");

insert into `authorities` values("user","read");
insert into `authorities` values("abhay","admin");

insert into customer (`email`,`pass`,`role`) values("happy@example.com","{noop}EasyBytes12345","read");
insert into customer (`email`,`pass`,`role`) values("admin@example.com","{bcrypt}$2a$12$PrBq7ZU2jbGfdQJY9T9xxuZPEYWTFMyRDbB6SSvdTUog7T82xUOZy","admin");

