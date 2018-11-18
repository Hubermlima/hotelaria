package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
    public void start(Stage stage) throws Exception {
        /*
		Pane root = FXMLLoader.load(getClass().getResource("../view/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("RESERVAS - Trump International Golf & HOTEL MANAUS");
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.setMaximized(true);
        stage.setResizable(false);
        */
		
		Pane root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("RESERVAS - Login");
        stage.setScene(scene);
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        //stage.setMaximized(true);
        //stage.setResizable(false);
		
		
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
