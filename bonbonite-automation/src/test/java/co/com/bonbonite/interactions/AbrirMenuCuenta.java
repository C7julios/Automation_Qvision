package co.com.bonbonite.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.bonbonite.userinterfaces.MiCuentaPage.ICONO_USUARIO;
import static co.com.bonbonite.userinterfaces.MiCuentaPage.MENU_CUENTA;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Despliega el menú de la cuenta que se abre desde el ícono con la inicial del usuario.
 */
public class AbrirMenuCuenta implements Interaction {

    public static AbrirMenuCuenta delEncabezado() {
        return instrumented(AbrirMenuCuenta.class);
    }

    @Override
    @Step("{0} abre el menú de su cuenta")
    public <T extends Actor> void performAs(T actor) {
        if (!MENU_CUENTA.resolveFor(actor).isDisplayed()) {
            actor.attemptsTo(Click.on(ICONO_USUARIO));
        }
        actor.attemptsTo(WaitUntil.the(MENU_CUENTA, isVisible()).forNoMoreThan(10).seconds());
    }
}
