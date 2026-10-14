package co.com.bonbonite.tasks;

import co.com.bonbonite.interactions.ClickJs;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.bonbonite.userinterfaces.EditarCuentaPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ModificarDatos implements Task {

    private final String nombres;
    private final String apellidos;
    private final String telefono;

    public ModificarDatos(String nombres, String apellidos, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public static ModificarDatos personales(String nombres, String apellidos, String telefono) {
        return instrumented(ModificarDatos.class, nombres, apellidos, telefono);
    }

    @Override
    @Step("{0} actualiza sus datos personales a #nombres #apellidos, teléfono #telefono")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(URL),
                WaitUntil.the(BOTON_ACTUALIZAR_INFORMACION, isVisible()).forNoMoreThan(20).seconds(),
                ClickJs.en(BOTON_ACTUALIZAR_INFORMACION),
                WaitUntil.the(CAMPO_EDITABLE.of(NOMBRES), isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(nombres).into(CAMPO_EDITABLE.of(NOMBRES)),
                Enter.theValue(apellidos).into(CAMPO_EDITABLE.of(APELLIDOS)),
                Enter.theValue(telefono).into(CAMPO_EDITABLE.of(TELEFONO)),
                ClickJs.en(BOTON_GUARDAR),
                WaitUntil.the(MENSAJE_EXITO, isVisible()).forNoMoreThan(30).seconds()
        );
    }
}
