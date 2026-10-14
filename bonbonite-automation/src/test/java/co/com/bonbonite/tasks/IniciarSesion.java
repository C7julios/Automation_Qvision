package co.com.bonbonite.tasks;

import co.com.bonbonite.interactions.ClickJs;
import co.com.bonbonite.models.Usuario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.bonbonite.userinterfaces.MiCuentaPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesion implements Task {

    private final Usuario usuario;

    public IniciarSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public static IniciarSesion con(Usuario usuario) {
        return instrumented(IniciarSesion.class, usuario);
    }

    @Override
    @Step("{0} inicia sesión con la cédula y contraseña de #usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                AbrirSitio.enMiCuenta(),
                WaitUntil.the(CAMPO_CEDULA_LOGIN, isVisible()).forNoMoreThan(20).seconds(),
                Enter.theValue(usuario.getCedula()).into(CAMPO_CEDULA_LOGIN),
                Enter.theValue(usuario.getPassword()).into(CAMPO_PASSWORD_LOGIN),
                ClickJs.en(BOTON_INICIAR_SESION),
                WaitUntil.the(PANEL_CUENTA, isVisible()).forNoMoreThan(30).seconds()
        );
    }
}
