package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane pnlVistas;
    @FXML
    private Text txtCabecera;

    public static GestorVistas gestorVistas;

    public PrincipalController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gestorVistas = new GestorVistas(pnlVistas, txtCabecera);
    }

    @FXML
    private void abrirInicio() {
        gestorVistas.abrirVista("/vista/Bienvenida.fxml", "Inicio");
    }

    @FXML
    private void abrirRecetas() {
        gestorVistas.abrirVista("/vista/Recetas.fxml", "Recetas");
    }

    @FXML
    private void abrirSubirReceta() {
        gestorVistas.abrirVista("/vista/SubirReceta.fxml", "Subir receta");
    }

    @FXML
    private void abrirPanelAdmin() {
        gestorVistas.abrirVista("/vista/RecetaDetalle.fxml", "Detalle de la receta");
    }

}