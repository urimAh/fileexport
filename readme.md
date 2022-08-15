# Run Bulk Export files using docker 

To run this application locally you need to import docker image
and run this command #docker run -p 8080:8080 springio/file-export-spring-boot-docker

Steps to create docker image
1- create jar file using command:
./mvnw package && java -jar target/file-export-spring-boot-docker-0.1.0.jar
2- build docker image with command 
docker build -t springio/file-export-spring-boot-docker .

Then you can try exporting some files to zip, by using any tool like Postman ex: