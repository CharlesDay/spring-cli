FROM eclipse-temurin:21-jdk AS runtime

WORKDIR /workspace

# Install wget and tar
RUN microdnf install -y wget tar gzip

# Install Maven manually
RUN wget https://archive.apache.org/dist/maven/maven-3/3.8.7/binaries/apache-maven-3.8.7-bin.tar.gz \
 && tar xzf apache-maven-3.8.7-bin.tar.gz -C /opt \
 && ln -s /opt/apache-maven-3.8.7 /opt/maven \
 && ln -s /opt/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME=/opt/maven
ENV PATH="${MAVEN_HOME}/bin:${PATH}"

COPY . .

WORKDIR /app

COPY target/*.jar app.jar

RUN java -version

ENTRYPOINT ["java", "-jar", "app.jar"]
