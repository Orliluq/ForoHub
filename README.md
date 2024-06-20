# Foro Hub 👩‍💻

Foro Hub es una aplicación de foro diseñada para facilitar la comunicación y discusión entre usuarios. Esta aplicación permite a los usuarios crear tópicos, responder a los mismos y participar en discusiones.

<p align="center">
    <img src="https://github.com/Orliluq/ForoHub/assets/122529721/952e8461-2eac-4c28-8fd0-bb676e672528"/>
</p>


## Características ⚙️

- Registro y autenticación de usuarios.
- Creación, edición y eliminación de tópicos.
- Respuesta a tópicos existentes.
- Listado de usuarios y tópicos.
- Autenticación mediante JWT.

## Tecnologías utilizadas ⚒️

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- JPA (Java Persistence API)
- H2 Database (para desarrollo y pruebas)
- Postman (para pruebas de API)
- Swagger (para documentación de API)

## Estructura del proyecto 🖥️

- **Entities**: Clases de entidad que representan las tablas de la base de datos.
- **Dto**: Clases de Data Transfer Object utilizadas para transferir datos entre el cliente y el servidor.
- **Repository**: Interfaces que extienden JpaRepository para realizar operaciones CRUD en las entidades.
- **Service**: Clases de servicio que contienen la lógica de negocio.
- **Controller**: Clases de controlador que manejan las solicitudes HTTP.
- **Security**: Clases relacionadas con la configuración de seguridad y la autenticación.

## Instalación 🚧

Clona este repositorio:
```
git clone https://github.com/Orliluq/ForoHub.git
```

Navega al directorio del proyecto:
```
cd ForoHub
```
- Abre el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse).
- Configuración
- Base de datos: MySQL 📈

Este proyecto está configurado para usar una base de datos H2 en memoria por defecto. Puedes cambiar la configuración de la base de datos en el archivo application.properties.

### Swagger 📊
Swagger está configurado para generar documentación de la API automáticamente. Puedes acceder a la interfaz de Swagger en la siguiente URL cuando el servidor esté en funcionamiento:
```
http://localhost:8080/swagger-ui/index.html
```
![swagger.png](hub%2Fswagger.png)

Ejecución
Para ejecutar la aplicación, utiliza el siguiente comando en la raíz del proyecto:
```
mvn spring-boot:run
```
La aplicación estará disponible en `http://localhost:8080`.

Endpoints principales:
- `/login`: Endpoint para autenticación de usuarios. Envía una solicitud POST con un JSON que contiene `username` y `password`.
- `/usuarios`: Endpoint para listar usuarios. Requiere autenticación mediante un token JWT.
- `/topicos`: Endpoint para manejar la creación, actualización y eliminación de tópicos.

### Ejemplos de solicitudes 📑

- Autenticación 🔐

Solicitud:
```
POST http://localhost:8080/login
```
Body:
```
{
    "username": "nombre_usuario",
    "password": "contraseña"
}
```
Respuesta:
```
{
    "token": "jwt_token_generado"
}
```
- Crear un tópico 📝

Solicitud:
```
POST http://localhost:8080/topicos
```
Headers:
```
Authorization: Bearer jwt_token_generado
Content-Type: application/json
```
Body:
```
{
  "titulo": "Título del tópico",
  "mensaje": "Mensaje del tópico",
  "autor": {
    "id": 1,
    "nombre": "Nombre del autor",
    "email": "email@example.com",
    "username": "nombre_usuario",
    "password": "contraseña"
  },
  "curso": {
    "id": 1,
    "nombre": "Nombre del curso",
    "descripcion": "Descripción del curso"
  }
}
```
## Contribuciones ⌨️
Las contribuciones son bienvenidas. Por favor, abre un issue para discutir el cambio que deseas realizar. Siéntete libre de hacer un fork del proyecto y enviar un pull request.

## Licencia 🚀
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
