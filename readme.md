## Autor:
### Sergio Andrés Otero Herrera

# Taller 1 AREP
En este taller se hace la creacion de una API sin uso de librerias, con el fin de entender el funcionamiento de las mismas.

## Funcionamiento
Se construyo esta aplicacion con el objetivo de consultar la información de películas de cine. La aplicación recibe una frase de búsqueda del título, por ejemplo, “Jhon Wick”. Con esto muestra la toda la informacion encontrada de la película correspondiente dentro de la API gratuita de https://www.omdbapi.com/.

## Prerrequisitos
- GIT
- JAVA
- MVN

## Instalación
De querer usar este codigo, se tiene que hacer lo siguiente:

Se clona el repositorio

```
git clone https://github.com/Sergi0tero/Arep_taller1.git
```

Ahora, si queremos verificar la integridad del codigo

```
mvn package
```
## Correr el código:
Para correr la clase main, la cual se encuentra en HTTPServer, corremos el siguiente comando en la terminal:

```
mvn exec:java
```

## Diseño
El proyecto fue realizado en Java. El ciclo de vida empieza por el usuario, quien da el nombre de la pelicula que quiere buscar, esto lo obtenemos por medio del servidor HTTP. Este servidor procesa los datos y nos da el titulo de la pelicula ingresada por el usuario, manda una peticion al cache. Dentro del Caché se verifica si esta información ya esta guardada en la memoria local. Si este no es el caso, se hace la peticion a la clase de HTTPConnection. En este cliente, se hace la conexión a la API externa de www.omdbapi.com

![image](https://user-images.githubusercontent.com/98189066/216498258-ad302cb5-6ae8-4a1e-9629-8156276fcab5.png)

## Patrones
- Singleton
- Servidor fachada

## Modular
Estas son las diferentes capaz que podemos ver:
- Conexion
- Servidor
- Caché.

El cliente HTTPConnector se conecta con el cliente y con la API externa para brindarle la informacion necesaria al usuario. Le envia esta informacion al servidor HTTP, quien es el que se ocupa de mostrar al usuario la informacion y de recibirla. Por ultimo, tenemos el caché. El caché nos mantiene la informacion temporalmente en la maquina local, evitando asi peticiones extra a la API externa, evadiendo tiempos prolongados innecesarios de carga.

## Pruebas
Se realizaron pruebas haciendo uso de hilos, con el objetivo de verificar el buen funcionamiento y la concurrencia del codigo.

Para ejecutarlas, corremos el siguiente comando:
```
mvn test
```

## Version
1.0
