FROM maven:3.6.3-adoptopenjdk-15
WORKDIR /app
COPY . .
COPY pom.xml /tmp/pom.xml
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve