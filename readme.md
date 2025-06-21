# TP Final Gianluca Fagherazzi

## Instalacion
Para ejecutar este proyecto, es necesario tener instalado JDK de java y Maven. Una vez que se tengan estos requisitos, se puede ejecutar el siguiente comando en la terminal:
- mvn clean install
Esto descargará todas las dependencias necesarias y compilará el proyecto.

## Ejecucion
Para ejecutar el proyecto, se puede ejecutar el siguiente comando en la terminal:
- mvn spring-boot:run
Esto iniciará el servidor de Spring Boot y el proyecto estará disponible en http://localhost:8080.

Para ejecutar las pruebas unitarias, se puede ejecutar el siguiente comando en la terminal:
- mvn clean test

## MENU
- [Introduccion](#Introduccion)
- [Ejemplos](#Ejemplos)
- [Endpoints](#Endpoints)
- [Funcionamiento](#Funcionamiento)
- [Pom.xml](#Pom.xml)

## Introduccion
Este proyecto es un servicio RESTfull desarrollado en Java utilizando Spring Boot. Se trata de una aplicación que permite simular ciertas operaciones de un banco, como la creación de clientes, cuentas y préstamos.


## Ejemplos
### Cliente
{
    "nombre": "Frodo",
    "apellido": "Bolson",
    "dni": 12345678,
    "fechaNacimiento": "1980-10-10",
    "tipoPersona": "F",
    "banco": "Banco nacional de hobbiton"
}

### Cuenta
{
    "titularDni": 12345678,
    "tipoCuenta": "CAJA_AHORRO",
    "moneda": "PESOS"
}

### Prestamo
{
    "numeroCliente": 12345678,
    "plazoMeses": 6,
    "montoPrestamo": 5000,
    "moneda": "PESOS"
}


## Endpoints
Cliente: 
* GET /cliente - Obtiene la lista de todos los clientes.
* GET /cliente/{dni} - Obtiene todas las cuentas de un cliente específico.
* POST /cliente - Crea un nuevo cliente.

Cuenta:
* POST /cuenta - Crea una nueva cuenta.

Prestamo:
* GET api/prestamo/{clienteDni} - Obtiene todos los préstamos de un cliente específico.
* POST api/prestamo - Crea un nuevo préstamo.


## Funcionamiento  
    Controller:
        - Contiene los métodos que se encargan de manejar las solicitudes HTTP y de interactuar con los servicios y repositorios correspondientes.

    Model:
        - Define las clases que representan los objetos de negocio y sus relaciones.

    Persistence:
        - Contiene las clases que se encargan de interactuar con la base de datos, la cual esta simulada en memoria.

    Service:
        - Contiene la lógica de negocio.
        - En este caso, se utiliza para validar los datos de entrada y realizar operaciones de negocio.

    EntidadesDao:
        - Define las interfaces que se encargan de interactuar con la base de datos.

    EntidadesDto
        - Define las clases que se encargan de mapear los datos que conectan al cliente con la base de datos.


## Pom.xml
### Plugins
maven-compiler-plugin
* Configura la versión del compilador de Java.

spring-boot-maven-plugin
* Proporciona soporte para empaquetar tu aplicación como un archivo ejecutable (JAR o WAR) y simplifica su ejecución

jacoco-maven-plugin
* Genera reportes de cobertura de código. Evalúa cuánto de tu código es cubierto por los tests.

maven-clean-plugin
*  Elimina archivos generados en el proceso de construcción (target/) para empezar desde cero

maven-surefire-plugin
* Ejecuta las pruebas unitarias durante la fase de test del ciclo de vida de Maven.
