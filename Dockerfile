FROM openjdk:11
VOLUME /tmp
ADD target/practiceWithJSOn-1.0-SNAPSHOT.jar practiceWithJSOn-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "practiceWithJSOn-1.0-SNAPSHOT.jar"]