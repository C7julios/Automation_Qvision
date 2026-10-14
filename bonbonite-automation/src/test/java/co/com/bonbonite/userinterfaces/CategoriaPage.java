package co.com.bonbonite.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class CategoriaPage {

    public static final Target MENU_CATEGORIA = Target.the("categoría {0} del menú")
            .locatedBy("//header//a[normalize-space()='{0}' and contains(@href,'categoria-producto')]");
    public static final Target ENLACES_PRODUCTOS = Target.the("productos de la categoría")
            .locatedBy("css:.product-wrapper-with-variation > a[href*='/producto/']");

    private CategoriaPage() {
    }
}
