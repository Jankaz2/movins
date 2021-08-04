FROM openjdk:16
EXPOSE 5000
ADD target/movins.jar movins.jar
ENTRYPOINT ["java", "-jar", "--enable-preview", "movins.jar"]

