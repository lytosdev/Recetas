package modelo;

import java.sql.SQLException;
import java.util.List;

public class IngredienteUso {

	static String insertQuery() {
		return "INSERT INTO Ingredientes "
				+ "(Nombre, Cantidad, IdUdMedida, IdReceta) "
				+ "VALUES (?, ?, ?, ?)";
	}

	static String selectPorRecetaQuery() {
		return "SELECT Id, Nombre, Cantidad, IdUdMedida, IdReceta "
				+ "FROM Ingredientes "
				+ "WHERE IdReceta=?";
	}

	public static RespuestaUso<?> insertar(Ingrediente ingrediente) {

		try (RepoGenerico rg = new RepoGenerico()) {

			int[] resul = rg.insert(insertQuery(), new Object[] {
					ingrediente.getNombre(),
					ingrediente.getCantidad(),
					ingrediente.getIdUdMedida(),
					ingrediente.getIdReceta()
			});

			ingrediente.setId(resul[1]);

			return new RespuestaUso<>("Ingrediente insertado correctamente", RespuestaUso.EXITO);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al insertar el ingrediente", RespuestaUso.ERROR);
		}

	}

	public static RespuestaUso<List<Ingrediente>> selectPorReceta(int idReceta) {

		try (RepoGenerico rg = new RepoGenerico()) {

			List<Ingrediente> resul = rg.getPor(selectPorRecetaQuery(), new Object[] { idReceta }, Ingrediente.class);

			return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
		}

	}

}
