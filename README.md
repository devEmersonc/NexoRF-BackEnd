# 📦 Picking System - Backend

Backend del sistema de gestión de picking desarrollado con Spring Boot.  
Expone una API REST segura para gestionar productos, inventario, órdenes y el flujo completo de picking dentro de un almacén.

---

## 🚀 Tecnologías utilizadas

- Java 21  
- Spring Boot  
- Spring Security  
- JWT (JSON Web Token)  
- MySQL  
- JPA / Hibernate  
- Maven  
- Dotenv (.env)  

---

## 📚 Descripción

Este backend es el núcleo del sistema y se encarga de toda la lógica de negocio:

- Gestión de productos e inventario  
- Creación y administración de órdenes  
- Flujo de picking paso a paso  
- Actualización automática del estado de órdenes  
- Autenticación y autorización mediante JWT  

---

## 🔐 Autenticación

El sistema utiliza JWT para proteger los endpoints.

Flujo de autenticación:

1. El usuario inicia sesión con sus credenciales  
2. El backend genera un token JWT  
3. El cliente debe enviar el token en cada request protegida  

Header requerido:

```
Authorization: Bearer TU_TOKEN
```

---

## ⚙️ Configuración del proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/lValtiel/NexoRF-BackEnd.git
```

---

### 2. Crear archivo `.env`

Este proyecto utiliza variables de entorno para manejar configuración sensible.

Debes crear un archivo `.env` en la raíz del proyecto con el siguiente contenido:

```
DB_URL=jdbc:mysql://localhost:3306/picking_sys
DB_USERNAME=root
DB_PASSWORD=root
JWT_SECRET_KEY=TU_SECRET_KEY
```

⚠️ Importante:

- El archivo `.env` está ignorado en `.gitignore`  
- Debes crearlo manualmente  
- No subir credenciales reales al repositorio  

---

### 3. Configuración en `application.properties`

El proyecto ya está preparado para leer automáticamente el archivo `.env`:

```
spring.application.name=backend_sys_picking
spring.config.import=optional:file:.env[.properties]

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

JWT_SECRET_KEY=${JWT_SECRET_KEY}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

---

### 4. Ejecutar el proyecto

Puedes ejecutar el proyecto con Maven:

```bash
mvn spring-boot:run
```

O directamente desde tu IDE ejecutando la clase principal.

---

## 🧠 Notas importantes 
- Este backend está diseñado para ser consumido por un frontend en JavaFX  

---

## 🧑‍💻 Autor

Desarrollado por lValtiel.  
Sistema completo de picking con arquitectura separada (backend + frontend).
