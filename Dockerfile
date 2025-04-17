FROM eclipse-temurin:21-jdk AS runtime

WORKDIR /app

COPY target/*.jar app.jar

RUN java -version

ENTRYPOINT ["java", "-jar", "app.jar"]
