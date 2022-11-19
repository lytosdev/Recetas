
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    public PrincipalController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void abrirInicio() {
        System.out.println("Abrir inicio");
        renderVista("/Bienvenida.fxml", "Inicio");
    }

    @FXML
    private void abrirRecetas() {
        System.out.println("Abrir recetas");
    }

    @FXML
    private void abrirSubirReceta() {
        System.out.println("Abrir subir receta");
    }

    @FXML
    private void abrirPanelAdmin() {
        System.out.println("Abrir panel admin");
    }

    private Node renderVista(String uri, String titulo) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(uri));
		Node vista = null;

		try {

			vista = fxmlLoader.load();
            txtTitulo.setText(titulo);

		} catch (IOException e) {
			e.printStackTrace();
		}
        //BienvenidaController controller = fxmlLoader.getController();

		AnchorPane.setTopAnchor(vista, 0.0);
		AnchorPane.setLeftAnchor(vista, 0.0);
		AnchorPane.setRightAnchor(vista, 0.0);
		AnchorPane.setBottomAnchor(vista, 0.0);
		pnlVistas.getChildren().add(0, vista);

		return vista;
	}

}