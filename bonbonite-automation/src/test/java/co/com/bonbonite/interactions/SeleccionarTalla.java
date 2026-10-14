package co.com.bonbonite.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Scroll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static co.com.bonbonite.userinterfaces.ProductoPage.BOTON_TALLA;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Selecciona la talla con click real sobre el botón del tema. Si el tema no sincroniza el select de
 * WooCommerce (#pa_talla), se fija el valor y se dispara el evento change para que WooCommerce
 * resuelva la variación. Termina cuando el formulario tiene un variation_id válido.
 */
public class SeleccionarTalla implements Interaction {

    private static final String VARIATION_ID =
            "var i = document.querySelector('form.variations_form input[name=variation_id]');"
            + "return i ? i.value : '0';";

    private final String talla;

    public SeleccionarTalla(String talla) {
        this.talla = talla;
    }

    public static SeleccionarTalla numero(String talla) {
        return instrumented(SeleccionarTalla.class, talla);
    }

    @Override
    @Step("{0} selecciona la talla #talla")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        actor.attemptsTo(Scroll.to(BOTON_TALLA.of(talla)).andAlignToBottom());
        BOTON_TALLA.of(talla).resolveFor(actor).click();

        if (!variacionResuelta(js)) {
            js.executeScript(
                    "var s = document.querySelector('#pa_talla'); s.value = arguments[0];"
                    + "if (window.jQuery) { jQuery(s).trigger('change'); }"
                    + "else { s.dispatchEvent(new Event('change', {bubbles: true})); }", talla);
        }
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(d -> variacionResuelta(js));
    }

    private static boolean variacionResuelta(JavascriptExecutor js) {
        Object valor = js.executeScript(VARIATION_ID);
        return valor != null && !valor.toString().isBlank() && !"0".equals(valor.toString());
    }
}
