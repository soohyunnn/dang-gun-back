ALTER TABLE user ADD UNIQUE (email);
ALTER TABLE user ADD UNIQUE (username);

ALTER TABLE user ADD addressnumber varchar(5);
ALTER TABLE user change address detailaddress varchar(240);

alter table user modify password varchar(100);
alter table user modify prev_password varchar(100);