package controlador;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import controlador.GestorErrores.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import tools.Vista;

public class NuevaRecetaCabezaController implements Initializable {

    @FXML
    private Pane pnlFoto;
    @FXML
    private TextField inpTitulo;
    @FXML
    private TextArea inpDescripcion;
    @FXML
    private Button btnDificultadBaja;
    @FXML
    private Button btnDificultadMedia;
    @FXML
    private Button btnDificultadAlta;
    @FXML
    private TextField inpDuracion;
    @FXML
    private Label lblErrorDuracion;
    @FXML
    private TextField inpPersonas;
    @FXML
    private Label lblErrorPersonas;

    public NuevaRecetaCabezaController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setDificultad("baja");
        lblErrorDuracion.setText("");
        lblErrorPersonas.setText("");

    }

    @FXML
    private void subirFoto(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        Node node = (Node) event.getSource();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File archivo = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (archivo != null) {
            setImagen(Vista.getImage(archivo.getAbsolutePath()));
        }

    }

    @FXML
    private void dificultadBaja() {
        setDificultad("baja");
    }

    @FXML
    private void dificultadMedia() {
        setDificultad("media");
    }

    @FXML
    private void dificultadAlta() {
        setDificultad("alta");
    }

    public void setRecetaCabeza(Image imagen, String titulo, String descripcion, String dificultad, int duracion,
            int personas) {

        setImagen(imagen);

        inpTitulo.setText(titulo);
        inpDescripcion.setText(descripcion);

        setDificultad(dificultad);
        inpDuracion.setText(Integer.toString(duracion));
        inpPersonas.setText(Integer.toString(personas));

    }

    public void suscribirErrores(GestorErrores gestorErrores) {

        gestorErrores.suscribir(inpDuracion, lblErrorDuracion,
                new TipoError[] { TipoError.NO_VACIO, TipoError.SOLO_NUMEROS, TipoError.LONGITUD });
        gestorErrores.suscribir(inpPersonas, lblErrorPersonas,
                new TipoError[] { TipoError.NO_VACIO, TipoError.SOLO_NUMEROS, TipoError.LONGITUD });
    }

    private void setDificultad(String dificultad) {

        btnDificultadBaja.getStyleClass().removeAll("nr-btn-baja");
        btnDificultadMedia.getStyleClass().removeAll("nr-btn-media");
        btnDificultadAlta.getStyleClass().removeAll("nr-btn-alta");

        switch (dificultad.toLowerCase()) {
            case "baja":
                btnDificultadBaja.getStyleClass().add("nr-btn-baja");
                break;
            case "media":
                btnDificultadMedia.getStyleClass().add("nr-btn-media");
                break;
            default:
                btnDificultadAlta.getStyleClass().add("nr-btn-alta");
                break;
        }
    }

    private void setImagen(Image imagen) {

        pnlFoto.getChildren().clear();
        pnlFoto.setStyle("-fx-background-color: null");
        pnlFoto.getChildren().add(Vista.getPaneImageRoundAll(imagen));

    }

}