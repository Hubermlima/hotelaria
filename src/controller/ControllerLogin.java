package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import basedados.TableUsuarioLoad;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Usuario;
import javafx.scene.control.CheckBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class ControllerLogin implements Initializable {

	@FXML Pane paneLogin;
	@FXML CheckBox checkBoxLembre;
	@FXML Button buttonLogin;
	@FXML Button buttonRegistrar;
	@FXML TextField textFieldLogin;
	@FXML PasswordField textFieldSenha;
	@FXML Label labelTitulo;
	@FXML Label labelAviso;
    private ObservableList<Usuario> lista;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// botao Confirmar desabilitado enquanto existir um dos campos abaixo em branco
        buttonLogin.disableProperty().bind(textFieldLogin.textProperty().isEmpty().or
                  (textFieldSenha.textProperty().isEmpty()));
       
		lista=TableUsuarioLoad.load();
		
	}

	@FXML public void onActionLogin() throws IOException {
		// Pesquisa login, senha
		labelAviso.setVisible(true);
		for (Usuario usuario : lista) {
			if (textFieldLogin.getText().equals(usuario.getLogin()) && textFieldSenha.getText().equals(usuario.getSenha())){
				
            	/*// Esta é uma forma com a notacao fx: controller.ControllerTelaPrincipal

				Pane root = FXMLLoader.load(getClass().getResource("../view/TelaPrincipal.fxml"));
		        Scene scene = new Scene(root);
		        Stage stagePrincipal = new Stage();
				stagePrincipal .setTitle("RESERVAS - Trump International Golf & HOTEL MANAUS - " + "Usuário: " + textFieldLogin.getText());
				stagePrincipal.setScene(scene);
				stagePrincipal.setMinHeight(400);
				stagePrincipal.setMinWidth(400);
				stagePrincipal.setMaximized(true);
				stagePrincipal.setResizable(false);
				stagePrincipal.show();
            	 */		
				
				// Esta é outra forma, sem a citacao acima, porém, já dá pra mandar alguma coisa pra outra tela, por meio do
				// construtor na outra classe de destino
				
				Stage stage = new Stage();
		    	FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/TelaPrincipal.fxml"));
		    	fxml.setController(new ControllerTelaPrincipal(this)); // criar um construtor na classe destino 	
		    	Parent root;
				try {
					root = fxml.load();
					Scene scene = new Scene(root,323,407);
					stage.setScene(scene);
					stage.setTitle("RESERVAS - Trump International Golf & HOTEL MANAUS - " + "Usuário: " + textFieldLogin.getText());
					stage.setMaximized(true);
					stage.setResizable(false);
					
					//Desabilitar o botão fechar da janela do windows
					stage.setOnCloseRequest(new EventHandler<WindowEvent>() { 
						@Override 
						public void handle(final WindowEvent windowEvent) { 
							windowEvent.consume();
						} 
					});
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}	else {
				
					labelAviso.setText("Login/senha inválida!");
			}
		}
	}

	@FXML public void onActionRegistrar() {}

	public ObservableList<Usuario> getLista() {
		return lista;
	}

	public void setLista(ObservableList<Usuario> lista) {
		this.lista = lista;
	}

    
}
