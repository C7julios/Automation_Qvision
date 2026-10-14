# Bon-bonite – Automatización E2E (Serenity BDD + Screenplay)

Automatización de tres escenarios del sitio https://www.bon-bonite.com/ (versión 1):

| # | Escenario | Feature | Etiqueta |
|---|-----------|---------|----------|
| 1 | Registro exitoso de usuario | `registro.feature` | `@Registro` |
| 2 | Modificación de datos del usuario registrado | `modificar_datos.feature` | `@ModificarDatos` |
| 3 | Compra de producto (Zapatos) hasta el resumen del pedido | `compra.feature` | `@Compra` |

## Requisitos
- JDK 17 o superior (probado con JDK 22)
- Maven 3.9+
- Google Chrome (Selenium Manager descarga el driver automáticamente)

## Ejecución
```bash
mvn clean verify                              # los 3 escenarios
mvn clean verify -Dtags="@Compra"             # un escenario por etiqueta
mvn clean verify -Dheadless.mode=true         # sin abrir el navegador
```
Reporte: `target/site/serenity/index.html`

## Documento de pruebas (Excel)
Después de ejecutar las pruebas, regenera `../Plan_Pruebas_BonBonite.xlsx` y las capturas de `../evidencias/`
con los resultados de esa ejecución (requiere Python 3 con `openpyxl` y `pillow`):
```bash
pip install openpyxl pillow
python tools/generar_excel.py
```
El script lee los features y el reporte JSON de Serenity (`target/site/serenity`), por lo que debe correrse
después de `mvn clean verify`.

## Estructura (patrón Screenplay)
```
src/test/java/co/com/bonbonite
├── runners/          CucumberTestSuite (JUnit Platform)
├── stepdefinitions/  Pasos Gherkin en español + Hooks (OnStage)
├── tasks/            AbrirSitio, Registrarse, IniciarSesion, CerrarSesion, ModificarDatos,
│                     SeleccionarProducto, AgregarAlCarrito, IrAlPago
├── interactions/     ClickJs, AceptarCookies, AbrirMenuCuenta, SeleccionarTalla, SeleccionarOpcionSelect2
├── questions/        TallaDisponible
├── userinterfaces/   MiCuentaPage, EditarCuentaPage, CategoriaPage, ProductoPage, CarritoPage, CheckoutPage
├── models/           Usuario, DatosEnvio, ProductoSeleccionado
└── utils/            GeneradorDatos, Constantes
src/test/resources
├── features/         registro.feature, modificar_datos.feature, compra.feature
├── serenity.conf
└── junit-platform.properties
```

## Consideraciones
- **Ambiente productivo**: cada ejecución crea usuarios reales con correo `qa.auto.<timestamp>@carlosmail.com`
  y cédula única que empieza por 9. Se recomienda un ambiente de pruebas (staging) para ejecuciones frecuentes.
- **La compra no se confirma**: el escenario 3 llega hasta el formulario de facturación/envío diligenciado y valida
  el resumen del pedido, sin pulsar "Registrar Orden", para no generar pedidos reales.
- **Datos dinámicos**: el producto no está quemado; se recorre la categoría y se elige el primero con una talla
  con inventario, leyendo el JSON de variaciones de WooCommerce (`data-product_variations`).
