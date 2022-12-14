package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Categoria;
import modelo.Ingrediente;
import modelo.IngredienteUso;
import modelo.Paso;
import modelo.PasoUso;
import modelo.Receta;
import modelo.UnidadMedida;
import modelo.Utensilio;
import modelo.UtensilioUso;
import tools.Vista;

public class VerRecetaController implements Initializable {

	@FXML
	private Pane pnlFoto;
	@FXML
	private Label lblTitulo;
	@FXML
	private Label lblDescripcion;
	@FXML
	private Text txtCategoria;
	@FXML
	private Label lblDificultadBaja;
	@FXML
	private Label lblDificultadMedia;
	@FXML
	private Label lblDificultadAlta;
	@FXML
	private Text txtDuracion;
	@FXML
	private Text txtPersonas;
	@FXML
	private VBox pnlIngredientes;
	@FXML
	private VBox pnlUtensilios;
	@FXML
	private VBox pnlPasos;

	public VerRecetaController() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setReceta(Receta receta) {

		// Reseteamos
		pnlFoto.getChildren().clear();
		pnlIngredientes.getChildren().clear();
		pnlUtensilios.getChildren().clear();
		pnlPasos.getChildren().clear();

		Receta r = receta;

		List<Ingrediente> ingredientes = IngredienteUso.selectPorReceta(r.getId()).getObjeto();
		List<Utensilio> utensilios = UtensilioUso.selectPorReceta(r.getId()).getObjeto();
		List<Paso> pasos = PasoUso.selectPorReceta(r.getId()).getObjeto();

		// Cargamos la cabecera
		pnlFoto.getChildren().add(Vista.getPaneImageRoundAll(r.getImagenJfx()));
		lblTitulo.setText(r.getTitulo());
		lblDescripcion.setText(r.getDescripcion());
		Categoria categoria = PrincipalController.categorias.stream().filter(x -> x.getId() == r.getIdCategoria())
				.findFirst()
				.get();
		txtCategoria.setText(categoria.getNombre());
		setDificultad(r.getDificultad());
		txtDuracion.setText(r.getDuracion() + (r.getDuracion() == 1 ? " minuto" : " minutos"));
		txtPersonas.setText(Integer.toString(r.getPersonas()));

		// Cargamos ingredientes
		for (Ingrediente item : ingredientes) {

			UnidadMedida udMedida = PrincipalController.udsMedida.stream()
					.filter(x -> x.getId() == item.getIdUdMedida())
					.findFirst().get();
			String udMedidaNombre = udMedida.getNombre();
			int cantidad = item.getCantidad();

			String uri = "/vista/VerIngrediente.fxml";
			Callback<Class<?>, Object> factory = (Class<?> clazz) -> {
				String txtIngrediente = "";
				if (udMedidaNombre.equalsIgnoreCase("sin ud")) {
					txtIngrediente = cantidad + " " + item.getNombre();
				} else if (cantidad == 0) {
					txtIngrediente = item.getNombre();
				} else {
					txtIngrediente = cantidad + " " + udMedidaNombre + " " + item.getNombre();
				}
				return new VerIngredienteController(txtIngrediente);
			};
			Object[] comp = GestorVistas.cargarVista(uri, factory);

			pnlIngredientes.getChildren().add((Node) comp[0]);
		}

		// Cargamos utensilios
		for (Utensilio item : utensilios) {

			String txtUtensilio = item.getNombre();

			String uri = "/vista/VerUtensilio.fxml";
			Callback<Class<?>, Object> factory = (Class<?> clazz) -> new VerUtensilioController(txtUtensilio);
			Object[] comp = GestorVistas.cargarVista(uri, factory);

			pnlUtensilios.getChildren().add((Node) comp[0]);
		}

		// Cargamos pasos
		for (Paso item : pasos) {

			int numPaso = pasos.indexOf(item) + 1;
			String txtPaso = item.getNombre();

			String uri = "/vista/VerPaso.fxml";
			Callback<Class<?>, Object> factory = (Class<?> clazz) -> new VerPasoController(numPaso, txtPaso);
			Object[] comp = GestorVistas.cargarVista(uri, factory);

			pnlPasos.getChildren().add((Node) comp[0]);
		}

	}

	private void setDificultad(String dificultad) {

		lblDificultadBaja.getStyleClass().removeAll("vr-lbl-baja");
		lblDificultadMedia.getStyleClass().removeAll("vr-lbl-media");
		lblDificultadAlta.getStyleClass().removeAll("vr-lbl-alta");

		switch (dificultad.toLowerCase()) {
			case "baja":
				lblDificultadBaja.getStyleClass().add("vr-lbl-baja");
				break;
			case "media":
				lblDificultadMedia.getStyleClass().add("vr-lbl-media");
				break;
			default:
				lblDificultadAlta.getStyleClass().add("vr-lbl-alta");
				break;
		}

	}

}