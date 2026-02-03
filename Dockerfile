# Imagen base con Java 23
FROM eclipse-temurin:23-jdk

# Crear carpeta app y copiar el WAR
WORKDIR /app
COPY war/SceneIt-0.0.1-SNAPSHOT.war app.war

# Exponer el puerto que usa tu app
EXPOSE 8080

# Ejecutar el WAR
ENTRYPOINT ["java","-jar","app.war"]