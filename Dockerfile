FROM openjdk:11
VOLUME [ "/tmp" ]
ADD  matchEngine-1.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "sh","-c","java -jar /app.jar" ]
