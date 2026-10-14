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

public class Registrarse implements Task {

    private final Usuario usuario;

    public Registrarse(Usuario usuario) {
        this.usuario = usuario;
    }

    public static Registrarse con(Usuario usuario) {
        return instrumented(Registrarse.class, usuario);
    }

    @Override
    @Step("{0} se registra con los datos #usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickJs.en(ENLACE_REGISTRATE),
                WaitUntil.the(CAMPO_CEDULA, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(usuario.getCedula()).into(CAMPO_CEDULA),
                Enter.theValue(usuario.getNombres()).into(CAMPO_NOMBRES),
                Enter.theValue(usuario.getApellidos()).into(CAMPO_APELLIDOS),
                Enter.theValue(usuario.getEmail()).into(CAMPO_EMAIL),
                Enter.theValue(usuario.getPassword()).into(CAMPO_PASSWORD),
                Enter.theValue(usuario.getPassword()).into(CAMPO_CONFIRMAR_PASSWORD),
                ClickJs.en(CHECK_TRATAMIENTO_DATOS),
                ClickJs.en(BOTON_REGISTRARME),
                WaitUntil.the(PANEL_CUENTA, isVisible()).forNoMoreThan(30).seconds()
        );
    }
}
