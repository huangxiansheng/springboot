my.ini有效的情况下

1、安装
mysqld -install
net start mysql

2、移除
net stop mysql
mysqld remove



update user set authentication_string = password('1234abcD!'),password_expired = 'N', password_last_changed = now() where user = 'root';
update user set host='%' where user='root';

flush privileges;



CREATE USER 'hs'@'%' IDENTIFIED BY '1234abcD!';
CREATE DATABASE `hs_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER,CREATE VIEW,REFERENCES,CREATE ROUTINE,EXECUTE, ALTER ROUTINE ON hs_db.* TO 'hs'@'%' IDENTIFIED BY '1234abcD!';