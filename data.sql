CREATE DATABASE IF NOT EXISTS `flywithus`;


CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `country` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `flight_id` int(11) NOT NULL,
  `order_date` datetime NOT NULL,
  `adult_tickets` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=latin1 

drop table IF EXISTS airports;
CREATE TABLE `airports` (
  `airport_id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `flag` varchar(2083) NOT NULL,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`airport_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 


drop table IF EXISTS flights;
CREATE TABLE `flights` (
  `flight_id` int(5) NOT NULL AUTO_INCREMENT,
  `fromLocation` int(5) NOT NULL,
  `toLocation` int(5) NOT NULL,
  `airlineName` varchar(50) NOT NULL,
  `flightNumber` varchar(50) NOT NULL,
  `totalPrice` decimal(10,0) NOT NULL,
  `baggageinfo` varchar(300) DEFAULT NULL,
  `refundableType` int(1) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `availableAdultTickets` int(3) DEFAULT NULL,
  `fromAirport` varchar(200) NOT NULL,
  `toAirport` varchar(200) NOT NULL,
  PRIMARY KEY (`flight_id`),
  KEY `fk_fromLocation` (`fromLocation`),
  KEY `fk_toLocation` (`toLocation`),
  CONSTRAINT `flights_ibfk_1` FOREIGN KEY (`fromLocation`) REFERENCES `airports` (`airport_id`),
  CONSTRAINT `flights_ibfk_2` FOREIGN KEY (`toLocation`) REFERENCES `airports` (`airport_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=latin1 ;

INSERT INTO `users` VALUES ('aarohib','$2a$10$0mZQNFsvkN0Qrqv..BGdJOIOX1IHlT2OVhr4vc8zJ5KdRw3Qsg/rW','Aarohi Banawalikar','ROLE_USER','India',1),('obanawal','$2a$10$GxJEIci0seUbxhAspFz.OOq82CX9QMpjDCEi9sJG868wpnsvY5zZS','Omkar Banawalikar','ROLE_ADMIN','India',1);
INSERT INTO `airports` VALUES (12,'Rajiv gandhi International Airport, Hyderabad','https://image.flaticon.com/icons/svg/197/197419.svg','India'),(13,'Kempegowda International airport, Bangalore','https://image.flaticon.com/icons/svg/197/197419.svg','India'),(14,'Hyderabad Sindh Airport','https://image.flaticon.com/icons/svg/197/197606.svg','Pakistan');
INSERT INTO `flights` VALUES (100,12,13,'Spicejet','SG-497',2000,'23 kg',1,'2019-08-02','11:30:45','12:30:45','2019-08-02',7,'Rajiv gandhi International Airport, Hyderabad','Kempegowda International airport, Bangalore'),(103,13,12,'Spicejet','SG-489',1500,'23 kg',1,'2019-08-03','19:50:45','19:50:45','2019-08-03',3,'Kempegowda International airport, Bangalore','Rajiv gandhi International Airport, Hyderabad'),(104,12,13,'Spicejet','SG-489',1500,'15kg',1,'2019-08-03','13:50:45','19:50:45','2019-08-03',10,'Rajiv gandhi International Airport, Hyderabad','Kempegowda International airport, Bangalore'),(105,12,13,'Spicejet','SG-489',1500,'15kg',1,'2019-08-02','14:50:45','19:50:45','2019-08-03',13,'Rajiv gandhi International Airport, Hyderabad','Kempegowda International airport, Bangalore'),(106,12,13,'Spicejet','SG-489',1500,'15kg',2,'2019-08-02','14:50:45','19:50:45','2019-08-03',16,'Rajiv gandhi International Airport, Hyderabad','Kempegowda International airport, Bangalore'),(107,12,13,'Spicejet','SG-489',1500,'15kg',2,'2019-08-02','17:50:45','12:50:45','2019-08-03',10,'Rajiv gandhi International Airport, Hyderabad','Kempegowda International airport, Bangalore'),(108,12,13,'Spicejet','SG-489',1500,'15kg',2,'2019-08-02','17:50:45','12:50:45','2019-08-03',10,'Rajiv gandhi International Airport, Hyderabad','Kempegowda International airport, Bangalore'),(109,12,13,'Spicejet','SG-489',1500,'15kg',2,'2019-08-02','11:50:45','12:50:45','2019-08-03',10,'Rajiv gandhi International Airport, Hyderabad','Kempegowda International airport, Bangalore'),(110,13,12,'Spicejet','SG-497',2000,NULL,1,'2019-08-03','11:30:45','12:30:45','2019-08-03',9,'Kempegowda International airport, Bangalore','Rajiv gandhi International Airport, Hyderabad'),(111,13,12,'Spicejet','SG-497',2000,NULL,1,'2019-08-03','12:30:45','01:30:45','2019-08-03',8,'Kempegowda International airport, Bangalore','Rajiv gandhi International Airport, Hyderabad'),(112,13,12,'Spicejet','SG-497',2000,NULL,1,'2019-08-03','12:30:45','01:30:45','2019-08-03',10,'Kempegowda International airport, Bangalore','Rajiv gandhi International Airport, Hyderabad'),(113,13,12,'Spicejet','SG-498',2000,NULL,1,'2019-08-03','12:30:45','01:30:45','2019-08-03',10,'Kempegowda International airport, Bangalore','Rajiv gandhi International Airport, Hyderabad'),(114,13,12,'Spicejet','SG-499',2000,NULL,1,'2019-08-03','12:30:45','01:30:45','2019-08-03',10,'Kempegowda International airport, Bangalore','Rajiv gandhi International Airport, Hyderabad'),(115,13,12,'Spicejet','SG-499',2000,NULL,1,'2019-08-03','12:30:45','01:40:45','2019-08-03',10,'Kempegowda International airport, Bangalore','Rajiv gandhi International Airport, Hyderabad');

