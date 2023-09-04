package dao.impl;

import dao.TipoOperacionDao;
import dao.dto.TipoOperacionDto;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoOperacionImpl implements TipoOperacionDao {
  private static final String GET_ALL_TIPOOPERACION = "SELECT * FROM tipoOperacion";
  private final Connection connection;

  public TipoOperacionImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<TipoOperacionDto> getAll() throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TIPOOPERACION)) {
      ResultSet resultSet = statement.executeQuery();
      List<TipoOperacionDto> tipoOperacionDtos = new ArrayList<>();
      // Iteramos el ResultSet para agregar el gasto a la lista
      // y mientras agregamos, realizamo el mapeo de cada item
      while (resultSet.next()) {
        tipoOperacionDtos.add(mapDtoToTipoOperacionesDto(resultSet));
      }
      return tipoOperacionDtos;
    } catch (SQLException e) {
      throw new DAOException("Error al obtener la lista de Tipo de Operaciones", e);
    }
  }

  private TipoOperacionDto mapDtoToTipoOperacionesDto(ResultSet resultSet) throws SQLException {
    TipoOperacionDto tipoOperacionDto = new TipoOperacionDto();
    tipoOperacionDto.setId(resultSet.getInt("id"));
    tipoOperacionDto.setNombre(resultSet.getString("nombre"));
    tipoOperacionDto.setOperacion(resultSet.getString("operacion"));
    return tipoOperacionDto;
  }
}
