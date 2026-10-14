package co.com.bonbonite.tasks;

import co.com.bonbonite.interactions.ClickJs;
import co.com.bonbonite.interactions.SeleccionarOpcionSelect2;
import co.com.bonbonite.models.DatosEnvio;
import co.com.bonbonite.models.Usuario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.bonbonite.userinterfaces.CarritoPage.BOTON_FINALIZAR_COMPRA;
import static co.com.bonbonite.userinterfaces.CheckoutPage.*;
import static co.com.bonbonite.utils.Constantes.USUARIO;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Pasa del carrito al checkout, avanza por los pasos y diligencia los datos de facturación y envío.
 * No hace click en "Registrar Orden" para no generar pedidos reales en producción.
 */
public class IrAlPago implements Task {

    private final DatosEnvio datosEnvio;

    public IrAlPago(DatosEnvio datosEnvio) {
        this.datosEnvio = datosEnvio;
    }

    public static IrAlPago yDiligenciarDatos(DatosEnvio datosEnvio) {
        return instrumented(IrAlPago.class, datosEnvio);
    }

    @Override
    @Step("{0} continúa al pago y diligencia los datos de facturación y envío")
    public <T extends Actor> void performAs(T actor) {
        Usuario usuario = actor.recall(USUARIO);
        actor.attemptsTo(
                ClickJs.en(BOTON_FINALIZAR_COMPRA),
                WaitUntil.the(BOTON_CONTINUAR_RESUMEN, isVisible()).forNoMoreThan(30).seconds(),
                ClickJs.en(BOTON_CONTINUAR_RESUMEN)
        );
        esperar(2000);
        // Con sesión iniciada el tema suele saltar directo al paso 3; si muestra el paso 2, se avanza
        if (ENLACE_SIGUIENTE_PASO_LOGUEADO.resolveAllFor(actor).stream().anyMatch(e -> e.isDisplayed())) {
            actor.attemptsTo(ClickJs.en(ENLACE_SIGUIENTE_PASO_LOGUEADO));
        }
        actor.attemptsTo(
                WaitUntil.the(CAMPO_NOMBRES, isVisible()).forNoMoreThan(15).seconds(),
                Enter.theValue(usuario.getNombres()).into(CAMPO_NOMBRES),
                Enter.theValue(usuario.getApellidos()).into(CAMPO_APELLIDOS),
                SeleccionarOpcionSelect2.deLaLista(LISTA_GENERO, datosEnvio.getGenero()),
                Enter.theValue(datosEnvio.getTelefono()).into(CAMPO_TELEFONO),
                SeleccionarOpcionSelect2.deLaLista(LISTA_DEPARTAMENTO, datosEnvio.getDepartamento()),
                SeleccionarOpcionSelect2.deLaLista(LISTA_CIUDAD, datosEnvio.getCiudad()),
                Enter.theValue(datosEnvio.getDireccion()).into(CAMPO_DIRECCION),
                ClickJs.en(CHECK_TRATAMIENTO_DATOS),
                WaitUntil.the(RESUMEN_PEDIDO, isVisible()).forNoMoreThan(30).seconds()
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
