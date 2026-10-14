# language: es
@ModificarDatos
Característica: Modificación de datos del usuario registrado
  Como cliente registrada de Bon-bonite
  Quiero actualizar los datos personales de mi cuenta
  Para mantener mi información al día

  Antecedentes:
    Dado que "Sofia" es una cliente registrada que inició sesión en el sitio

  @EscenarioExitoso
  Esquema del escenario: Modificar los datos personales desde la pestaña Datos de Mi cuenta
    Cuando actualiza sus datos personales con nombres "<nombres>", apellidos "<apellidos>" y teléfono "<telefono>"
    Entonces debería ver el mensaje "Datos personales actualizados correctamente"
    Y al consultar de nuevo sus datos deberían aparecer nombres "<nombres>", apellidos "<apellidos>" y teléfono "<telefono>"

    Ejemplos:
      | nombres      | apellidos     | telefono   |
      | Sofia Andrea | Ramirez Gomez | 3001234567 |
