# Foro Hub API - Challenge
![Java](https://img.shields.io/badge/Java-17-orange.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen.svg) ![Gradle](https://img.shields.io/badge/Gradle-8.11.1-blue.svg) ![Docker](https://img.shields.io/badge/Docker-Supported-blue.svg)
## Tabla de Contenidos
1. [Descripción del proyecto](#descripción-del-proyecto)
2. [Estado del Proyecto](#estado-del-proyecto)
3. [Demostración de Funciones y Aplicaciones](#demostración-de-funciones-y-aplicaciones)
4. [Instrucciones de Clonado](#instrucciones-de-clonado)
5. [Tecnologías Utilizadas](#tecnologías-utilizadas)
6. [Arquitectura](#arquitectura)
7. [Personas Desarrolladoras del Proyecto](#personas-desarrolladoras-del-proyecto)
8. [Licencia](#licencia)

### Descripción del proyecto
Foro Hub es una API REST de tipo stateless diseñada para la gestión de topics en un foro. Este proyecto permite administrar los temas creados y sus respuestas, con un enfoque en simplicidad y eficiencia. Está dirigido a desarrolladores y usuarios interesados en soluciones API para foros.

### Estado del Proyecto
El proyecto se encuentra actualmente en desarrollo.
#### Funciones Pendientes:
- Crear réplicas de respuestas de los topics.
- Eliminar respuestas.
- Agregar funcionalidades específicas para el rol de moderador.
- Implementar autenticación mediante OAuth2.

### Demostración de Funciones y Aplicaciones
Puedes probar la API utilizando la colección de Postman proporcionada. Incluye todos los endpoints disponibles para gestionar los topics y sus respuestas.
> [Collection](postman/collection/Foro%20Hub.postman_collection.json)

> [Environment](postman/enviroment/Foro%20Hub%20Dev.postman_environment.json)

### Instrucciones de Clonado
1. Clona el repositorio: 
```bash
git clone https://github.com/ARuizMontana/Foro-Hub-Challenge.git
cd Foro-Hub-Challenge
```
2. Levanta la base de datos MySQL con Docker estableciendo las variables y ejecutando el archivo [docker-compose.yml](docker-compose.yml)
```bash 
docker-compose up -d
```
3. Configura la cadena de conexión con la base de datos
> [application.properties](src/main/resources/application.properties)
4. Configura el secret key para el cifrado del accessToken
> [application-dev.properties](src/main/resources/application-dev.properties)
5. Ejecuta el proyecto
```bash
./gradlew bootRun
```

### Tecnologías Utilizadas
- Java 17
- Spring Boot 3.4.X
- Gradle 8.11.X
- Flyway
- JWT (jsonwebtoken jjwt)
- Lombok
- MySQL (utilizando un contenedor Docker)

### Arquitectura
Este proyecto sigue una arquitectura hexagonal, lo que permite separar las diferentes capas del sistema y promover la independencia de las tecnologías y las interfaces.
![Arquitectura Hexagonal](https://miro.medium.com/v2/resize:fit:720/format:webp/0*hc8Ba-NzzRnBFJoK.png)
### Personas Desarrolladoras del Proyecto
[Anderson Ruiz](https://github.com/ARuizMontana)
### Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.