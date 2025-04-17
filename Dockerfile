FROM eclipse-temurin:21-jdk AS runtime

RUN mvn clean package -Dmaven.test.skip=true

WORKDIR /app

COPY target/*.jar app.jar

RUN java -version

ENTRYPOINT ["java", "-jar", "app.jar"]
