# bookingAPI

## description:
this repo is a simple implementation of the rabbitMQ flow
it receives a booking action add/edit/delete and send those to a dedicated queues
then a consumer module will consume those queues and do actions depending on the route key we send
the consumers is to save/update/delete bookings from the database.

## database used
in memory H2

## prerequisites 
#### a local instance of rabbitMQ running on your local
#### JAVA 11 openJDK
#### docker
#### Maven 3 or above

## How to run it locally 
#### compile the project running 
```mvn clean install```
#### make sure you run the rabbitMQ server you can simply do that by running
```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management```
you can check it by visiting
http://localhost:15672/ with username/password (guest/guest)

#### for Producer run this from your terminal
```mvn spring-boot:run -pl producer```
#### for Consumer open another terminal window and run
``` mvn spring-boot:run -pl consumer```
#### Producer will work on port 8080 and Consumer will work on 8082

#### on the project there is a postman collection you can test the endpoints with
./postmanCollections/booking.postman_collection.json
