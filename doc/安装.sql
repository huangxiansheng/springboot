
1、把my.ini放入解压后的mysql-5.7.22-winx64下
2、修改my.ini文件中的 #skip_grant_tables 去掉#号
  修改路径 改为当前电脑的路径
  basedir=G:\mysql\mysql-5.7.22-winx64
	datadir=G:\mysql\mysql-5.7.22-winx64\data
3、cmd管理员身份运行，并到目录 mysql-5.7.22-winx64\bin 下
4、执行 mysqld --initialize-insecure --user=mysql
5、执行 mysqld -install 提示 Service successfully installed.
6、执行 net start mysql 提示服务启动成功

7、执行mysql 进入sql命令行
 7.1 使用mysql database
 use mysql; 
 7.2 更新root密码
 update user set authentication_string = password('1234abcD!'),password_expired = 'N', password_last_changed = now() where user = 'root';
 update user set host='%' where user='root';
 flush privileges;
 7.3 创建database及用户
 CREATE USER 'hs'@'%' IDENTIFIED BY '1234abcD!';
 CREATE DATABASE `hs_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
 GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER,CREATE VIEW,REFERENCES,CREATE ROUTINE,EXECUTE, ALTER ROUTINE ON hs_db.* TO 'hs'@'%' IDENTIFIED BY '1234abcD!';
 7.4 exit;

8、停止mysql服务
  net stop mysql
9、修改my.ini
  skip_grant_tables 前面加上#
10、启用mysql服务
  net start mysql
  
11、登录验证
  mysql -uhs -p
  输入密码
  能够登录，则安装成功