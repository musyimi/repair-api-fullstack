FROM amazoncorretto:17
VOLUME /tmp
COPY target/repair-api-0.0.1-SNAPSHOT.jar /repair-api.jar
ENTRYPOINT ["java","-jar","/repair-api.jar"]