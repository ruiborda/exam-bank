# Etapa de compilación
FROM maven:3-amazoncorretto-21 AS build
WORKDIR /app

# Copia los archivos de dependencias y de código fuente
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn install -DskipTests

# Etapa de ejecución
FROM amazoncorretto:21
VOLUME /tmp

# Copia el JAR compilado desde la etapa de construcción
COPY --from=build /app/target/account-movement-service-0.0.1-SNAPSHOT.jar app.jar

# Ejecuta la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8183