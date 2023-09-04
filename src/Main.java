import config.JdbcConfiguration;
import dao.CategoriasDao;
import dao.GestorDeCuentaDao;
import dao.TipoOperacionDao;
import dao.TransaccionesDao;
import dao.dto.CategoriasDto;
import dao.dto.GestorDeCuentaDto;
import dao.dto.TipoOperacionDto;
import dao.dto.TransaccionesDto;
import dao.impl.CategoriasDaoImpl;
import dao.impl.GestorDeCuentaImpl;
import dao.impl.TipoOperacionImpl;
import dao.impl.TransaccionesDaoImpl;
import exceptions.DAOException;
import exceptions.InvalidGastoException;
import intefaces.GastosMontoValidator;
import intefaces.GastosMontoValidatorImpl;
import intefaces.PanelDeControl;
import intefaces.PanelDeControlImpl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);
  static  List<GestorDeCuentaDto> gestorDeCuentaDtos;
  static List<CategoriasDto> categoriasDtos;
  static  List<TipoOperacionDto> tipoOperacionDtos;
  static List<TransaccionesDto> transaccionesDtos;
  static TransaccionesDao transaccionesDao;
  static CategoriasDao categoriasDao;
  static GestorDeCuentaDao gestorDeCuentaDao;
  public static void main(String[] args) {

    // Conexión con la base de datos
    try (Connection connection = JdbcConfiguration.getDBConnection()) {
      // Crear una declaración SQL
      Statement statement = connection.createStatement();

      transaccionesDao = new TransaccionesDaoImpl(connection);
      transaccionesDtos = transaccionesDao.getAll();

      categoriasDao = new CategoriasDaoImpl(connection);
      gestorDeCuentaDao = new GestorDeCuentaImpl(connection);

      CategoriasDao categoriasDao = new CategoriasDaoImpl(connection);
      TipoOperacionDao tipoOperacionDao = new TipoOperacionImpl(connection);

      gestorDeCuentaDtos = gestorDeCuentaDao.getAll();
      categoriasDtos = categoriasDao.getAll();
      tipoOperacionDtos = tipoOperacionDao.getAll();

      int opcion;

      do {
        opcion = menu();
        switch (opcion) {
          case 0:
            break;
          case 1:
            nuevaTransaccion();
            break;
          case 2:
            nuevaCategoria();
            break;
          case 3:
            calcularGastos(transaccionesDtos, categoriasDtos);
            break;
          case 4:
            listarTransacciones(transaccionesDtos);
            break;
          case 5:
            listarCategorias(categoriasDtos);
            break;
        }
      } while (opcion != 0);

      // Cerrar la conexión
      statement.close();
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static int menu() {
    int opcionMenu;
    System.out.println("Menú");
    System.out.println("1 - Ingresar Nueva Transacción");
    System.out.println("2 - Ingresar Nueva Categoría");
    System.out.println("3 - Calcular Gastos");
    System.out.println("4 - Listar Transacciones");
    System.out.println("5 - Listar Categorias");
    System.out.println("0 - Salir");
    opcionMenu = sc.nextInt();
    sc.nextLine();

    return opcionMenu;
  }

  public static void nuevaTransaccion() throws InvalidGastoException, DAOException {
    boolean bandera = false;
    double monto;

    try {
      TransaccionesDto transaccionesDto = new TransaccionesDto();
      GastosMontoValidator gastosMontoValidator = new GastosMontoValidatorImpl();

      listarGestorDeCuentaDtoPLana(gestorDeCuentaDtos);
      System.out.print("Ingrese la cuenta: ");
      int cuenta = sc.nextInt();
      sc.nextLine();

      listarCategoriasPlana(categoriasDtos);
      System.out.print("Ingrese la categoría: ");
      int categoria = sc.nextInt();
      sc.nextLine();

      System.out.print("Ingrese la fecha (dd-mm-yyyy): ");
      String fecha = sc.nextLine();

      while (true) {
        System.out.print("Ingrese el monto: ");
        monto = sc.nextDouble();
        if (gastosMontoValidator.validarMonto(monto)) {
          break;
        }
        sc.nextLine();
      }

      transaccionesDto.setGestorDeCuentaId(cuenta);
      transaccionesDto.setCategoriaId(categoria);
      transaccionesDto.setFecha(fecha);
      transaccionesDto.setMonto(monto);
      transaccionesDao.insert(transaccionesDto);
      transaccionesDtos = transaccionesDao.getAll();
      calcularGastos(transaccionesDtos, categoriasDtos);
    }catch (DAOException e){
      System.out.println("No se pudo registrar la transaccióm: " + e.getMessage());
    }
  }

  public static void nuevaCategoria() throws InvalidGastoException, DAOException {
    try {
      CategoriasDto categoriasDto = new CategoriasDto();
      GastosMontoValidator gastosMontoValidator = new GastosMontoValidatorImpl();

      System.out.print("Ingrese el nombre de la Categoría: ");
      String nombre = sc.nextLine();

      listarTipoOperacionPLana(tipoOperacionDtos);
      System.out.print("Ingrese el Tipo de Operación: ");
      int operacion = sc.nextInt();
      sc.nextLine();

      categoriasDto.setNombre(nombre);
      categoriasDto.setTipoOperacionId(operacion);
      categoriasDao.insert(categoriasDto);
      categoriasDtos = categoriasDao.getAll();
    } catch (Exception e){
      System.out.println("No se pudo registrar la transaccióm: " + e.getMessage());
    }
  }

  public static void calcularGastos( List<TransaccionesDto> transaccionesDtos, List<CategoriasDto> categoriasDtos){
    PanelDeControl cpanel = new PanelDeControlImpl();
    System.out.println("Total Gastos: "
        + cpanel.calcularTotalGastos(transaccionesDtos, categoriasDtos));
  }
  public static void listarTransacciones(List<TransaccionesDto> transaccionesDtos) throws DAOException {
    if (transaccionesDtos.size() > 0) {
      for (TransaccionesDto t : transaccionesDtos) {
        //p.toString();
        System.out.println("Cuenta: " + t.getGestorDeCuentaId() + " - " + gestorDeCuentaDao.getById(t.getGestorDeCuentaId()).getDescripcion());
        System.out.println("Categoría: " + t.getCategoriaId() + " - " + categoriasDao.getById(t.getCategoriaId()).getNombre());
        System.out.println("Fecha: " + t.getFecha());
        System.out.println("Monto: " + t.getMonto());
        System.out.println("-----------------");
      }
    } else {
      System.out.println("No hay transacciones");
    }
  }

  public static void listarCategorias(List<CategoriasDto> categoriasDtos) throws DAOException {
    System.out.println("Categorías: ");
    for (CategoriasDto p : categoriasDtos) {
      System.out.println("Nombre: " + p.getNombre());
      System.out.println("Tipo Operación: " + p.getTipoOperacionId());
      System.out.println("-----------------");
    }
    System.out.println("");
  }

  public static void listarCategoriasPlana(List<CategoriasDto> categoriasDtos) {
    System.out.print("Categorías: ");
    for (CategoriasDto p : categoriasDtos) {
      System.out.print(p.getId() + " - " + p.getNombre() + " / ");
    }
    System.out.println("");
  }

  public static void  listarGestorDeCuentaDtoPLana(List<GestorDeCuentaDto> gestorDeCuentaDtos){
    System.out.print("Cuentas: ");
    for (GestorDeCuentaDto c : gestorDeCuentaDtos) {
      System.out.print(c.getId() + " - " + c.getDescripcion() + " / ");
    }
    System.out.println("");
  }

  public static void listarTipoOperacionPLana(List<TipoOperacionDto> tipoOperacionDtos){
    System.out.print("Operciones: ");
    for (TipoOperacionDto t : tipoOperacionDtos) {
      System.out.print(t.getId() + " - " + t.getNombre() + " / ");
    }
    System.out.println("");
  }
}
