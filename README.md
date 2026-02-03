# SceneIt - Docker Deployment

Este repositorio permite levantar la aplicación **SceneIt** y su base de datos MySQL usando **Docker** y **Docker Compose**.

---

## ⚙️ Requisitos previos

- [Docker](https://docs.docker.com/get-docker/)  
- [Docker Compose](https://docs.docker.com/compose/install/)

---

## 🚀 Levantar Docker

1. Clona el repositorio:

   Desde el bash/cmd

    - git clone https://github.com/Estebanez2/SceneIt.git

2. Construye y levanta los contenedores:

   Desde el bash/cmd estando en la carpeta del repositorio
      - docker compose up --build

⚠️ Si MySQL ya está usando el puerto 3306 en tu máquina, cambia el puerto externo en docker-compose.yml.

---

## 🌐 Acceso a la aplicación

URL: http://localhost:8080
Credenciales de administrador para la web:
  - Usuario: admin
  - Contraseña: 1234
