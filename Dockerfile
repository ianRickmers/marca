FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
VOLUME /uploads
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]