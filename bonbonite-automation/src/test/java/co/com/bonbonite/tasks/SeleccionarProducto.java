package co.com.bonbonite.tasks;

import co.com.bonbonite.questions.TallaDisponible;
import co.com.bonbonite.models.ProductoSeleccionado;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static co.com.bonbonite.userinterfaces.CategoriaPage.ENLACES_PRODUCTOS;
import static co.com.bonbonite.userinterfaces.CategoriaPage.MENU_CATEGORIA;
import static co.com.bonbonite.userinterfaces.ProductoPage.*;
import static co.com.bonbonite.utils.Constantes.PRODUCTO;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Entra a la categoría desde el menú y recorre sus productos hasta encontrar el primero con
 * una talla disponible en inventario. El producto elegido queda en la memoria del actor.
 */
public class SeleccionarProducto implements Task {

    private static final int MAXIMO_PRODUCTOS_A_REVISAR = 10;

    private final String categoria;

    public SeleccionarProducto(String categoria) {
        this.categoria = categoria;
    }

    public static SeleccionarProducto disponibleDeLaCategoria(String categoria) {
        return instrumented(SeleccionarProducto.class, categoria);
    }

    @Override
    @Step("{0} selecciona un producto disponible de la categoría #categoria")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("https://www.bon-bonite.com/"),
                WaitUntil.the(MENU_CATEGORIA.of(categoria), isPresent()).forNoMoreThan(20).seconds()
        );
        String urlCategoria = MENU_CATEGORIA.of(categoria).resolveFor(actor).getAttribute("href");
        actor.attemptsTo(
                Open.url(urlCategoria),
                WaitUntil.the(ENLACES_PRODUCTOS, isPresent()).forNoMoreThan(20).seconds()
        );

        List<String> urlsProductos = ENLACES_PRODUCTOS.resolveAllFor(actor).stream()
                .map(e -> e.getAttribute("href"))
                .distinct()
                .limit(MAXIMO_PRODUCTOS_A_REVISAR)
                .toList();

        for (String urlProducto : urlsProductos) {
            actor.attemptsTo(
                    Open.url(urlProducto),
                    WaitUntil.the(TITULO, isVisible()).forNoMoreThan(20).seconds()
            );
            String talla = actor.asksFor(TallaDisponible.delProductoActual());
            if (talla != null) {
                String nombre = TITULO.resolveFor(actor).getText().trim();
                String precio = PRECIO.resolveFor(actor).getText().trim();
                actor.remember(PRODUCTO, new ProductoSeleccionado(nombre, talla, precio));
                return;
            }
        }
        throw new AssertionError("No se encontró ningún producto con inventario en la categoría " + categoria);
    }
}
