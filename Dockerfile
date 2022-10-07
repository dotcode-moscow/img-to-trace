FROM openjdk

# Refer to Maven build -> finalName
ARG JAR_FILE=out/artifacts/ImageConverter_jar/ImageConverter.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar", "-Xms2g", "-Xmx2g", "app.jar"]
