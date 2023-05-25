FROM maven:3.8.1-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-alpine
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java","-jar","/app.jar","--HUMPTY=${HUMPTY}","--DUMMY=${DUMMY}" ]
