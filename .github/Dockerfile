FROM ghcr.io/graalvm/graalvm-ce:latest

ENV APP_HOME "/opt/app"

ADD ./target/account-service-0.0.1-SNAPSHOT.jar $APP_HOME/account-service-0.0.1-SNAPSHOT.jar

CMD	java -Xmx512M -Xms256M -jar /opt/app/account-service-0.0.1-SNAPSHOT.jar