package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

public class InicioController implements Initializable {

    @FXML
    private FlowPane pnlUltimasRecetas;

    public InicioController() {
  
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        String uri = "/vista/PrevisualReceta.fxml";

        for (int i = 0; i < 10; i++) {
            pnlUltimasRecetas.getChildren().add((Node)GestorVistas.cargarVista(uri)[0]);
        }
 
    }

}