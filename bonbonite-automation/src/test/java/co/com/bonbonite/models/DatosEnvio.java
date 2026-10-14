package co.com.bonbonite.models;

public class DatosEnvio {

    private final String genero;
    private final String telefono;
    private final String departamento;
    private final String ciudad;
    private final String direccion;

    public DatosEnvio(String genero, String telefono, String departamento, String ciudad, String direccion) {
        this.genero = genero;
        this.telefono = telefono;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getGenero() { return genero; }
    public String getTelefono() { return telefono; }
    public String getDepartamento() { return departamento; }
    public String getCiudad() { return ciudad; }
    public String getDireccion() { return direccion; }
}
