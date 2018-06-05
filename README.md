# relay42IOTDevices

This is a sprint boot application in which I have demostrated that few IOT devices are continuously sending out their data
and we need to store in the database.

Technologies used :
1) Spring boot application - 2.0.2.RELEASE
2) Kafka (as streaming engine)
3) MongoDb (to store the data into and query from)
4) Docker

Steps to run:
The following instructions will show how to run the application:

Clone this project onto your local system

1) Run docker on Windows 

2) In any terminal cd to the root of the project - iot-producer and run
docker-compose up -d
This will up zookeeper and kafka both (you can check via docker ps)

3) Start the iot-producer -> which will produce the iot data 
cd iot-producer
/gradlew bootRun

4) Start the iot-consumer -> which will consume the data and eventually save into the database
cd iot-consumer
/gradlew bootrun

5) At this point of time, you can see that the data which the schedular is producing 
(com.mas.sonam.iotproducer.iotproducer.scheduledTasks.ScheduledClass.java)
is getting consumed at  iot-consumer side.
Check logs >>
2018-06-05 19:20:16.480  INFO 13100 --- [ntainer#0-0-C-1] c.m.s.i.i.consumer.IotMessageConsumer    : Processing topic = iotdevice, partition = 0, offset = 22, iotDevice = IotDevice{id=null, deviceName=thermostat, value=9, date=Tue Jun 05 19:20:16 CEST 2018}
2018-06-05 19:20:16.482  INFO 13100 --- [ntainer#0-0-C-1] c.m.s.i.i.s.IotSaveKafkaMessageService   : IotDeviceData is saved into the database = 931c8e3a-d5f6-4106-94d0-71f8cd553064

6) Query maximum reading for one device over a period of time
Use postman to hit this URL as GET operation
http://localhost:8897/iotdevice/thermostat/operation/max?from=2018-06-05&to=2018-06-06

7) Query minimum reading for one device over a period of time
Use postman to hit this URL as GET operation
http://localhost:8897/iotdevice/thermostat/operation/min?from=2018-06-05&to=2018-06-06
   
8) Query average reading for one device over a period of time
Use postman to hit this URL as GET operation
http://localhost:8897/iotdevice/thermostat/operation/average?from=2018-06-05&to=2018-06-06

9) Query maximum reading for list of devices over a period of time
Use postman to hit this URL as GET operation
http://localhost:8897/iotdevice/operation/max?list=thermostat&list=carFuel&from=2018-06-05&to=2018-06-06

etc etc.

Note: Used Apache kafka as data streaming technology as you can dump as much data as possible. It is highly scalable,
and works really well when there is massive flow of data from multiple clients and routing it to the systems that needs that data.

Also kafka is very good for performing computations on the stored data like filtering, aggregating etc.

Also configured MongoDB as a kafka consumer. Again very scalable and data can be stored as BSON documents easily and also very
easy to query from.

Though I have learnt about MongoDB during this assignment only ;) 

I had one more idea - we can completely get rid of mongoDb and use KSQL to query directly on kafka as use kafka as data source .
And I dont feel it is wrong as in general terms iot device data nobody wants to store for years in database.
So we can aggregate the data from one kafka topic and stream the data to other kafka topic.
