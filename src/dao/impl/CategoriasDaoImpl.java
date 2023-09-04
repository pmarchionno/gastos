package dao.impl;

import config.JdbcConfiguration;
import dao.CategoriasDao;
import dao.dto.CategoriasDto;
import entities.Categorias;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDaoImpl implements CategoriasDao {
  private static final String GET_ALL_CATEGORIAS = "SELECT * FROM categorias";
  private static final String INSERT_INTO_CATEGORIAS = "INSERT INTO categorias (nombre, tipoOperacionId) VALUES (?, ?)";
  private static final String UPDATE_CATEGORIA = "UPDATE categorias SET nombre = ?, tipoOperacionId = ? WHERE id = ?";
  private static final String GET_CATEGORIAS_BY_NAME = "SELECT * FROM categorias WHERE name = ?";
  private static final String GET_CATEGORIAS_BY_ID = "SELECT * FROM categorias WHERE id = ?";
  private static final String DELETE_CATEGORIA = "DELETE FROM categorias WHERE id = ?";
  private final Connection connection;

  public CategoriasDaoImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public CategoriasDto getById(int id) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORIAS_BY_ID)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return mapResultSetToCategoriasDto(resultSet);
      }
      return null;
    } catch (SQLException e) {
      throw new DAOException("Error al obtener el gasto por ID", e);
    }
  }

  @Override
  public Categorias getByName(String name) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORIAS_BY_NAME)) {
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return new Categorias(
            resultSet.getInt("id"),
            resultSet.getString("nombre"),
            resultSet.getInt("tipoOperacionId")
        );
      }
      return null;
    } catch (SQLException e) {
      throw new DAOException("Error al obtener el gasto por Nombre", e);
    }
  }
  @Override
  public List<CategoriasDto> getAll() throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORIAS)) {
      ResultSet resultSet = statement.executeQuery();
      List<CategoriasDto> categoriasDtos = new ArrayList<>();
      // Iteramos el ResultSet para agregar el gasto a la lista
      // y mientras agregamos, realizamo el mapeo de cada item
      while (resultSet.next()) {
        categoriasDtos.add(mapDtoToCategoriasDto(resultSet));
      }
      return categoriasDtos;
    } catch (SQLException e) {
      throw new DAOException("Error al obtener la lista de Categorias", e);
    }
  }

  @Override
  public void insert(CategoriasDto categoriasDto) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CATEGORIAS)) {
      // Mapeo de dto a entidad
      Categorias categoria = mapDtoToCategorias(categoriasDto);

      statement.setString(1, categoria.getNombre());
      statement.setInt(2, categoria.getTipoOperacionId());
      int affectedRows = statement.executeUpdate();
      // Validamos si el resultado de la ejecución del statement no devuelve filas afectadas,
      // entonces hubo un error al insertar en base de datos
      if (affectedRows == 0) {
        throw new DAOException("Error al insertar la Categorìa, ninguna fila fue afectada.");
      }
    } catch (DAOException | SQLException e) {
      assert e instanceof SQLException;
      throw new DAOException("Error al insertar la Categorìa", (SQLException) e);
    }
  }

  @Override
  public void update(CategoriasDto categoriasDto) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORIA)) {

      Categorias categorias = mapDtoToCategorias(categoriasDto);

      statement.setString(1, categorias.getNombre());
      statement.setInt(2, categorias.getTipoOperacionId());
      statement.setInt(3, categorias.getId());
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new DAOException("Error al actualizar la Categorìa, ninguna fila fue afectada.");
      }
    } catch (SQLException e) {
      throw new DAOException("Error al actualizar la Categorìa", e);
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(int id) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORIA)) {
      statement.setInt(1, id);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new DAOException("Error al eliminar la Categorìa, ninguna fila fue afectada.");
      }
    } catch (SQLException e) {
      throw new DAOException("Error al eliminar la Categorìa", e);
    }
  }

  private Categorias mapDtoToCategorias(CategoriasDto categoriasDto) {
    Categorias categorias = new Categorias();
    categorias.setNombre(categoriasDto.getNombre());
    categorias.setId(categoriasDto.getId());
    categorias.setTipoOperacionId(categoriasDto.getTipoOperacionId());
    return categorias;
  }

  private CategoriasDto mapDtoToCategoriasDto(ResultSet resultSet) throws SQLException {
    CategoriasDto categoriasDto = new CategoriasDto();
    categoriasDto.setId(resultSet.getInt("id"));
    categoriasDto.setNombre(resultSet.getString("nombre"));
    categoriasDto.setTipoOperacionId(resultSet.getInt("tipoOperacionId"));
    return categoriasDto;
  }

  private CategoriasDto mapResultSetToCategoriasDto(ResultSet resultSet) throws SQLException {
    CategoriasDto categoriasDto = new CategoriasDto();
    categoriasDto.setId(resultSet.getInt("id"));
    categoriasDto.setNombre(resultSet.getString("nombre"));
    categoriasDto.setTipoOperacionId(resultSet.getInt("tipoOperacionId"));
    return categoriasDto;
  }
}
