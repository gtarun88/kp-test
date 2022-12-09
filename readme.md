#Default setup

1)Need to create below two folders inside Documents folder.<br>
a)InputData <br>
b)ProcessedData

2)Transaction upload and processed file location is in Documents folder , we can change this location as well. <br>

input file location /Documents/InputData/ <br>

output file location /Documents/ProcessedData/ <br>

3)SampleTransactionFile.csv file is exist in project root folder. 

4)To execute application as jar file with arguments servicename inputfilename outputfilename <br>
java -jar target/kp-health-batch-scheduler-0.0.1-SNAPSHOT.jar CommandLineJobRunner com.kp.spring.batch.scheduler.config.SchedulerJobConfiguration processTransaction  inputTransactionFile.csv outputTransactionFile.csv

5)To execute application as mvn command  with arguments servicename inputfilename outputfilename <br>
mvn spring-boot:run -Dspring-boot.run.arguments="processTransaction  inputTransactionFile.csv outputTransactionFile.csv"

6)For batch processing scheduler is scheduled for every 30 minutes to pick the file from input transaction processing location 
and its should be named as batchTransactionFile.csv and once its processed its name will be batchTransactionProcessedFile.csv

7)Local host url and port <br>
http://localhost:8085/

8)Web service details : <br>

a)To get family accumulated deductible ,please use below curl url or request <br>

http://localhost:8085/getFamilyAccumulatedDeductible?policyId=100002  <br>

b)To get individual accumulated deductible ,please use below curl url or request  <br>

http://localhost:8085/getIndividualAccumulatedDeductible?policyId=100082&policyHolderId=1000011 <br>

9)For uploading a file using GUI  <br>

after clicking on http://localhost:8085/upload  <br>

please upload a file . <br>

10)For batch processing execution using scheduler , please put file into /Documents/InputData/ folder with name batchTransactionFile.csv <br>

11)Database used H2 database and ORM JPA . <br>

12)SpringBoot technology is used . <br>

13)SpringBatch is used for batch processing .<br>

14)Rest API is used for web-service calls .<br>

15)For view purpose thymeleaf templates are used .<br>

16)Java programming version 8 is used in this project .<br>

17)Below database tables are used and created for this project .<br>
Default database tables are created using data.sql file , which is exist under resource folder .

Tables :<br>
1)PLAN_DESCRIPTIONS <br>
2)PLAN_COVERAGE <br>
3)POLICY_DATA <br>
4)PROCESSED_TRANSACTION_DETAILS <br>

18)To connect with database please use below url .<br>
http://localhost:8085/h2-console/ <br>

Please use below details to connect with database .<br>

JDBC URL: jdbc:h2:mem:dcbapp  <br>
User Name: sa <br>
Password: password <br>


![img.png](img.png)