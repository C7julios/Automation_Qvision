package co.com.bonbonite.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Los selects del checkout (departamento, ciudad, género...) usan select2 y el select original queda oculto.
 * Esta interacción espera a que el select tenga opciones y esté habilitado (la ciudad se carga por AJAX según el
 * departamento), elige la opción cuyo texto contiene el valor buscado (sin tildes ni mayúsculas) y dispara
 * "change" con jQuery para que WooCommerce y select2 se actualicen.
 */
public class SeleccionarOpcionSelect2 implements Interaction {

    private static final String SELECT_LISTO =
            "var s = document.getElementById(arguments[0]);"
            + "return !!s && !s.disabled && s.options.length > 1;";

    private static final String SELECCIONAR =
            "var norm = function (t) { return t.normalize('NFD').replace(/[\\u0300-\\u036f]/g, '').toLowerCase().trim(); };"
            + "var s = document.getElementById(arguments[0]); var buscado = norm(arguments[1]);"
            + "var o = Array.from(s.options).find(function (op) { return op.value && norm(op.text).indexOf(buscado) >= 0; });"
            + "if (!o) { return null; }"
            + "jQuery(s).val(o.value).trigger('change');"
            + "return o.text;";

    private final String idSelect;
    private final String opcion;

    public SeleccionarOpcionSelect2(String idSelect, String opcion) {
        this.idSelect = idSelect;
        this.opcion = opcion;
    }

    public static SeleccionarOpcionSelect2 deLaLista(String idSelect, String opcion) {
        return instrumented(SeleccionarOpcionSelect2.class, idSelect, opcion);
    }

    @Override
    @Step("{0} selecciona '#opcion' en la lista #idSelect")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> Boolean.TRUE.equals(js.executeScript(SELECT_LISTO, idSelect)));
        Object seleccionada = js.executeScript(SELECCIONAR, idSelect, opcion);
        if (seleccionada == null) {
            throw new AssertionError("No existe la opción '" + opcion + "' en la lista " + idSelect);
        }
    }
}
