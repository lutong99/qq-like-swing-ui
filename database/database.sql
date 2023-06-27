-- 使用qq_project
USE qq_project;

-- 创建qq用户表
DROP TABLE IF EXISTS qq_table;
CREATE TABLE IF NOT EXISTs qq_table (
    qq_id INT PRIMARY KEY AUTO_INCREMENT,
    qq_number INT(5) NOT NULL UNIQUE,
    qq_nickname VARCHAR(12),
    qq_password CHAR(32) NOT NULL,
    qq_photo BLOB,
    qq_gender VARCHAR(4),
    qq_birthday DATE,
    qq_country VARCHAR(20),
    qq_province VARCHAR(10),
    qq_city VARCHAR(10),
    qq_nation VARCHAR(10),
    qq_email VARCHAR(50),
    qq_state CHAR(1) DEFAULT '1',
    qq_register DATETIME
);


-- 创建一个触发器
-- 让我们的用户在插入时, 如果昵称为空, 则把qq号码作为昵称
DROP TRIGGER IF EXISTS proaddqq;
CREATE TRIGGER proaddqq BEFORE INSERT ON qq_table FOR EACH ROW
BEGIN
    IF new.qq_nickname IS NULL THEN SET new.qq_nickname = new.qq_number;
    END IF;
END;

-- 测试
INSERT INTO qq_table (qq_number, qq_password) VALUES(13432, "1231"); 


-- 测试创建表含有外键的
DROP TABLE IF EXISTS qqgame_table;

create table qqgame (
    qq_number int(3) primary key,
    CONSTRAINT qq_game FOREIGN KEY (qq_number) REFERENCES qq_table (qq_number) ON DELETE CASCADE ON UPDATE CASCADE
);
create table number__
(qq_number int(5) primary key, qq_friends int(5), constraint qq_game_ foreign key (qq_number) references qq_table (qq_number) on delete cascade on update cascade,
    CONSTRAINT qq_num FOREIGN KEY (qq_friends) REFERENCES qq_table (qq_number) on DELETE CASCADE on UPDATE CASCADE
);


-- 创建一个测试的cloud 表格来作为我们的测试cloud
create table qq_cloud123456 (
    cloud_id int(3) primary key auto_increment, 
    cloud_name varchar(255), 
    cloud_path varchar(255), 
    cloud_time datetime, 
    cloud_size varchar(30)
);