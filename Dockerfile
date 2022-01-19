FROM openjdk:alpine-jre
VOLUME [ "/tmp" ]
ADD  matchEngine-1.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "sh","-c","java -jar -Dspring.profiles.active=jenkins app.jar" ]
