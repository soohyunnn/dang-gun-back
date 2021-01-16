ALTER TABLE post modify column modified_at datetime;
ALTER TABLE user modify column modified_at datetime;
ALTER TABLE image modify column modified_at datetime;

alter table image modify imgpath varchar(300);