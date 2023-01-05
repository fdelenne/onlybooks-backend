FROM maven:3.8.6-openjdk-18 as builder

WORKDIR /usr/app/
COPY . /usr/app
RUN mvn package -Dmaven.test.skip
RUN JAR_FILE="target/*.war"; cp ${JAR_FILE} /app.war

FROM openjdk:18
WORKDIR /usr/app
COPY --from=builder /app.war /usr/app
EXPOSE 8080

CMD ["java","-jar","app.war"]