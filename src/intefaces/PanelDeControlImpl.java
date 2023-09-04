package intefaces;

import dao.dto.CategoriasDto;
import dao.dto.GestorDeCuentaDto;
import dao.dto.TransaccionesDto;

import java.util.List;

public class PanelDeControlImpl implements PanelDeControl {
  @Override
  public double calcularTotalGastos(List<TransaccionesDto> transaccionesDtos, List<CategoriasDto> categoriasDtos) {
    return transaccionesDtos.stream()
        .mapToDouble(el -> el.getMonto() * valorOperacion(el.getGestorDeCuentaId(), categoriasDtos))
        .sum();
  }

  public int valorOperacion(int gestorDeCuentaid, List<CategoriasDto> categoriasDtos) {
    int tipo = categoriasDtos.stream()
        .filter(el -> el.getId() == gestorDeCuentaid)
        .mapToInt(el -> el.getTipoOperacionId())
        .sum();
    if (tipo == 1) return 1;
    return -1;
  }
}
