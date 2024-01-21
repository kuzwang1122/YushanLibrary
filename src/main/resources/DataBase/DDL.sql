CREATE DATABASE IF NOT EXISTS library;
USE library;
-- ========如有需要========
DROP TABLE user;
DROP TABLE inventory;
DROP TABLE book;
DROP TABLE borrowingrecord;
-- ========如有需要========
CREATE TABLE user (
  userID int PRIMARY KEY AUTO_INCREMENT,
  phoneNumber char(10),
  passWord varchar(100),
  userName varchar(20),
  registrationTime datetime DEFAULT CURRENT_TIMESTAMP,
  lastLoginTime datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) AUTO_INCREMENT = 10001;

CREATE TABLE book (
  isbn char(13) PRIMARY KEY,
  name VARCHAR(500),
  author VARCHAR(100),
  introduction VARCHAR(500)
) ;

CREATE TABLE inventory (
  inventoryID INT PRIMARY KEY AUTO_INCREMENT,
  isbn char(13),
  storeTime DATETIME DEFAULT CURRENT_TIMESTAMP,
  status boolean,
  FOREIGN KEY (isbn) REFERENCES book(isbn)
) AUTO_INCREMENT = 20001;

CREATE TABLE borrowingrecord (
  userID int not null,
  inventoryID int not null,
  borrowingTime datetime DEFAULT CURRENT_TIMESTAMP,
  returnTime datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (userID) REFERENCES user(userID),
  FOREIGN KEY (inventoryID) REFERENCES inventory(inventoryID)
) ;