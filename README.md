# 🚀 ForoHub API - Alura Challenge

ForoHub es una **API REST** desarrollada con **Spring Boot** para gestionar un foro de discusión.  
Permite registrar tópicos, administrar usuarios y proteger el acceso mediante **autenticación JWT**.

---

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.x**
- **Spring Security** (Autenticación y Autorización)
- **JSON Web Token (JWT)**
- **Spring Data JPA**
- **MySQL / H2 Database**
- **Flyway** (Migraciones de base de datos)
- **Lombok**

---

## 🔐 Seguridad y Autenticación

La API utiliza un esquema de seguridad basado en **tokens JWT**:

1. **Registro/Login**: Envía tus credenciales al endpoint de autenticación.
2. **JWT**: Recibirás un token que deberás incluir en cada solicitud protegida en el header:

```
Authorization: Bearer <tu_token_aqui>
```

## 📌 Endpoints Principales

### 🔑 Autenticación(`/auth`)
| Método | Endpoint    | Descripción |
|--------|-------------|-------------|
| POST   | `/register` | Registro de nuevos usuarios |
| POST   | `/login`    | Autentica un usuario y devuelve el JWT |

---

### 👤 Usuarios (`/usuario`)
| Método | Endpoint       | Descripción |
|--------|----------------|-------------|
| GET    | `/usuario`     | Lista todos los usuarios registrados (**requiere token**) |
| GET    | `/usuario/{id}`| Detalle de un usuario específico (**requiere token**) |
| PUT    | `/usuario/{id}`| Actualiza datos de un usuario (**requiere token**) |
| DELETE | `/usuario/{id}`| Elimina un usuario (**requiere token**) |

---

### 💬 Tópicos (`/topicos`)
| Método | Endpoint       | Descripción                                        |
|--------|----------------|----------------------------------------------------|
| GET    | `/topico`     | Lista todos los temas (**requiere token**)         |
| GET    | `/topico/{id}` | Detalle de un tema específico (**requiere token**) |
| POST   | `/topico`      | Crea un nuevo tema (**requiere token**)            |
| PUT    | `/topico/{id}` | Actualiza un tema (**requiere token**)             |
| DELETE | `/topico/{id}` | Elimina un tema (**requiere token**)               |

---

## 📦 Configuración e Instalación
1️⃣ Clonar el repositorio

```
git clone https://github.com/manuel018/forohub.git
```

2️⃣ Configurar variables de entorno

En application.properties define tus credenciales de MySQL

```
spring.datasource.url=jdbc:mysql://localhost:3306/tubd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

3️⃣ Compilar y ejecutar
```
mvn clean compile
mvn spring-boot:run
```

## 📝 Ejemplo de Respuesta (ApiResponse)

Todos los endpoints devuelven un formato estandarizado para facilitar la integración con el frontend (Angular/React):

```json
{
  "data": null,
  "message": "Tópico eliminado exitosamente",
  "success": true
}
```

## 📖 Notas

    La base de datos puede configurarse en MySQL

    El proyecto sigue buenas prácticas de arquitectura y seguridad con Spring Security + JWT.

## 👨‍💻 Autor

Proyecto desarrollado como parte del Alura Challenge - ForoHub.