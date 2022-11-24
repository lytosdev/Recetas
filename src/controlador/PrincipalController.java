package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane pnlVistas;
    @FXML
    private Text txtTitulo;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnRecetas;
    @FXML
    private Button btnSubirReceta;
    @FXML
    private Button btnPanelAdmin;

    public static GestorVistas gestorVistas;

    public PrincipalController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gestorVistas = new GestorVistas(pnlVistas, txtTitulo);
    }

    @FXML
    private void abrirInicio() {
        System.out.println("Abrir inicio");
        gestorVistas.abrirVista("/vista/Bienvenida.fxml", "Inicio");
    }

    @FXML
    private void abrirRecetas() {
        System.out.println("Abrir recetas");
        gestorVistas.abrirVista("/vista/Recetas.fxml", "Recetas");
    }

    @FXML
    private void abrirSubirReceta() {
        System.out.println("Abrir subir receta");
        gestorVistas.abrirVista("/vista/SubirReceta.fxml", "Subir receta");
    }

    @FXML
    private void abrirPanelAdmin() {
        System.out.println("Abrir panel admin");
        gestorVistas.abrirVista("/vista/RecetaDetalle.fxml", "Detalle de la receta");
    }

}