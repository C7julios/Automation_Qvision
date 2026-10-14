package co.com.bonbonite.tasks;

import co.com.bonbonite.interactions.AceptarCookies;
import co.com.bonbonite.userinterfaces.MiCuentaPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AbrirSitio implements Task {

    public static AbrirSitio enMiCuenta() {
        return instrumented(AbrirSitio.class);
    }

    @Override
    @Step("{0} abre la sección Mi cuenta del sitio Bon-bonite")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(MiCuentaPage.class),
                AceptarCookies.siSeMuestranLasCookies()
        );
    }
}
