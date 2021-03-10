#
# Build stage
#


FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
RUN echo "WHATS UP"
RUN echo $PORT
#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/ProjectPurpleCow-jar-with-dependencies.jar /usr/local/lib/run.jar
ARG PORT=3000
EXPOSE $PORT

ENTRYPOINT ["java","-jar","/usr/local/lib/run.jar"]