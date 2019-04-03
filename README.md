## vt Assignment


###Technologies and tools used:
Spring, SpringBoot, Hibernate, SQL, Java 8, H2 embedded DB, Junit, Maven, Eclipse










###Confguration (based on eclipse IDE):
1. Download sourcecode from git
2. Import as maven project in eclipse (or any other IDE, command line is also fine)
3. Update project dependcies through maven
4. Build project 
5. Select VtApplication.java and right click and select run as - Java Application
6. Use http://localhost:8080/upload.html to upload CSV data in H2 embedded DB.
7. Once data is loaded, Try other APIs.

Note: Application should be running when trying APIs through curl or browser.










###H2 embedded DB URL to check loaded data: 
http://localhost:8080/console
Username: test
Password (no password set):




###API data
###API- A
http://localhost:8080/port/2/time/2015-02-02 05:08:16.457
curl http://localhost:8080/port/2/time/2015-02-02 05:08:16.457

Sample Query-
select v.*, pv.time_started from port_vessel pv, vessel v where pv.port_id=2 and pv.time_started<='2015-02-02 05:08:16.457' and pv.time_finished>='2015-02-02 05:08:16.457' and pv.imo=v.imo

Sample Output-
[  
   {  
      "name":"COSCO SPAIN",
      "imo":9516442,
      "length":366.0,
      "timeStarted":"2015-01-30T19:08:20.724+0000"
   },
   {  
      "name":"MAERSK ELBA",
      "imo":9458078,
      "length":366.0,
      "timeStarted":"2015-02-01T09:39:28.963+0000"
   },
   {  
      "name":"OOCL BERLIN",
      "imo":9622605,
      "length":366.0,
      "timeStarted":"2015-01-31T14:47:18.670+0000"
   },
   {  
      "name":"CMA CGM THALASSA",
      "imo":9356294,
      "length":347.0,
      "timeStarted":"2015-02-01T18:26:42.858+0000"
   },
   {  
      "name":"CAP SAN NICOLAS",
      "imo":9622203,
      "length":333.0,
      "timeStarted":"2015-02-01T23:38:16.457+0000"
   },
   {  
      "name":"EVER LOTUS",
      "imo":9604122,
      "length":335.0,
      "timeStarted":"2015-02-01T09:17:46.624+0000"
   }
]










###API- B
http://localhost:8080/port/2/start/2015-02-02%2005:08:16.457/end/2015-09-14%2023:06:21.625
curl http://localhost:8080/port/2/start/2015-02-02%2005:08:16.457/end/2015-09-14%2023:06:21.625

Sample Query-
select * from port_vessel where port_id=2 and time_started>='2015-02-02 05:08:16.457' and time_finished<='2015-09-14 23:06:21.625' order by time_started

Sample Output-
{  
   "uniqueVessels":[  
      9633939,
      9294991,
      9502867,
      9501332,
      9337482,
      9302152,
      9633941,
      9461398,
      9472153,
      9525912,
      9484443,
      9606302,
      9630365,
      9318046,
      9469572,
      9461893,
      9484936,
      9302164,
      9472139,
      9472141,
      9306287,
      9472177,
      9484467,
      9226920,
      9410727,
      9501368,
      9622203,
      9684665,
      9501370,
      9502908,
      9472189,
      9502910,
      9484479,
      9501344,
      9684641,
      9525924,
      9472165,
      9704609,
      9704611,
      9606314,
      9410741,
      9501356,
      9684653,
      9566382,
      9312975,
      9622227,
      9631955,
      9410765,
      9684689,
      9613018,
      9461465,
      9631967,
      9622239,
      9613020,
      9410753,
      9484481,
      9713349,
      9473731,
      9436379,
      9312987,
      9622215,
      9399002,
      9684677,
      9612997,
      9613006,
      9702132,
      9502960,
      9461489,
      9708784,
      9706736,
      9410791,
      9706748,
      9708796,
      9410789,
      9706750,
      9463035,
      9631993,
      9502972,
      9622241,
      9502946,
      9320697,
      9631979,
      9343730,
      9343728,
      9631981,
      9622253,
      9502958,
      9312781,
      9321483,
      9604122,
      9356294,
      9467419,
      9467392,
      9321500,
      9312793,
      9321495,
      9356311,
      9356309,
      9229843,
      9467407,
      9306158,
      9312808,
      9321512,
      9501239,
      9665592,
      9622590,
      9622588,
      9306172,
      9604134,
      9467433,
      9321524,
      9595436,
      9665619,
      9321548,
      9665621,
      9667162,
      9475674,
      9622617,
      9321536,
      9275971,
      9302621,
      9467457,
      9299551,
      9307229,
      9665607,
      9302619,
      9457737,
      9306196,
      9667150,
      9622605,
      9085546,
      9667186,
      9475698,
      9302633,
      9461879,
      9475703,
      9461881,
      9525883,
      9398371,
      9286243,
      9461374,
      9472127,
      9525857,
      9302140,
      9665633,
      9622631,
      9667174,
      9475686,
      9622629,
      9302138,
      9595498,
      9461867,
      9525869,
      9665645,
      9337456,
      9695121,
      9703318,
      9450387,
      9468308,
      9289099,
      9467287,
      9465241,
      9629081,
      9516442,
      9695133,
      9516416,
      9289116,
      9466245,
      9629067,
      9637258,
      9467275,
      9516428,
      9706889,
      9637260,
      9516430,
      9314222,
      9471408,
      9465265,
      9632179,
      9462706,
      9516466,
      9399210,
      9107887,
      9695157,
      9295268,
      9465277,
      9339296,
      9462718,
      9516478,
      9467299,
      9314234,
      9516454,
      9629093,
      9399222,
      9695145,
      9567661,
      9395147,
      9465306,
      9447902,
      9462720,
      9695169,
      9301471,
      9395159,
      9465291,
      9462732,
      9314258,
      9466867,
      9305582,
      9503732,
      9301483,
      9453559,
      9465318,
      9305594,
      9632026,
      9400069,
      9632002,
      9401116,
      9702144,
      9708801,
      9309461,
      9632014,
      9401104,
      9290543,
      9708851,
      9454395,
      9461051,
      9321249,
      9708837,
      9632038,
      9355575,
      9632040,
      9685334,
      9454424,
      9395525,
      9299783,
      9627992,
      9454400,
      9308510,
      9627978,
      9685322,
      9408853,
      9215830,
      9454412,
      9627980,
      9454448,
      9637234,
      9454450,
      9588081,
      9467251,
      9516404,
      9637246,
      9467263,
      9685346,
      9628001,
      9645920,
      9454436,
      9637222,
      9685358
   ],
   "averageDockingTime":134842657,
   "minimumDockingTime":{  
      "imo":9107887,
      "vesselDockingTime":52569782
   },
   "maximumDockingTime":{  
      "imo":9631979,
      "vesselDockingTime":328395109
   }
}










###API- C
http://localhost:8080/vessel/9622203/port/2/start/2015-02-02 05:08:16.457/end/2015-09-14 23:06:21.625
curl http://localhost:8080/vessel/9622203/port/2/start/2015-02-02 05:08:16.457/end/2015-09-14 23:06:21.625

Sample Query-
select time_started, time_finished from port_vessel where imo=9622203 and port_id=2 and time_started>='2015-02-02 05:08:16.457' and time_finished<='2015-09-14 23:06:21.625' order by time_started

Sample Output-
{  
   "portVisitCount":5,
   "averageDockingTime":174155862,
   "minimumDockingTime":148554437,
   "maximumDockingTime":227067436,
   "earliestPortVisitTime":"2015-02-01T23:38:16.457+0000",
   "latestPortVisitTime":"2015-09-12T18:42:34.503+0000"
}










###API- D
http://localhost:8080/port/2/year/2015/month/05
curl http://localhost:8080/port/2/year/2015/month/05

Sample Query-
select v.imo, v.length, pv.time_started, pv.time_finished from port_vessel pv, vessel v where pv.port_id=2 and pv.imo=v.imo and pv.time_started>='2015-2-1 00:00:00.000' and pv.time_finished>='2015-02-28 12:00:00.000'

Sample Output-
{  
   "totalArrivedVesselCount":75,
   "uniqueArrivedVesselCount":75,
   "averageVesselDockingTime":142229288,
   "totalArrivedVesselLength":26782.0
}










###Test case:
VtApplicationTests.java
	Unit testcase: test_HTMLPage, test_GetPortMonthlyTrafficEndpoint
	Integration test: test_DataLoad_GetPortMonthlyTrafficEndpoint

