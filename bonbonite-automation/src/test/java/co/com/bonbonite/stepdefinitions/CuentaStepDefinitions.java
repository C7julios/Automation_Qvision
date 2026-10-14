package co.com.bonbonite.stepdefinitions;

import co.com.bonbonite.interactions.AbrirMenuCuenta;
import co.com.bonbonite.models.Usuario;
import co.com.bonbonite.tasks.AbrirSitio;
import co.com.bonbonite.tasks.CerrarSesion;
import co.com.bonbonite.tasks.IniciarSesion;
import co.com.bonbonite.tasks.Registrarse;
import co.com.bonbonite.utils.GeneradorDatos;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.bonbonite.userinterfaces.MiCuentaPage.ENLACE_CERRAR_SESION;
import static co.com.bonbonite.userinterfaces.MiCuentaPage.PANEL_CUENTA;
import static co.com.bonbonite.utils.Constantes.USUARIO;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/**
 * Pasos de registro e inicio de sesión (Escenario 1 y precondición de los Escenarios 2 y 3).
 */
public class CuentaStepDefinitions {

    @Dado("que {actor} es una cliente nueva que ingresa a la sección Mi cuenta")
    public void clienteNuevaIngresaAMiCuenta(Actor actor) {
        actor.remember(USUARIO, GeneradorDatos.usuarioNuevo(actor.getName()));
        actor.attemptsTo(AbrirSitio.enMiCuenta());
    }

    @Cuando("se registra con datos válidos y acepta el tratamiento de datos personales")
    public void seRegistraConDatosValidos() {
        Actor actor = theActorInTheSpotlight();
        actor.attemptsTo(Registrarse.con(actor.recall(USUARIO)));
    }

    @Entonces("debería ver el panel de su cuenta con su nombre")
    public void deberiaVerElPanelDeSuCuenta() {
        Actor actor = theActorInTheSpotlight();
        Usuario usuario = actor.recall(USUARIO);
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().contains("/mi-cuenta"),
                Ensure.that(Text.of(PANEL_CUENTA)).containsIgnoringCase(usuario.getNombres())
        );
    }

    @Entonces("debería tener la opción de cerrar sesión")
    public void deberiaTenerLaOpcionDeCerrarSesion() {
        theActorInTheSpotlight().attemptsTo(
                AbrirMenuCuenta.delEncabezado(),
                Ensure.that(ENLACE_CERRAR_SESION).isDisplayed()
        );
    }

    @Dado("que {actor} es una cliente registrada que inició sesión en el sitio")
    public void clienteRegistradaQueInicioSesion(Actor actor) {
        Usuario usuario = GeneradorDatos.usuarioNuevo(actor.getName());
        actor.remember(USUARIO, usuario);
        actor.attemptsTo(
                AbrirSitio.enMiCuenta(),
                Registrarse.con(usuario),
                CerrarSesion.delSitio(),
                IniciarSesion.con(usuario)
        );
    }
}
