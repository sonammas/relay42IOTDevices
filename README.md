# relay42IOTDevices

This is a sprint boot application in which I have demostrated that few IOT devices are continuously sending out their data
and we need to store in the database.

Technologies used :
1) Spring boot application - 2.0.2.RELEASE
2) Kafka (as streaming engine)
3) MongoDb (to store the data into and query from)
4) Docker

Steps to run:
The folllowing instructions will show how to run the application:

1) Run docker on Windows 

2) In any terminal cd to the root of the project - iot-producer and run
docker-compose up -d
>> This will up zookeeper and kafka both (you can check via docker ps)

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


