package ProyectoPractica2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author Enzo
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtpassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void actionLogin(ActionEvent event) {
        Window owner = txtUser.getScene().getWindow();
        System.out.println(txtUser.getText());
        System.out.println(txtpassword.getText());
        
        if(txtUser.getText().isEmpty()){
            alerta(Alert.AlertType.ERROR, owner, "Por favor ingrese un usuario valido","Error!");
            return;
        }
        if(txtpassword.getText().isEmpty()){
            alerta(Alert.AlertType.ERROR, owner, "Por favor ingrese una contrasenia valida","Error!");
            return;
        }
        String username = txtUser.getText();
        String password = txtpassword.getText();
        
        JDBCoracle jdbcoracle = new JDBCoracle();
        boolean flag = jdbcoracle.validar(username, password);
        if(!flag){
            infoCuadro("Por favor ingrese un usuario y contrasenia correcta", null, "Fallo");   
        }else{
            infoCuadro("Ingreso exitoso!", null, "Exito");
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("POS | Menu");
            stage.setScene(new Scene(root));
            MenuController controller = (MenuController) fxmlLoader.getController();
            //controller.setUsername(username);
            stage.show();
            
        } catch (Exception ex) {
            //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
            ex.printStackTrace();  
            System.exit(0);
        }
    }
    //cuadro de advertencia si el usuario ha ingresado o si es incorrecto los datos 
    public static void infoCuadro(String infoMessage, String hearderText, String tittle){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(tittle);
        alert.setHeaderText(hearderText);
        alert.showAndWait();
    }
    public static void alerta(AlertType alertType,Window owner, String message, String tittle){
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setTitle(tittle);
        alert.setHeaderText(null);
        alert.initOwner(owner);
        alert.show();
    }
    
}
