FROM adoptopenjdk/openjdk16:ubi
COPY target/Report-0.0.1-SNAPSHOT.jar Report.jar
COPY src/main/resources/triggers Report/src/main/resources/triggers
ENTRYPOINT ["java","-jar","/Report.jar"]