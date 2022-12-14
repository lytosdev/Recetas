package modelo;

import java.sql.SQLException;
import java.util.List;

public class PasoUso {

	static String insertQuery() {
		return "INSERT INTO Pasos "
				+ "(Nombre, IdReceta) "
				+ "VALUES (?, ?)";
	}

	static String selectPorRecetaQuery() {
		return "SELECT Id, Nombre, IdReceta "
				+ "FROM Pasos "
				+ "WHERE IdReceta=?";
	}

	public static RespuestaUso<?> insertar(Paso paso) {

		try (RepoGenerico rg = new RepoGenerico()) {

			int[] resul = rg.insert(insertQuery(), new Object[] {
					paso.getNombre(),
					paso.getIdReceta()
			});

			paso.setId(resul[1]);

			return new RespuestaUso<>("Paso insertado correctamente", RespuestaUso.EXITO);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al insertar el paso", RespuestaUso.ERROR);
		}

	}

	public static RespuestaUso<List<Paso>> selectPorReceta(int idReceta) {

		try (RepoGenerico rg = new RepoGenerico()) {

			List<Paso> resul = rg.getPor(selectPorRecetaQuery(), new Object[] { idReceta }, Paso.class);

			return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
		}

	}

}
