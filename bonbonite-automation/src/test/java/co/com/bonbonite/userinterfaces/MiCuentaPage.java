package co.com.bonbonite.userinterfaces;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("https://www.bon-bonite.com/mi-cuenta/")
public class MiCuentaPage extends PageObject {

    // Login
    public static final Target CAMPO_CEDULA_LOGIN = Target.the("campo cédula de inicio de sesión").locatedBy("css:#username");
    public static final Target CAMPO_PASSWORD_LOGIN = Target.the("campo contraseña de inicio de sesión").locatedBy("css:#password");
    public static final Target BOTON_INICIAR_SESION = Target.the("botón Iniciar Sesión").locatedBy("css:button[name='login']");
    public static final Target ENLACE_REGISTRATE = Target.the("enlace Regístrate").locatedBy("css:#show_register");

    // Registro
    public static final Target CAMPO_CEDULA = Target.the("campo número de cédula").locatedBy("css:#reg_username");
    public static final Target CAMPO_NOMBRES = Target.the("campo nombres").locatedBy("css:#first_name");
    public static final Target CAMPO_APELLIDOS = Target.the("campo apellidos").locatedBy("css:#last_name");
    public static final Target CAMPO_EMAIL = Target.the("campo correo electrónico").locatedBy("css:#reg_email");
    public static final Target CAMPO_PASSWORD = Target.the("campo contraseña").locatedBy("css:#reg_password");
    public static final Target CAMPO_CONFIRMAR_PASSWORD = Target.the("campo confirmar contraseña").locatedBy("css:#reg_password2");
    public static final Target CHECK_TRATAMIENTO_DATOS = Target.the("autorización de tratamiento de datos").locatedBy("css:#privacy_policy_reg");
    public static final Target BOTON_REGISTRARME = Target.the("botón Registrarme").locatedBy("css:button[name='register']");

    // Panel de la cuenta (usuario autenticado)
    public static final Target PANEL_CUENTA = Target.the("saludo del panel de Mi cuenta").locatedBy("//h3[starts-with(normalize-space(),'Hola')]");
    public static final Target ICONO_USUARIO = Target.the("ícono de usuario del encabezado").locatedBy("css:#user-icon-wrap");
    public static final Target MENU_CUENTA = Target.the("menú desplegable de la cuenta").locatedBy("css:#header-account-menu");
    public static final Target ENLACE_CERRAR_SESION = Target.the("enlace Cerrar sesión")
            .locatedBy("css:#header-account-menu a[href*='action=logout']");
    public static final By BOTON_ACEPTAR_COOKIES = By.cssSelector("#cookiescript_accept");
}
