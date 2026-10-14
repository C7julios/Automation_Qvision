package co.com.bonbonite.utils;

import co.com.bonbonite.models.DatosEnvio;
import co.com.bonbonite.models.Usuario;
import net.datafaker.Faker;

import java.util.Locale;

/**
 * Genera datos de prueba únicos por ejecución. Los correos usan el prefijo "qa.auto"
 * para identificar fácilmente las cuentas creadas por la automatización.
 */
public final class GeneradorDatos {

    private static final Faker FAKER = new Faker(new Locale("es", "CO"));

    private GeneradorDatos() {
    }

    public static Usuario usuarioNuevo(String nombre) {
        long marca = System.currentTimeMillis();
        String cedula = "9" + String.valueOf(marca).substring(4); // 10 dígitos, único por ejecución
        String apellido = FAKER.name().lastName().replaceAll("[^A-Za-zÁÉÍÓÚáéíóúÑñ ]", "");
        String email = "qa.auto." + marca + "@carlosmail.com";
        return new Usuario(cedula, nombre, apellido, email, "QaAuto#" + marca);
    }

    public static DatosEnvio datosEnvio() {
        return new DatosEnvio("Mujer", "300" + FAKER.number().digits(7), "Antioquia", "Medellín",
                "Calle " + FAKER.number().numberBetween(1, 99) + " # " + FAKER.number().numberBetween(1, 99)
                        + " - " + FAKER.number().numberBetween(1, 99));
    }
}
