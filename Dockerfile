FROM openjdk:11
VOLUME /main-app
EXPOSE 8080
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]