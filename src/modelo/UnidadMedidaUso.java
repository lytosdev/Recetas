package modelo;

import java.sql.SQLException;
import java.util.List;

public class UnidadMedidaUso {

  static String insertQuery() {
    return "INSERT INTO UnidadesMedida "
        + "(Nombre) "
        + "VALUES (?)";
  }

  static String selectQuery() {
    return "SELECT Id, Nombre "
        + "FROM UnidadesMedida";
  }

  static String selectPorIdQuery() {
    return "SELECT Id, Nombre "
        + "FROM UnidadesMedida "
        + "WHERE Id=?";
  }

  public static RespuestaUso<?> insertar(UnidadMedida unidadMedida) {

    try (RepoGenerico rg = new RepoGenerico()) {

      int[] resul = rg.insert(insertQuery(), new Object[] {
          unidadMedida.getNombre()
      });

      unidadMedida.setId(resul[1]);

      return new RespuestaUso<>("Unidad de medida insertada correctamente", RespuestaUso.EXITO);

    } catch (SQLException e) {
      e.printStackTrace();
      return new RespuestaUso<>("Error al insertar la unidad de medida", RespuestaUso.ERROR);
    }

  }

  public static RespuestaUso<List<UnidadMedida>> select() {

    try (RepoGenerico rg = new RepoGenerico()) {

      List<UnidadMedida> resul = rg.getPor(selectQuery(), null, UnidadMedida.class);

      return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

    } catch (SQLException e) {
      e.printStackTrace();
      return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
    }

  }

  public static RespuestaUso<UnidadMedida> selectPorId(int id) {

    try (RepoGenerico rg = new RepoGenerico()) {

      UnidadMedida unidadMedida = new UnidadMedida();
      rg.getPor(selectPorIdQuery(), new Object[] { id }, unidadMedida);

      return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, unidadMedida);

    } catch (SQLException e) {
      e.printStackTrace();
      return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
    }

  }

}
