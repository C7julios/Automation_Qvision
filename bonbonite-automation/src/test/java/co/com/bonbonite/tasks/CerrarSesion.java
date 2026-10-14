package co.com.bonbonite.tasks;

import co.com.bonbonite.interactions.AbrirMenuCuenta;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static co.com.bonbonite.userinterfaces.MiCuentaPage.CAMPO_CEDULA_LOGIN;
import static co.com.bonbonite.userinterfaces.MiCuentaPage.ENLACE_CERRAR_SESION;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CerrarSesion implements Task {

    private static final String MI_CUENTA = "https://www.bon-bonite.com/mi-cuenta/";

    public static CerrarSesion delSitio() {
        return instrumented(CerrarSesion.class);
    }

    @Override
    @Step("{0} cierra sesión")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(AbrirMenuCuenta.delEncabezado());
        // Se usa el mismo enlace "Cerrar Sesión" (con el nonce de WordPress) pero redirigiendo a Mi cuenta en lugar
        // del home: el home carga videos y feeds pesados que demoran más de un minuto el evento load del navegador.
        String enlace = ENLACE_CERRAR_SESION.resolveFor(actor).getAttribute("href")
                .replaceAll("redirect_to=[^&]*", "redirect_to=" + URLEncoder.encode(MI_CUENTA, StandardCharsets.UTF_8));
        actor.attemptsTo(
                Open.url(enlace),
                WaitUntil.the(CAMPO_CEDULA_LOGIN, isVisible()).forNoMoreThan(30).seconds()
        );
    }
}
