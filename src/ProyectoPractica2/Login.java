package ProyectoPractica2;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author Enzo
 */
public class Login extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       try{
           Parent parentRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
           primaryStage.setTitle("login");
           primaryStage.setResizable(false);
           primaryStage.setIconified(false);
           primaryStage.setScene(new Scene(parentRoot));
           primaryStage.show();
       }catch(Exception ex){
           System.err.println(ex.getMessage());
           ex.printStackTrace();  
           System.exit(0);
       } 
    }
    public static void main(String[]args) {
            launch(args);  
    }
}
