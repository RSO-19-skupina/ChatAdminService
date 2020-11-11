FROM adoptopenjdk:14-jre-hotspot

RUN mkdir /app

WORKDIR /app

ADD ./api/target/chatplays-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "chatplays-api-1.0.0-SNAPSHOT.jar"]
#CMD java -jar image-catalog-api-1.0.0-SNAPSHOT.jar