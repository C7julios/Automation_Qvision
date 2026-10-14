package co.com.bonbonite.stepdefinitions;

import co.com.bonbonite.tasks.ModificarDatos;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.bonbonite.userinterfaces.EditarCuentaPage.*;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ModificarDatosStepDefinitions {

    @Cuando("actualiza sus datos personales con nombres {string}, apellidos {string} y teléfono {string}")
    public void actualizaSusDatos(String nombres, String apellidos, String telefono) {
        theActorInTheSpotlight().attemptsTo(ModificarDatos.personales(nombres, apellidos, telefono));
    }

    @Entonces("debería ver el mensaje {string}")
    public void deberiaVerElMensaje(String mensaje) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(Text.of(MENSAJE_EXITO)).containsIgnoringCase(mensaje)
        );
    }

    @Entonces("al consultar de nuevo sus datos deberían aparecer nombres {string}, apellidos {string} y teléfono {string}")
    public void susDatosDeberianQuedarActualizados(String nombres, String apellidos, String telefono) {
        // Se recarga la página para comprobar que los datos quedaron guardados en el servidor
        theActorInTheSpotlight().attemptsTo(
                Open.url(URL),
                Ensure.that(Text.of(VALOR_GUARDADO.of(NOMBRES))).isEqualTo(nombres),
                Ensure.that(Text.of(VALOR_GUARDADO.of(APELLIDOS))).isEqualTo(apellidos),
                Ensure.that(Text.of(VALOR_GUARDADO.of(TELEFONO))).isEqualTo(telefono)
        );
    }
}
