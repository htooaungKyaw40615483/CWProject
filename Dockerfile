FROM openjdk:latest
COPY ./target/CWProject.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "CWProject.jar", "30000"]

