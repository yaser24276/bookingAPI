CREATE TABLE `booking`
(
    `bookingId`              LONG(11)     NOT NULL AUTO_INCREMENT,
    `passengerName`          varchar(100) NOT NULL,
    `passengerContactNumber` varchar(50)  NOT NULL,
    `pickupTime`             timestamp,
    `Asap`                   TINYINT(1) default 0,
    `waitingTime`            LONG(20),
    `numberOfPassengers`     INT(2),
    `price`                  DOUBLE       NOT NULL,
    `rating`                 INT(1),
    `createdOn`              timestamp,
    `lastModifiedOn`         timestamp,
    PRIMARY KEY (`bookingId`)
);

CREATE TABLE `tripWayPoint`
(
    `tripWayPointId` LONG(11)     NOT NULL,
    `locality`       varchar(200) NOT NULL,
    `longitude`      DOUBLE       NOT NULL,
    `latitude`       DOUBLE       NOT NULL,
    PRIMARY KEY (`tripWayPointId`)
);

CREATE TABLE `booking_tripWayPoint`
(
    `tripWayPointId` int(11) NOT NULL,
    `bookingId`      int(11) NOT NULL,
    PRIMARY KEY (`bookingId`, `tripWayPointId`),
    CONSTRAINT `booking_tripWayPoint_ibfk_1`
        FOREIGN KEY (`bookingId`) REFERENCES `booking` (`bookingId`),
    CONSTRAINT `booking_tripWayPoint_ibfk_2`
        FOREIGN KEY (`tripWayPointId`) REFERENCES `tripWayPoint` (`tripWayPointId`)
);

