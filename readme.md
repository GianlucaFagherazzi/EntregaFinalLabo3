ClienteService maneja la lógica empresarial.
ClienteDAO interactúa con la base de datos.
ClienteDTO controla los datos expuestos al cliente.
Cliente representa el modelo de datos de negocio

cosas pendientes: 
revisar los test unitarios



/cliente
{
    "nombre": "Juan",
    "apellido": "Pérez",
    "dni": 12345678,
    "fechaNacimiento": "1980-10-10",
    "tipoPersona": "F",
    "banco": "Banco Nación"
}

/cuenta
{
    "titularDni": 12345678,
    "tipoCuenta": "CAJA_AHORRO",
    "moneda": "PESOS"
}

/api/prestamo
{
    "numeroCliente": 12345678,
    "plazoMeses": 6,
    "montoPrestamo": 5000,
    "moneda": "PESOS"
}