
CREATE TABLE `airports` (
  `airport_id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `flag` varchar(2083) NOT NULL,
  `country` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`airport_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 



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