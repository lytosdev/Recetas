package modelo;

import java.sql.SQLException;
import java.util.List;

public class UtensilioUso {

	static String insertQuery() {
		return "INSERT INTO Utensilios "
				+ "(Nombre, IdReceta) "
				+ "VALUES (?, ?)";
	}

	static String selectPorRecetaQuery() {
		return "SELECT Id, Nombre, IdReceta "
				+ "FROM Utensilios "
				+ "WHERE IdReceta=?";
	}

	public static RespuestaUso<?> insertar(Utensilio utensilio) {

		try (RepoGenerico rg = new RepoGenerico()) {

			int[] resul = rg.insert(insertQuery(), new Object[] {
					utensilio.getNombre(),
					utensilio.getIdReceta()
			});

			utensilio.setId(resul[1]);

			return new RespuestaUso<>("Utensilio insertado correctamente", RespuestaUso.EXITO);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al insertar el utensilio", RespuestaUso.ERROR);
		}

	}

	public static RespuestaUso<List<Utensilio>> selectPorReceta(int idReceta) {

		try (RepoGenerico rg = new RepoGenerico()) {

			List<Utensilio> resul = rg.getPor(selectPorRecetaQuery(), new Object[] { idReceta }, Utensilio.class);

			return new RespuestaUso<>("Búsqueda exitosa", RespuestaUso.EXITO, resul);

		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaUso<>("Error al realizar la búsqueda", RespuestaUso.ERROR);
		}

	}

}
