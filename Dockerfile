FROM openjdk:latest
COPY ./target/CWProject-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "CWProject-0.1.0.3-jar-with-dependencies.jar"]