FROM openjdk:11-jre

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Xmx512M", "-Xms256M","-jar","/app.jar"]

EXPOSE 8080 8080