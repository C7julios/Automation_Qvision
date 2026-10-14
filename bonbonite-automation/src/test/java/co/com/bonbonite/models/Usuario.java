package co.com.bonbonite.models;

public class Usuario {

    private final String cedula;
    private final String nombres;
    private final String apellidos;
    private final String email;
    private final String password;

    public Usuario(String cedula, String nombres, String apellidos, String email, String password) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    public String getCedula() { return cedula; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return nombres + " " + apellidos + " (cédula " + cedula + ", " + email + ")";
    }
}
