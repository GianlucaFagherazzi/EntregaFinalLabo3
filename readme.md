ClienteService maneja la lógica empresarial.
ClienteDAO interactúa con la base de datos.
ClienteDTO controla los datos expuestos al cliente.
Cliente representa el modelo de datos de negocio

# TP Final Gianluca Fagherazzi


## MENU
- [Introduccion](#Introduccion)
- [Ejemplos](#Ejemplos)
- [Endpoints](#Endpoints)
- [Funcionamiento](#Funcionamiento)
- [Pom.xml](#Pom.xml)

## Introduccion
Este proyecto es un servicio RESTful desarrollado en Java utilizando Spring Boot. Se trata de una aplicación que permite simular ciertas operaciones de un banco, como la creación de clientes, cuentas y préstamos.


## Ejemplos
### Cliente
{
    "nombre": "Juan",
    "apellido": "Pérez",
    "dni": 12345678,
    "fechaNacimiento": "1980-10-10",
    "tipoPersona": "F",
    "banco": "Banco Nación"
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



## Pom.xml


