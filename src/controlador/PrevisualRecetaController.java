package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import modelo.Receta;
import tools.Vista;

public class PrevisualRecetaController implements Initializable {

    @FXML
    private Pane pnlFoto;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDescripcion;

    private Receta receta;

    public PrevisualRecetaController(Receta receta) {
        this.receta = receta;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Cargamos la receta
        pnlFoto.getChildren().add(Vista.getPaneImageRoundTop(receta.getImagenJfx()));
        lblTitulo.setText(receta.getTitulo());
        lblDescripcion.setText(receta.getDescripcion());

    }

    @FXML
    private void ver() {

        GestorVistas gestorVistas = PrincipalController.gestorVistas;
        String uri = "/vista/VerReceta.fxml";

        gestorVistas.cerrarVista(uri);
        PropsVista propsVista = gestorVistas.abrirVista(uri, "Detalle receta");

        VerRecetaController verReceta = (VerRecetaController) propsVista.getControlador();
        verReceta.setReceta(receta);

    }

}