package co.com.bonbonite.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static co.com.bonbonite.userinterfaces.MiCuentaPage.BOTON_ACEPTAR_COOKIES;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * El banner de cookies (cookie-script) se carga de forma asíncrona y puede tapar botones.
 * Si aparece en unos segundos se acepta; si no aparece, se continúa sin fallar.
 */
public class AceptarCookies implements Interaction {

    public static AceptarCookies siSeMuestranLasCookies() {
        return instrumented(AceptarCookies.class);
    }

    @Override
    @Step("{0} acepta las cookies si se muestra el banner")
    public <T extends Actor> void performAs(T actor) {
        try {
            WebElement boton = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(BOTON_ACEPTAR_COOKIES));
            boton.click();
        } catch (Exception bannerNoVisible) {
            // Sin banner: nada que hacer
        }
    }
}
