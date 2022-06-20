FROM openjdk:18
LABEL maintainer="javaguides.net"
ADD target/demo-utilizador-0.0.1-SNAPSHOT.jar Demoutilizador.jar
ENTRYPOINT ["java",  "-Dspring.profiles.active=${ENV}", "-jar", "Demoutilizador.jar"]