package co.com.bonbonite.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Click por JavaScript para elementos que quedan cubiertos por banners o capas (cookies, chat, popups).
 */
public class ClickJs implements Interaction {

    private final Target target;

    public ClickJs(Target target) {
        this.target = target;
    }

    public static ClickJs en(Target target) {
        return instrumented(ClickJs.class, target);
    }

    @Override
    @Step("{0} hace click en #target")
    public <T extends Actor> void performAs(T actor) {
        WebElement elemento = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", elemento);
    }
}
