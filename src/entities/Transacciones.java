package entities;

public class Transacciones {
  private int id;
  private int gestorDeCuentaId;
  private int categoriaId;
  private String fecha;
  private Double monto;

  public Transacciones() {
  }

  public Transacciones(int id, int gestorDeCuentaId, int categoriaId, String fecha, Double monto) {
    this.id = id;
    this.gestorDeCuentaId = gestorDeCuentaId;
    this.categoriaId = categoriaId;
    this.fecha = fecha;
    this.monto = monto;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public void setMonto(Double monto) {
    this.monto = monto;
  }

  @Override
  public String toString() {
    return "Transacción {" +
        "id=" + id +
        ", gestorDeCuentaId='" + gestorDeCuentaId + '\'' +
        ", categoriaId='" + categoriaId + '\'' +
        ", fecha='" + fecha + '\'' +
        ", monto='" + monto + '\'' +
        '}';
  }
}
