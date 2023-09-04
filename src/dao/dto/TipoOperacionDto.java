package dao.dto;

public class TipoOperacionDto {
  private int id;
  private String nombre;
  private String  operacion;

  public TipoOperacionDto() {
  }

  public TipoOperacionDto(int id, String nombre, String operacion) {
    this.id = id;
    this.nombre = nombre;
    this.operacion = operacion;
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

  public String getOperacion() {
    return operacion;
  }

  public void setOperacion(String operacion) {
    this.operacion = operacion;
  }

  @Override
  public String toString() {
    return "Tipo de Operación {" +
        "Nombre='" + nombre + '\'' +
        ", Operacion='" + operacion + '\'' +
        '}';
  }
}
