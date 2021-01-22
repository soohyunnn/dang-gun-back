ALTER TABLE image change imgname filename varchar(20);
ALTER TABLE image change imgpath path varchar(300);
ALTER TABLE image change imgtype type varchar(10);
ALTER TABLE image change order_number sequence int;
ALTER TABLE user change username name varchar(10);