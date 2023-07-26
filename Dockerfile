FROM openjdk:latest
COPY ./target/CWProject.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "CWProject.jar", "db:3306", "30000"]

