package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.Ingrediente;
import modelo.Receta;
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

    // private AnchorPane getPaso(String texto) {

    // }

    // private AnchorPane getIngrediente() {

    // }

    // private AnchorPane getUtensilio() {

    // }

    public void cargar(Receta receta) {

        Pane foto = Vista.getPane(receta.getImagen(), Receta.alturaFoto, Receta.anchuraFoto, 0.1, 0.1, 0.1, 0.1);
        pnlFoto.getChildren().add(foto);

        lblTitulo.setText(receta.getTitulo());
        lblDescripcion.setText(receta.getDescripcion());

        txtCategoria.setText(receta.getCategoria());
        setDificultad(receta.getDificultad());
        txtDuracion.setText(receta.getDuracion() + (receta.getDuracion() == 1 ? " minuto" : " minutos"));
        txtPersonas.setText(Integer.toString(receta.getPersonas()));

        // Cargamos ingredientes
        Ingrediente[] ingredientes = receta.getIngredientes();
        for (int i = 0; i < ingredientes.length; i++) {
            Ingrediente ing = ingredientes[i];
            String udMedida = ing.getUdMedida();
            int cantidad = ing.getCantidad();
            String uri = "/vista/VerIngrediente.fxml";
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> {
                String txtIngrediente = "";
                if (udMedida.equalsIgnoreCase("ud")) {
                    txtIngrediente = cantidad + " " + ing.getNombre();
                } else if (cantidad == 0) {
                    txtIngrediente = ing.getNombre();
                } else {
                    txtIngrediente = cantidad + " " + udMedida + " " + ing.getNombre();
                }
                return new VerIngredienteController(txtIngrediente);
            };
            Object[] arrIngrediente = GestorVistas.cargarVista(uri, factory);
            pnlIngredientes.getChildren().add((Node) arrIngrediente[0]);
        }

        // Cargamos utensilios
        String[] utensilios = receta.getUtensilios();
        for (int i = 0; i < utensilios.length; i++) {
            String txtUtensilio = utensilios[i];
            String uri = "/vista/VerUtensilio.fxml";
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new VerUtensilioController(txtUtensilio);
            Object[] arrUtensilio = GestorVistas.cargarVista(uri, factory);
            pnlUtensilios.getChildren().add((Node) arrUtensilio[0]);
        }

        // Cargamos pasos
        String[] pasos = receta.getPasos();
        for (int i = 0; i < pasos.length; i++) {
            int numPaso = i + 1;
            String txtPaso = pasos[i];
            String uri = "/vista/VerPaso.fxml";
            Callback<Class<?>, Object> factory = (Class<?> clazz) -> new VerPasoController(numPaso, txtPaso);
            Object[] arrPaso = GestorVistas.cargarVista(uri, factory);
            pnlPasos.getChildren().add((Node) arrPaso[0]);
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