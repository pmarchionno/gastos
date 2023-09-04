package dao.dto;

public class TransaccionesDto {
  private int gestorDeCuentaId;
  private int categoriaId;
  private String fecha;
  private Double monto;

  public TransaccionesDto() {
  }

  public TransaccionesDto(int gestorDeCuentaId, int categoriaId, String fecha, Double monto) {
    this.gestorDeCuentaId = gestorDeCuentaId;
    this.categoriaId = categoriaId;
    this.fecha = fecha;
    this.monto = monto;
  }

  public int getGestorDeCuentaId() {
    return gestorDeCuentaId;
  }

  public void setGestorDeCuentaId(int gestorDeCuentaId) {
    this.gestorDeCuentaId = gestorDeCuentaId;
  }

  public int getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(int categoriaId) {
    this.categoriaId = categoriaId;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public Double getMonto() {
    return monto;
  }

  public Double getMontoNeto() {
    return monto;
  }
  public void setMonto(Double monto) {
    this.monto = monto;
  }

  @Override
  public String toString() {
    return "Transacción {" +
        "gestorDeCuentaId='" + gestorDeCuentaId + '\'' +
        ", categoriaId='" + categoriaId + '\'' +
        ", fecha='" + fecha + '\'' +
        ", monto='" + monto + '\'' +
        '}';
  }
}
