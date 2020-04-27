create table users (
	id UUID not null,
	username varchar(30) not null,
	primary key (id),
	unique (username)
);