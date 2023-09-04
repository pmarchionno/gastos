package dao;

import dao.dto.CategoriasDto;
import dao.dto.GestorDeCuentaDto;
import exceptions.DAOException;

import java.util.List;

public interface GestorDeCuentaDao {
  List<GestorDeCuentaDto> getAll() throws DAOException;

  GestorDeCuentaDto getById(int id) throws DAOException;
}
