FROM openjdk

WORKDIR /app

COPY target/mercadolivre-0.0.1-SNAPSHOT.jar /app/mercadolivre.jar

ENTRYPOINT ["java", "-jar", "mercadolivre.jar"]
