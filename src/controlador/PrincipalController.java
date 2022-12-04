package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrincipalController implements Initializable {

    @FXML
    private VBox principal;
    @FXML
    private AnchorPane pnlVistas;
    @FXML
    private Text txtCabecera;
    @FXML
    private Region regModo;

    public static GestorVistas gestorVistas;
    public static boolean modo = false; // false = modo claro

    public PrincipalController() {
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gestorVistas = new GestorVistas(pnlVistas, txtCabecera);
    }

    @FXML
    private void abrirInicio() {
        gestorVistas.abrirVista("/vista/Inicio.fxml", "Inicio");
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
        gestorVistas.abrirVista("/vista/LoginPanelAdmin.fxml", "Login panel administrador");
    }

    @FXML
    private void cambiarModo() {
      
        if (!modo) {
            regModo.getStyleClass().clear();
            regModo.getStyleClass().add("icn-modo-claro");
            principal.getStylesheets().add("/vista/css/modoOscuro.css");
            principal.getStylesheets().removeAll("/vista/css/modoClaro.css");
        } else {
            regModo.getStyleClass().clear();
            regModo.getStyleClass().add("icn-modo-oscuro");
            principal.getStylesheets().add("/vista/css/modoClaro.css");
            principal.getStylesheets().removeAll("/vista/css/modoOscuro.css");
        }

        modo = !modo;
    }

}