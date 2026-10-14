package co.com.bonbonite.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Checkout por pasos del tema: 1) Carrito (resumen), 2) Ingreso (usuario ya logueado), 3) Datos y pago.
 */
public final class CheckoutPage {

    // Paso 1 y 2
    public static final Target BOTON_CONTINUAR_RESUMEN = Target.the("botón Continuar del resumen")
            .locatedBy("css:#step1 button.cta-step[data-step='step2']");
    public static final Target ENLACE_SIGUIENTE_PASO_LOGUEADO = Target.the("enlace 'Ya estás logueado, ir al siguiente paso'")
            .locatedBy("css:#step2 .go-to-step-3");

    // Paso 3: datos de facturación y envío
    public static final String LISTA_GENERO = "billing_gender";
    public static final String LISTA_DEPARTAMENTO = "billing_state";
    public static final String LISTA_CIUDAD = "billing_city";
    public static final Target CAMPO_NOMBRES = Target.the("nombres de facturación").locatedBy("css:#billing_first_name");
    public static final Target CAMPO_APELLIDOS = Target.the("apellidos de facturación").locatedBy("css:#billing_last_name");
    public static final Target CAMPO_TELEFONO = Target.the("teléfono de facturación").locatedBy("css:#billing_phone");
    public static final Target CAMPO_DIRECCION = Target.the("dirección de facturación").locatedBy("css:#billing_address_1");
    public static final Target CHECK_TRATAMIENTO_DATOS = Target.the("autorización de tratamiento de datos").locatedBy("css:#terms");

    // Resumen del pedido
    public static final Target RESUMEN_PEDIDO = Target.the("resumen del pedido").locatedBy("css:#order_review");
    public static final Target PRODUCTOS_RESUMEN = Target.the("productos del resumen")
            .locatedBy("css:#order_review .cart_item .product-name");
    public static final Target TOTAL_PEDIDO = Target.the("total del pedido")
            .locatedBy("css:#order_review .order-total strong .woocommerce-Price-amount");
    public static final Target BOTON_REGISTRAR_ORDEN = Target.the("botón Registrar Orden").locatedBy("css:#place_order");

    private CheckoutPage() {
    }
}
