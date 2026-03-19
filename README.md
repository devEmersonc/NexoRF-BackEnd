# 📦 Picking System - Backend

Backend del sistema de gestión de picking desarrollado con Spring Boot.  
Expone una API REST segura para gestionar productos, inventario, órdenes y el flujo de picking.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot (4.0.1)
- Spring Security
- JWT (JSON Web Token)
- MySQL
- JPA / Hibernate
- Maven
- Dotenv (.env)

---

## 📚 Descripción

Este backend maneja toda la lógica del sistema:

- Gestión de productos e inventario
- Creación y administración de órdenes
- Flujo de picking
- Actualización de estado de órdenes
- Autenticación y autorización con JWT

---

## 🔐 Autenticación

El sistema utiliza JWT:

1. Usuario inicia sesión
2. El backend genera un token JWT
3. El cliente debe enviar el token en cada request

Header requerido:

Authorization: Bearer TU_TOKEN


---

## ⚙️ Configuración del proyecto

### 1. Clonar repositorio

### 2. Crear archivo .env

Este proyecto utiliza variables de entorno para configuración sensible.

Crear un archivo .env en la raíz del proyecto:

DB_URL=jdbc:mysql://localhost:3306/picking_sys
DB_USERNAME=root
DB_PASSWORD=root
JWT_SECRET_KEY=TU_SECRET_KEY

⚠️ Importante:

El archivo .env está ignorado en .gitignore debes crearlo manualmente

### 3. Configuración en application.properties

El proyecto ya está preparado para leer el .env:

spring.config.import=optional:file:.env[.properties]

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

JWT_SECRET_KEY=${JWT_SECRET_KEY}

spring.jpa.hibernate.ddl-auto=update

### 4. Ejecutar el proyecto

mvn spring-boot:run
