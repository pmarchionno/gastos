package intefaces;

import dao.dto.CategoriasDto;
import dao.dto.TransaccionesDto;

import java.util.List;

public interface PanelDeControl {
  double calcularTotalGastos(List<TransaccionesDto> transaccionesDtos, List<CategoriasDto> categoriasDtos);
}
