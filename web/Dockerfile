FROM openjdk:16
EXPOSE 5000
ADD target/cinema.jar cinema.jar
ENTRYPOINT ["java", "-jar", "--enable-preview", "cinema.jar"]

