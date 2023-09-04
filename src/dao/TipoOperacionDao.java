package dao;

import dao.dto.TipoOperacionDto;
import exceptions.DAOException;

import java.util.List;

public interface TipoOperacionDao {
  List<TipoOperacionDto> getAll() throws DAOException;
}
