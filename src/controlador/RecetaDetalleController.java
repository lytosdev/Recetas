package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class RecetaDetalleController implements Initializable {

    @FXML
    private VBox pnlIngredientes;
    @FXML
    private VBox pnlUtensilios;
    @FXML
    private VBox pnlPasos;

    public RecetaDetalleController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    private AnchorPane getPaso(String texto) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/NuevoPaso.fxml"));
        fxmlLoader.setControllerFactory((Class<?> clazz) -> new NuevoPasoController(this::borrarPaso));

        AnchorPane vista = null;

        try {
            vista = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NuevoPasoController paso = fxmlLoader.getController();
        vista.setUserData(paso);
        paso.txtNumPaso.setText(texto);

        return vista;
    }

    private void borrarPaso(NuevoPasoController obj) {

        ObservableList<Node> pasos = pnlPasos.getChildren();
        Node pasoSelec = null;

        for (Node paso : pasos) {
            // Actualizamos el número de paso a partir del borrado
            if (pasoSelec != null) {
                int numPaso = pasos.indexOf(paso);
                NuevoPasoController pasoController = (NuevoPasoController) paso.getUserData();
                pasoController.txtNumPaso.setText("Paso " + numPaso);
            }
            // Guardamos el paso que queremos borrar
            if (paso.getUserData().equals(obj)) {
                pasoSelec = paso;
            }
        }

        pnlPasos.getChildren().remove(pasoSelec);

    }

    private AnchorPane getIngrediente() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/NuevoIngrediente.fxml"));
        fxmlLoader.setControllerFactory((Class<?> clazz) -> new NuevoIngredienteController(this::borrarIngrediente));

        AnchorPane vista = null;

        try {
            vista = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NuevoIngredienteController ingrediente = fxmlLoader.getController();
        vista.setUserData(ingrediente);

        return vista;
    }

    private void borrarIngrediente(NuevoIngredienteController obj) {

        ObservableList<Node> ingredientes = pnlIngredientes.getChildren();
        Node ingredienteSelec = null;

        for (Node ingrediente : ingredientes) {
            // Guardamos el ingrediente que queremos borrar
            if (ingrediente.getUserData().equals(obj)) {
                ingredienteSelec = ingrediente;
                break;
            }
        }

        pnlIngredientes.getChildren().remove(ingredienteSelec);

    }

    private AnchorPane getUtensilio() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/NuevoUtensilio.fxml"));
        fxmlLoader.setControllerFactory((Class<?> clazz) -> new NuevoUtensilioController(this::borrarUtensilio));

        AnchorPane vista = null;

        try {
            vista = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NuevoUtensilioController utensilio = fxmlLoader.getController();
        vista.setUserData(utensilio);

        return vista;
    }

    private void borrarUtensilio(NuevoUtensilioController obj) {

        ObservableList<Node> utensilios = pnlUtensilios.getChildren();
        Node utensilioSelec = null;

        for (Node utensilio : utensilios) {
            // Guardamos el ingrediente que queremos borrar
            if (utensilio.getUserData().equals(obj)) {
                utensilioSelec = utensilio;
                break;
            }
        }

        pnlUtensilios.getChildren().remove(utensilioSelec);

    }

}