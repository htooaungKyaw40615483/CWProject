FROM openjdk:latest
COPY ./target/* /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "CWProject.jar", "db:3306", "30000"]

