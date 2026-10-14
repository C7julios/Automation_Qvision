# language: es
@Compra
Característica: Compra de productos en el sitio web Bon-bonite
  Como cliente registrada de Bon-bonite
  Quiero agregar productos al carrito y llegar al pago
  Para comprar los productos que necesito

  Antecedentes:
    Dado que "Valentina" es una cliente registrada que inició sesión en el sitio

  @EscenarioExitoso
  Escenario: Compra de un producto de la categoría Zapatos hasta la confirmación del pedido
    Cuando selecciona un producto disponible de la categoría "Zapatos"
    Y lo agrega al carrito con una talla disponible
    Entonces el carrito debería contener el producto seleccionado con cantidad 1 y su precio
    Cuando continúa al pago y diligencia los datos de facturación y envío
    Entonces el resumen del pedido debería mostrar el producto seleccionado y el total a pagar
