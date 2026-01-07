# bin-available-fileserver

A simple highly available fileserver


### Build command

mvn clean package -Pnative

### Run 1st server command

java -Dserver.id=1 \   
-Dserver.port=8081 \
-Dserver.replicas=http://localhost:8082 \
-jar target/quarkus-app/quarkus-run.jar

### Run 2nd server command

java -Dserver.id=2 \
-Dserver.port=8082 \
-Dserver.replicas=http://localhost:8081 \
-jar target/quarkus-app/quarkus-run.jar


