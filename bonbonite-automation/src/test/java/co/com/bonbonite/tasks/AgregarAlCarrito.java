package co.com.bonbonite.tasks;

import co.com.bonbonite.interactions.SeleccionarTalla;
import co.com.bonbonite.models.ProductoSeleccionado;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.bonbonite.userinterfaces.CarritoPage.NOMBRES_PRODUCTOS;
import static co.com.bonbonite.userinterfaces.CarritoPage.URL;
import static co.com.bonbonite.userinterfaces.ProductoPage.BOTON_ANADIR_CARRITO;
import static co.com.bonbonite.utils.Constantes.PRODUCTO;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarAlCarrito implements Task {

    private static final long ESPERA_RESPUESTA_CARRITO_MS = 5000;

    public static AgregarAlCarrito conTallaDisponible() {
        return instrumented(AgregarAlCarrito.class);
    }

    @Override
    @Step("{0} agrega el producto seleccionado al carrito con una talla disponible")
    public <T extends Actor> void performAs(T actor) {
        ProductoSeleccionado producto = actor.recall(PRODUCTO);
        actor.attemptsTo(
                SeleccionarTalla.numero(producto.getTalla()),
                WaitUntil.the(BOTON_ANADIR_CARRITO, isEnabled()).forNoMoreThan(10).seconds(),
                Click.on(BOTON_ANADIR_CARRITO)
        );
        // El tema agrega el producto por AJAX; se da tiempo a que el servidor actualice la sesión del carrito
        esperar(ESPERA_RESPUESTA_CARRITO_MS);
        actor.attemptsTo(
                Open.url(URL),
                WaitUntil.the(NOMBRES_PRODUCTOS, isVisible()).forNoMoreThan(30).seconds()
        );
    }

    private static void esperar(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
