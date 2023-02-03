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
git clone https://github.com/judibec/Taller_1_AREP.git
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
El proyecto

## Patrones
- Singleton
- Servidor fachada

## Modular
- Cliente
- Pruebas
- Servidor

## Pruebas
Se realizaron pruebas haciendo uso de hilos, con el objetivo de verificar el buen funcionamiento y la concurrencia del codigo.

Para ejecutarlas, corremos el siguiente comando:
```
mvn test
```

## Version
1.0
