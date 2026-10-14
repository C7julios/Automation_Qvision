package co.com.bonbonite.stepdefinitions;

import co.com.bonbonite.models.ProductoSeleccionado;
import co.com.bonbonite.tasks.AgregarAlCarrito;
import co.com.bonbonite.tasks.IrAlPago;
import co.com.bonbonite.tasks.SeleccionarProducto;
import co.com.bonbonite.utils.GeneradorDatos;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;

import static co.com.bonbonite.userinterfaces.CarritoPage.*;
import static co.com.bonbonite.userinterfaces.CheckoutPage.*;
import static co.com.bonbonite.utils.Constantes.PRODUCTO;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CompraStepDefinitions {

    @Cuando("selecciona un producto disponible de la categoría {string}")
    public void seleccionaUnProductoDisponible(String categoria) {
        theActorInTheSpotlight().attemptsTo(SeleccionarProducto.disponibleDeLaCategoria(categoria));
    }

    @Cuando("lo agrega al carrito con una talla disponible")
    public void loAgregaAlCarrito() {
        theActorInTheSpotlight().attemptsTo(AgregarAlCarrito.conTallaDisponible());
    }

    @Entonces("el carrito debería contener el producto seleccionado con cantidad {int} y su precio")
    public void elCarritoDeberiaContenerElProducto(int cantidad) {
        Actor actor = theActorInTheSpotlight();
        ProductoSeleccionado producto = actor.recall(PRODUCTO);
        actor.attemptsTo(
                Ensure.that(NOMBRES_PRODUCTOS.resolveAllFor(actor).size()).isEqualTo(1),
                Ensure.that(Text.of(NOMBRES_PRODUCTOS)).containsIgnoringCase(producto.getNombre()),
                Ensure.that(Value.of(CANTIDADES)).isEqualTo(String.valueOf(cantidad)),
                Ensure.that(Text.of(PRECIOS)).isEqualTo(producto.getPrecio())
        );
    }

    @Cuando("continúa al pago y diligencia los datos de facturación y envío")
    public void continuaAlPago() {
        theActorInTheSpotlight().attemptsTo(IrAlPago.yDiligenciarDatos(GeneradorDatos.datosEnvio()));
    }

    @Entonces("el resumen del pedido debería mostrar el producto seleccionado y el total a pagar")
    public void elResumenDelPedidoDeberiaMostrarElProducto() {
        Actor actor = theActorInTheSpotlight();
        ProductoSeleccionado producto = actor.recall(PRODUCTO);
        actor.attemptsTo(
                Ensure.that(Text.of(PRODUCTOS_RESUMEN)).containsIgnoringCase(producto.getNombre()),
                Ensure.that(Text.of(PRODUCTOS_RESUMEN)).contains("Cantidad: 1"),
                Ensure.that(Text.of(TOTAL_PEDIDO)).isEqualTo(producto.getPrecio()),
                Ensure.that(BOTON_REGISTRAR_ORDEN).isDisplayed()
        );
    }
}
