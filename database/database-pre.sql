-- 创建数据库
create database qqproject;

-- 创建一个为本项目使用的用户
CREATE USER 'qqproject'@'localhost' IDENTIFIED BY 'qqproject';

-- 将我们的这个项目数据库的所有权限给这个用户
GRANT ALL ON qqproject.* TO 'qqproject'@'localhost';

-- 需要用到管理员权限
CREATE TRIGGER proaddqq BEFORE INSERT ON qq_table FOR EACH ROW
BEGIN
    IF new.qq_nickname IS NULL THEN SET new.qq_nickname = new.qq_number;
    END IF;
END;