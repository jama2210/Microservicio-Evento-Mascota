FROM openjdk:21-ea-24-oracle 
WORKDIR /app
COPY target/microservicio_mascota-0.0.1-SNAPSHOT.jar app.jar
COPY bd_prueba_dos /app/bd_prueba_dos
EXPOSE 9090

CMD ["java", "-jar", "app.jar"]

