package controller;

import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import basedados.TablePaisLoad;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Pais;

public class ControllerPais implements Initializable{

	@FXML private TableView<Pais> tableViewPais;
	@FXML private TableColumn<Pais, Integer> tableColumnIdentificacao;
	@FXML private TableColumn<Pais, String> tableColumnNome;
	
	@FXML private Button btNovaPais;
	@FXML private Button btExcluirPais;
	@FXML private Button btConfirmarPais;
	@FXML private Button btCancelarPais;
	@FXML private TextField textFieldIdentificacao;
	@FXML private TextField textFieldNome;
	@FXML private TextField textFieldPesquisa;
	@FXML private Label labelStatus;
	
	ObservableList<Pais> lista;
	FilteredList<Pais> listaFiltrada;
	Integer flag, selectedIndex;
	
	Alert alert;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	    	
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de inclusão/alteração de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
         
    	// Popular observaalelist Pais
		lista = TablePaisLoad.load();
		
		// Justificar colunas
		tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
	    
		// popular Tableview
		tableViewPais.getItems().clear();
   		tableViewPais.setItems(lista); 
   		lista.sort(Comparator.comparing(Pais::getNome));
   		
   		// Detalhar o primeiro item do Tableview
   		selectedIndex = 0;
   		tableViewPais.getSelectionModel().select(selectedIndex);
   		Pais Pais = tableViewPais.getSelectionModel().getSelectedItem();
   		validarCampos();
	    if (Pais!=null) {
	    	flag=2;
			textFieldIdentificacao.setText(String.valueOf(Pais.getId()));
			textFieldNome.setText(Pais.getNome());
			flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
	    }
	    
	 // 1. Envolva o ObservableList em uma FilteredList (inicialmente exiba todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);
	    

    }
    
	// Incluir e gravar uma nova Pais
	
	@FXML public void onNovaPais() {   // Nova e gravar Pais
	
		// Preparar tela
		if (btNovaPais.getText().equals("Novo país")) {            
			    flag = 1; // modo de inclusao de Pais
			    botoesOnOf();
			    btNovaPais.setText("Gravar");
	            
				textFieldIdentificacao.setText(TablePaisLoad.getMaxIdPais(lista));
				textFieldNome.setText("");
				textFieldNome.requestFocus(); // O cursor se posiciona em nome	  
		  }
		else { // Efetuar gravacao da nova Pais
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	
					flag = 0;
					btNovaPais.setText("Novo país");
					habilitarTodosControles();
				       
					lista.add(new Pais(Integer.valueOf(textFieldIdentificacao.getText()),
			                textFieldNome.getText()));
					lista.sort(Comparator.comparing(Pais::getNome));
					btNovaPais.setText("Novo país");
				}
		}
	}

	@FXML public void onAtualizar() { // Atualizar
		
		alert.setHeaderText("Confirmação de alteração de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				habilitarTodosControles();
		        lista.set(selectedIndex, new Pais(Integer.valueOf(textFieldIdentificacao.getText()),
		        		textFieldNome.getText()));		
		        lista.sort(Comparator.comparing(Pais::getNome));
		        tableViewPais.getSelectionModel().select(selectedIndex);
			}
	}
	
	@FXML public void onExcluirPais() {
	
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewPais.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					textFieldIdentificacao.setText("");
					textFieldNome.setText("");
				}
		}		
			
     }
	
	@FXML public void onCancelar() {
		
		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
	
				flag = 0;
				btNovaPais.setText("Novo país");
				habilitarTodosControles();
				tableViewPais.getSelectionModel().select(selectedIndex);
		   		Pais Pais = tableViewPais.getSelectionModel().getSelectedItem();
			    if (Pais!=null) {
			    	flag=2;
					textFieldIdentificacao.setText(String.valueOf(Pais.getId()));
					textFieldNome.setText(Pais.getNome());
					
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
			    }
		}	    
	}
	
	@FXML public void onKeyPressedTableView() {
		tableViewPais.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
		    	selectedIndex = tableViewPais.getSelectionModel().getSelectedIndex();
		    	Pais Pais = tableViewPais.getSelectionModel().getSelectedItem();
			    if (Pais!=null) {
			    	flag=2;
					textFieldIdentificacao.setText(String.valueOf(Pais.getId()));
					textFieldNome.setText(Pais.getNome());
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
					btNovaPais.setDisable(false); //  habilitar
				    btExcluirPais.setDisable(false);
				    btConfirmarPais.setDisable(false);
			    };
		    }
		});
		
		
		tableViewPais.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
			  
					  int selectedIndex1 = tableViewPais.getSelectionModel().getSelectedIndex();
					  if (selectedIndex1 >= 0 && keyEvent.getCode().equals(KeyCode.DELETE)) { 
						  
						  alert.setHeaderText("Confirmação de exclusão de informações!");
	       				  Optional<ButtonType> result = alert.showAndWait();
						  if (result.get() == ButtonType.OK){
	                                   lista.remove(selectedIndex1);
						  }              
				      }	
					  if (selectedIndex1 < 0 && keyEvent.getCode().equals(KeyCode.DELETE)) {
						    textFieldIdentificacao.setText("");
							textFieldNome.setText("");
							
				       }	
				  
		   }
		} );
	
	}

	@FXML public void onKeyPressedPesquisa() {
		
		    textFieldPesquisa.textProperty().addListener(obs->{
		        String filter = textFieldPesquisa.getText(); 
		        if(filter == null || filter.length() == 0) {
		            listaFiltrada.setPredicate(s -> true);
		        }
		        else {
		            listaFiltrada.setPredicate(s -> s.getNome().toUpperCase().contains(filter.toUpperCase()));
		        }
		    });
		    tableViewPais.setItems(listaFiltrada);
	    }
			
	private void botoesOnOf() {
    	if (flag == 1 || flag == 0) {
    		tableViewPais.setDisable(true);
    			textFieldPesquisa.setDisable(true);
    	}
		if (flag == 1) { // Significa inclusao
		       btNovaPais.setDisable(false); //  habilitar
		       btExcluirPais.setDisable(true);
		       btConfirmarPais.setDisable(true);
		   }
		if (flag == 0) { // Significa atualizacao
			   btNovaPais.setDisable(true); // deshabilitar
		       btExcluirPais.setDisable(true);
		       btConfirmarPais.setDisable(false);
		}
	}

    private void habilitarTodosControles() {
		tableViewPais.setDisable(false);
		textFieldPesquisa.setDisable(false);
		btNovaPais.setDisable(false); //  habilitar
		btExcluirPais.setDisable(false);
		btConfirmarPais.setDisable(false);
     }
    
 // --------------------------------------------------------- VALIDAÇÕES --------------------------------------------------------------------
    
    private void validarCampos() {
            
      	  
      	             
            // max length
            UnaryOperator<TextFormatter.Change> filterMaxLength = (TextFormatter.Change change) -> {
                     if (change.getControlNewText().length() > 35) {
                           return null;                         
                      }
                      return change;
              };
              textFieldNome.setTextFormatter(new TextFormatter<>(filterMaxLength));
              
              textFieldNome.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
              	if (!newValue.equals(oldValue)) {
              		botoesOnOf();
              	} if (textFieldNome.getText() == null || textFieldNome.getText().replaceAll(" ","").isEmpty()) { 
              		   btNovaPais.setDisable(true); //  desabilitar
              		   btConfirmarPais.setDisable(true);
              		   textFieldNome.setPromptText("Não é permitido em branco!");
              		   textFieldNome.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
              		   
              	} else {
                      	 textFieldNome.setStyle(null);
                      	 
                       }
                   });

                  
        }
        
    // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------
         


}