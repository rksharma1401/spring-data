FROM maven:3.8.1-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-alpine
COPY --from=build /home/app/target/*.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java","-jar","/app.jar","--debug","--PASS_KEY=${PASSKEY_ED}" ,"--P_APP_ID={P_APP_ID_ED}" ,"--P_REST_KEY={P_REST_KEY_ED}","--PASS_URL={PASS_URL_ED}"]
