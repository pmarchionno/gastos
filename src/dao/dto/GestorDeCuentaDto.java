package dao.dto;

public class GestorDeCuentaDto {
  private int id;
  private int tipoDeCuentaId;
  private String descripcion;

  private Double saldoInicial;

  public GestorDeCuentaDto() {
  }

  public GestorDeCuentaDto(int id, int tipoDeCuentaId, String descripcion, Double saldoInicial) {
    this.id = id;
    this.tipoDeCuentaId = tipoDeCuentaId;
    this.descripcion = descripcion;
    this.saldoInicial = saldoInicial;
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

  public Double getSaldoInicial() {
    return saldoInicial;
  }

  public void setSaldoInicial(Double saldoInicial) {
    this.saldoInicial = saldoInicial;
  }

  @Override
  public String toString() {
    return "Gestor de Cuenta {" +
        "tipoDeCuentaId='" + tipoDeCuentaId + '\'' +
        ", descripcion='" + descripcion + '\'' +
        ", saldoInicial='" + saldoInicial + '\'' +
        '}';
  }
}
