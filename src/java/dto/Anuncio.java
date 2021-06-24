package dto;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public class Anuncio {

    private int anuncioId;
    private int usuarioId;
    private String titulo;
    private String categoria;
    private String descripcion;
    private int telefono;
    private int precio;
    private int estado;
    private int imagenFileId;

    public Anuncio() {
        
    }

    public int getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(int anuncioId) {
        this.anuncioId = anuncioId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getImagenFileId() {
        return imagenFileId;
    }

    public void setImagenFileId(int imagenFileId) {
        this.imagenFileId = imagenFileId;
    }

  

    

}
