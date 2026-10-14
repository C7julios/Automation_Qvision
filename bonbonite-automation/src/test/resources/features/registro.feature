# language: es
@Registro
Característica: Registro de usuario en el sitio web Bon-bonite
  Como cliente potencial de Bon-bonite
  Quiero registrarme en el sitio web
  Para poder iniciar sesión y comprar productos

  @EscenarioExitoso
  Escenario: Registro exitoso de un nuevo usuario
    Dado que "Laura" es una cliente nueva que ingresa a la sección Mi cuenta
    Cuando se registra con datos válidos y acepta el tratamiento de datos personales
    Entonces debería ver el panel de su cuenta con su nombre
    Y debería tener la opción de cerrar sesión
