




### Docker

#### 1. Database (Mysql)
``
docker run -p 3306:3306 --name recruit-mysql -e MYSQL_ROOT_PASSWORD=forlks -d mysql:latest
``
````
# Database, user 생성
CREATE DATABASE recruit_db  default CHARACTER SET UTF8;

CREATE USER 'recruit'@'localhost' IDENTIFIED BY 'recruit';
CREATE USER 'recruit'@'%' IDENTIFIED BY 'recruit';

GRANT ALL PRIVILEGES ON recruit_db.* TO 'recruit'@'localhost';
GRANT ALL PRIVILEGES ON recruit_db.* TO 'recruit'@'%';

FLUSH PRIVILEGES;
````


