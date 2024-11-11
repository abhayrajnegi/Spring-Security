create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);


create table `customer`
(
    `id` int not null auto_increment,
    `email` varchar(50) not null,
    `pass` varchar(100) not null,
    `role` varchar(50) not null,
    primary key(`id`)
)