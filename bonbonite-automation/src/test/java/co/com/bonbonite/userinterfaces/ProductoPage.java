package co.com.bonbonite.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class ProductoPage {

    public static final Target TITULO = Target.the("nombre del producto").locatedBy("css:h1.product_title");
    public static final Target PRECIO = Target.the("precio del producto").locatedBy("css:p.price .woocommerce-Price-amount");
    public static final Target BOTON_TALLA = Target.the("talla {0}")
            .locatedBy("css:button.variation-button[data-attribute_name='attribute_pa_talla'][data-value='{0}']");
    public static final Target BOTON_ANADIR_CARRITO = Target.the("botón Añadir al carrito").locatedBy("css:button.single_add_to_cart_button");

    private ProductoPage() {
    }
}
