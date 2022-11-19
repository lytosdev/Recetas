
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

public class BienvenidaController implements Initializable {

    @FXML
    private FlowPane pnlUltimasRecetas;

    public BienvenidaController() {
  
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        pnlUltimasRecetas.getChildren().addAll(get(), get(), get(), get(), get(), get(), get());
    }

    private Node get() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/templates/RecetaVistaPrevia.fxml"));
		Node vista = null;
		
		try {
            vista = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return vista;
    }

}