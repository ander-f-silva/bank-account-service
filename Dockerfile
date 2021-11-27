FROM adoptopenjdk:11-jre-hotspot

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Xmx512M", "-Xms256M","-jar", "-Dserver.port=80", "/app.jar"]

EXPOSE 80 80