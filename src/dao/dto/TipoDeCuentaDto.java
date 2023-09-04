package dao.dto;

public class TipoDeCuentaDto {
  private String nombre;

  public TipoDeCuentaDto() {
  }

  public TipoDeCuentaDto(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return "Tipo de Cuenta {" +
        "nombre='" + nombre + '\'' +
        '}';
  }
}
