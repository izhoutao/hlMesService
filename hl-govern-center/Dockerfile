#FROM java:8
#ENV ARTIFACTID hl-govern-center
#ENV ARTIFACTVERSION 1.0-SNAPSHOT
#ENV HOME_PATH /home
#WORKDIR $HOME_PATH
#ADD /$ARTIFACTID-$ARTIFACTVERSION.jar $HOME_PATH/$ARTIFACTID.jar
#ENTRYPOINT ["java", "-jar", "hl-govern-center.jar"]
FROM maven:3.5.4-alpine
ADD . /app
WORKDIR /app/
RUN mvn clean package
EXPOSE 50101
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar target/hl-govern-center-0.0.1-SNAPSHOT.jar
