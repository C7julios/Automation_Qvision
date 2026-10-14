package co.com.bonbonite.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Lee el JSON de variaciones de WooCommerce (atributo data-product_variations) y retorna
 * la primera talla con inventario, o null si el producto está agotado.
 */
public class TallaDisponible implements Question<String> {

    private static final String SCRIPT =
            "var f = document.querySelector('form.variations_form');"
            + "if (!f) { return null; }"
            + "var v = JSON.parse(f.getAttribute('data-product_variations') || '[]');"
            + "var d = v.find(function (e) { return e.is_in_stock && e.variation_is_active && e.is_purchasable; });"
            + "return d ? d.attributes['attribute_pa_talla'] : null;";

    public static TallaDisponible delProductoActual() {
        return new TallaDisponible();
    }

    @Override
    public String answeredBy(Actor actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        Object talla = js.executeScript(SCRIPT);
        return talla == null ? null : talla.toString();
    }
}
