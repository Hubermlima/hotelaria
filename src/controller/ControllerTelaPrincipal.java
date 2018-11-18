package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;

public class ControllerTelaPrincipal implements Initializable {

	@FXML private AnchorPane anchorPane;
	@FXML private MenuItem menuItemNovo;
	@FXML private MenuItem menuItemAbrir;
	@FXML private MenuItem menuItemSalvar;
	@FXML private MenuItem menuItemExcluir;
	@FXML private MenuItem menuItemImprimir;
	@FXML private MenuItem menuItemSobre;
	@FXML private MenuItem menuItemSair;
	@FXML private MenuItem menuItemCategoria;
	@FXML private MenuItem menuItemServico;
	@FXML private MenuItem menuItemTipoPromocional;
	@FXML private MenuItem menuItemPais;
	@FXML private MenuItem menuItemAgenciaViagem;
	@FXML private MenuItem menuItemUnidadesHabitacionais;
	@FXML MenuItem menuItemTorres;
	@FXML MenuItem menuItemHospede;
	@FXML MenuBar menuBarHotelaria;

	
	ControllerLogin controleLogin;
    public ControllerTelaPrincipal(ControllerLogin controleLogin ) {
    	this.controleLogin = controleLogin;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	public MenuBar getMenuBarHotelaria() {
		return menuBarHotelaria;
	}

	public void setMenuBarHotelaria(MenuBar menuBarHotelaria) {
		this.menuBarHotelaria = menuBarHotelaria;
	}

	@FXML public void onActionMenuItemNovo() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Reserva.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
	}

	@FXML public void onActionMenuItemAbrir() throws IOException {}

	@FXML public void onActionMenuItemSalvar() throws IOException {}

	@FXML public void onActionMenuItemExcluir() throws IOException {}

	@FXML public void onActionMenuItemImprimir() throws IOException {}

	@FXML public void onActionMenuItemSobre() throws IOException {}

	@FXML public void onActionMenuItemSair() throws IOException {
	        Platform.exit();
	}

	@FXML public void onActionCategoria() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Categorias.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
 
	}

	@FXML public void onActionUnidadesHabitacionais() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/UnidadeHabitacional.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
 
	}

	@FXML public void onActionTorres() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Torre.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
 
	} 

	@FXML public void onActionServico() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Servico.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
	}

	@FXML public void onActionAgenciaViagem() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/AgenciaViagem.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
	}

	@FXML public void onActionTipoPromocional() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/TipoPromocional.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
	}

	@FXML public void onActionPais() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Pais.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
	}

	@FXML public void onActionHospede() throws IOException {
		BorderPane a = (BorderPane) FXMLLoader.load(getClass().getResource("../view/Hospede.fxml"));
 		AnchorPane.setTopAnchor(a, 0.0);
 		AnchorPane.setBottomAnchor(a, -350.0);
 		AnchorPane.setLeftAnchor(a, 0.0);
 		AnchorPane.setRightAnchor(a, 0.0);
        anchorPane.getChildren().setAll(a);
	} 
}
