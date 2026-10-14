package co.com.bonbonite.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class CarritoPage {

    public static final String URL = "https://www.bon-bonite.com/carrito/";

    public static final Target NOMBRES_PRODUCTOS = Target.the("productos del carrito").locatedBy("css:.woocommerce-cart-form .product-name a");
    public static final Target CANTIDADES = Target.the("cantidades del carrito").locatedBy("css:.woocommerce-cart-form td.product-quantity input.qty");
    public static final Target PRECIOS = Target.the("precios del carrito").locatedBy("css:.woocommerce-cart-form .product-price .woocommerce-Price-amount");
    public static final Target BOTON_FINALIZAR_COMPRA = Target.the("botón Finalizar compra").locatedBy("css:a.checkout-button");

    private CarritoPage() {
    }
}
