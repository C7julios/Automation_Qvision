package co.com.bonbonite.models;

public class ProductoSeleccionado {

    private final String nombre;
    private final String talla;
    private final String precio;

    public ProductoSeleccionado(String nombre, String talla, String precio) {
        this.nombre = nombre;
        this.talla = talla;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public String getTalla() { return talla; }
    public String getPrecio() { return precio; }

    @Override
    public String toString() {
        return nombre + " - talla " + talla + " - " + precio;
    }
}
