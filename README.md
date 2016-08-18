# AOP_ExHandler
AOP , AspectJ and Exception Handler


Sample URLs:

1) http://localhost:8080/device/partofmap/16/23 

2) http://localhost:8080/device/findFromDate/2020-09-04T05:08:56.235-0400 

3) http://localhost:8080/device/findById/57b4fd6a9223d17342873882 

4) http://localhost:8080/device/listwithname/Device4 

5) http://localhost:8080/device/listwithname/evice4  - To see the Handled Exception.

POST case: Through Desktop postman: To Save : 
1. URL : http://localhost:8080/device/ 

2. In Request Header, Set Key "Content-Type" to value "application/json". 

3. In Request Body, send something like below: 

        { "deviceName" : "Device18", 
        "eventType" : "Automated type", 
        "eventDate" : "2010-09-04T05:08:56.235-0400", 
        "eventCount" : "30" }

4.Response would be something like : 

        { "deviceId": "57b5bbd49223d1739b7c8bc0", 
        "deviceName": "Device18", 
        "eventType": "Automated type", 
        "eventCount": 30, 
        "eventDate": 1283591336235 } 
