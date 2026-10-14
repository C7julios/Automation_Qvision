package co.com.bonbonite.runners;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Ejecuta todos los escenarios de src/test/resources/features.
 * Filtrar por etiqueta: mvn clean verify -Dtags="@Compra"
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class CucumberTestSuite {
}
