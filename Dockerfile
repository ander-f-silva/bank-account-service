FROM ghcr.io/graalvm/graalvm-ce:latest

ENV APP_HOME "/opt/app"

ADD ./target/bank-account-service-0.0.1-SNAPSHOT.jar $APP_HOME/bank-account-service-0.0.1-SNAPSHOT.jar

CMD	java -Xmx512M -Xms256M -jar /opt/app/bank-account-service-0.0.1-SNAPSHOT.jar