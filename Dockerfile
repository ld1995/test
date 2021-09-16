FROM openjdk:11
VOLUME /tmp
COPY target/test-*.jar app.jar
CMD ["java", "-Xmx512M", "-jar", "/app.jar"]

EXPOSE 8099