package entities;

public class Categorias {
  private int id;
  private String nombre;
  private int tipoOperacionId;

  public Categorias() {
  }

  public Categorias(int id, String nombre, int tipoOperacionId) {
    this.id = id;
    this.nombre = nombre;
    this.tipoOperacionId = tipoOperacionId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getTipoOperacionId() {
    return tipoOperacionId;
  }

  public void setTipoOperacionId(int tipoOperacionId) {
    this.tipoOperacionId = tipoOperacionId;
  }

  @Override
  public String toString() {
    return "Categorias {" +
        "id=" + id +
        ", nombre='" + nombre + '\'' +
        ", tipoOperacionId='" + tipoOperacionId + '\'' +
        '}';
  }
}
