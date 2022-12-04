package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class LoginPanelAdminController implements Initializable {

    @FXML
    private PasswordField inpContrasenia;
    @FXML
    private Text txtError;

    public LoginPanelAdminController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        inpContrasenia.setText("");
        txtError.setText("");
    }

    @FXML
    private void entrar() {

        if (inpContrasenia.getText().equals("123")) {
            GestorVistas gestorVistas = PrincipalController.gestorVistas;
            gestorVistas.abrirVista("/vista/PanelAdmin.fxml", "Panel administrador");
            inpContrasenia.setText("");
            txtError.setText("");
        } else {
            txtError.setText("La contraseña es incorrecta");
        }
    }

    @FXML
    private void inpContraseniaChanged() {
        txtError.setText("");
    }

}