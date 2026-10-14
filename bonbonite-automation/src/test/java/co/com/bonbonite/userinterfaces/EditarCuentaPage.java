package co.com.bonbonite.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Pestaña "Datos" de Mi cuenta. El sitio usa un formulario propio (#profile-update-form): los datos se muestran
 * como texto y al pulsar "Actualizar Información" se convierten en campos editables.
 */
public final class EditarCuentaPage {

    public static final String URL = "https://www.bon-bonite.com/mi-cuenta/edit-account/";

    public static final Target BOTON_ACTUALIZAR_INFORMACION = Target.the("botón Actualizar Información")
            .locatedBy("css:#profile-update-form .update-info-btn");
    public static final Target CAMPO_EDITABLE = Target.the("campo editable {0}")
            .locatedBy("css:#profile-update-form input[name='{0}']");
    public static final Target BOTON_GUARDAR = Target.the("botón Guardar")
            .locatedBy("css:#profile-update-form .save-info-btn");
    public static final Target MENSAJE_EXITO = Target.the("mensaje de confirmación")
            .locatedBy("css:#profile-message");
    public static final Target VALOR_GUARDADO = Target.the("valor guardado del campo {0}")
            .locatedBy("css:#profile-update-form .profile-field[data-field='{0}']");

    public static final String NOMBRES = "first_name";
    public static final String APELLIDOS = "last_name";
    public static final String TELEFONO = "billing_phone";

    private EditarCuentaPage() {
    }
}
