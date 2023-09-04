package entities;

public class GestorDeCuenta {
  private int id;
  private int tipoDeCuentaId;
  private String descripcion;

  private Double saldoInial;

  public GestorDeCuenta() {
  }

  public GestorDeCuenta(int id, int tipoDeCuentaId, String descripcion, Double saldoInial) {
    this.id = id;
    this.tipoDeCuentaId = tipoDeCuentaId;
    this.descripcion = descripcion;
    this.saldoInial = saldoInial;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getTipoDeCuentaId() {
    return tipoDeCuentaId;
  }

  public void setTipoDeCuentaId(int tipoDeCuentaId) {
    this.tipoDeCuentaId = tipoDeCuentaId;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Double getSaldoInial() {
    return saldoInial;
  }

  public void setSaldoInial(Double saldoInial) {
    this.saldoInial = saldoInial;
  }

  @Override
  public String toString() {
    return "Gestor de Cuenta {" +
        "id=" + id +
        ", tipoDeCuentaId='" + tipoDeCuentaId + '\'' +
        ", descripcion='" + descripcion + '\'' +
        ", saldoInial='" + saldoInial + '\'' +
        '}';
  }
}
