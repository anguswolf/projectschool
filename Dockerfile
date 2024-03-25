# Usa l'immagine ufficiale di Java 11 come base
FROM openjdk:17

# Specifica l'etichetta dell'autore
LABEL maintainer="Michael"

# Copia il jar dell'applicazione nella directory
COPY target/projectschool-0.0.1-SNAPSHOT.jar projectschool-0.0.1-SNAPSHOT.jar

# Esponi la porta 8081 per l'applicazione Spring Boot
EXPOSE 8081

# Avvia l'applicazione Spring Boot quando il contenitore viene avviato
ENTRYPOINT ["java", "-jar", "projectschool-0.0.1-SNAPSHOT.jar"]