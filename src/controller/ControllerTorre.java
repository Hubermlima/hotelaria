package controller;

import java.net.URL;

import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import basedados.TableTorreLoad;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import model.LocalizacaoTorre;
import model.Torre;

public class ControllerTorre implements Initializable{

	@FXML private TableView<Torre> tableViewTorre;
	@FXML private TableColumn<Torre, Integer> tableColumnIdentificacao;
	@FXML private TableColumn<Torre, String> tableColumnNome;
	@FXML private TableColumn<Torre, Integer> tableColumnNAndar;
	@FXML private TableColumn<Torre, Integer> tableColumnNApartamentosPorAndar;
	
	@FXML private Button btNovaTorre;
	@FXML private Button btExcluirTorre;
	@FXML private Button btConfirmarTorre;
	@FXML private Button btCancelarTorre;
	
	@FXML private TextField textFieldIdentificacao;
	@FXML private TextField textFieldNome;
	@FXML private TextField textFieldAndar;
	@FXML private TextField textFieldNApartamentosPorAndar; 
		
	@FXML private ComboBox<String> comboBoxLocalidade;
	
	@FXML private TextField textFieldPesquisa;
	@FXML private Label labelStatus;
	ObservableList<Torre> lista;
	FilteredList<Torre> listaFiltrada;
	Integer flag, selectedIndex;
	
	Alert alert;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	// DIALOG CONFIGURACOES
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
 	
    	// popular observalelists
		lista = TableTorreLoad.load();		
		
		// popular tableview
   		tableViewTorre.setItems(lista);
   		lista.sort(Comparator.comparing(Torre::getNome));
   		
   		// popular combobox
   		comboBoxLocalidade.getItems().clear(); 
   	    for(LocalizacaoTorre tipo : LocalizacaoTorre.values()){
   		       comboBoxLocalidade.getItems().add(tipo.getDescricao());
         }
   	   
		// Justificar colunas
		tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
		tableColumnNAndar.setStyle( "-fx-alignment: CENTER;");
		tableColumnNApartamentosPorAndar.setStyle( "-fx-alignment: CENTER;");
        
   		// Detalhar o primeiro item do tableview
   		selectedIndex = 0;
   		tableViewTorre.getSelectionModel().select(selectedIndex);
   		Torre torre = tableViewTorre.getSelectionModel().getSelectedItem();
   		validarCampos();
	    if (torre!=null) {
	    	flag = 2;
			textFieldIdentificacao.setText(String.valueOf(torre.getId()));
			textFieldNome.setText(torre.getNome());
			textFieldAndar.setText(String.valueOf(torre.getNAndar()));
			textFieldNApartamentosPorAndar.setText(String.valueOf(torre.getNApartamentosPorAndar()));
			comboBoxLocalidade.setValue(LocalizacaoTorre.getNome(torre.getLocal()).toString());
			
			flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
	    }
	    
	    // 1. Envolva o ObservableList em uma FilteredList (inicialmente exibe todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);
	   
        comboBoxLocalidade.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				botoesOnOf();	
			 }
		});

    	

      }
     
 // Incluir e gravar uma nova categoria
    @FXML public void onNovaTorre() {   // Nova e gravar categoria
	
		// Preparar tela
		if (btNovaTorre.getText().equals("Nova Torre")) {
	            
			    flag = 1; // modo de inclusao de categoria
			    botoesOnOf();
			    btNovaTorre.setText("Gravar");
	            
				textFieldIdentificacao.setText(TableTorreLoad.getMaxIdTorre(lista));
				textFieldNome.setText("");
				textFieldAndar.setText("");
				textFieldNApartamentosPorAndar.setText("");
				comboBoxLocalidade.getSelectionModel().selectFirst();
				textFieldNome.requestFocus(); // O cursor se posiciona em nome	  
				
		  }
		else { // Efetuar gravacao da nova UH
    
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	 
					flag = 0;
					btNovaTorre.setText("Nova Torre");
					habilitarTodosControles();
					lista.add(new Torre(Integer.valueOf(textFieldIdentificacao.getText()),
			                textFieldNome.getText(),  
			                Integer.valueOf(textFieldAndar.getText()),
			                LocalizacaoTorre.getCodigo(comboBoxLocalidade.getValue().toString()), 
			                Integer.valueOf(textFieldNApartamentosPorAndar.getText())));
			              
					//reOrderingList();
					lista.sort(Comparator.comparing(Torre::getNome));
					btNovaTorre.setText("Nova Torre");
			}	
		}
	
	}
	
	@FXML public void onAtualizarTorre() { // Atualizar
		
		alert.setHeaderText("Confirmação de alteração de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
			habilitarTodosControles();
			
			lista.set(selectedIndex, new Torre(Integer.valueOf(textFieldIdentificacao.getText()),
	                textFieldNome.getText(),  
	                Integer.valueOf(textFieldAndar.getText()),
	                //getIdTorre(comboBoxLocalidade.getValue().toString()),
	                LocalizacaoTorre.getCodigo(comboBoxLocalidade.getValue().toString()),
	                Integer.valueOf(textFieldNApartamentosPorAndar.getText())));
			
			lista.sort(Comparator.comparing(Torre::getNome));
	        tableViewTorre.getSelectionModel().select(selectedIndex);
		}   
	}

	@FXML public void onExcluirTorre() {
	   
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewTorre.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					textFieldIdentificacao.setText(TableTorreLoad.getMaxIdTorre(lista));
					textFieldNome.setText("");
					textFieldAndar.setText("");
					textFieldNApartamentosPorAndar.setText("");
					comboBoxLocalidade.getSelectionModel().selectFirst();	
				}
		}		
			
     }
	
	@FXML public void onCancelarTorre() {

		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
				flag = 0;
				btNovaTorre.setText("Nova Torre");
				habilitarTodosControles();
				tableViewTorre.getSelectionModel().select(selectedIndex);
		   		
				Torre torre = tableViewTorre.getSelectionModel().getSelectedItem();
			    if (torre!=null) {
			    	flag = 2;
			    	textFieldIdentificacao.setText(String.valueOf(torre.getId()));
					textFieldNome.setText(torre.getNome());
					textFieldAndar.setText(String.valueOf(torre.getNAndar()));
					textFieldNApartamentosPorAndar.setText(String.valueOf(torre.getNApartamentosPorAndar()));
					comboBoxLocalidade.setValue(LocalizacaoTorre.getNome(torre.getLocal()).toString());
					
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
			    }
		}    
	}

	@FXML public void onKeyPressedTableView() {
		tableViewTorre.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
		    	selectedIndex = tableViewTorre.getSelectionModel().getSelectedIndex();
		    	
		    	Torre torre = tableViewTorre.getSelectionModel().getSelectedItem();
			    if (torre!=null) {
			    	flag = 2;
			    	textFieldIdentificacao.setText(String.valueOf(torre.getId()));
					textFieldNome.setText(torre.getNome());
					textFieldAndar.setText(String.valueOf(torre.getNAndar()));
					textFieldNApartamentosPorAndar.setText(String.valueOf(torre.getNApartamentosPorAndar()));
					comboBoxLocalidade.setValue(LocalizacaoTorre.getNome(torre.getLocal()).toString());
					
					
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
					btNovaTorre.setDisable(false); //  habilitar
				    btExcluirTorre.setDisable(false);
				    btConfirmarTorre.setDisable(false);
			    };
		    }
		});
		
		
		tableViewTorre.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
		    	
					  int selectedIndex1 = tableViewTorre.getSelectionModel().getSelectedIndex();
					  if (selectedIndex1 >= 0 && keyEvent.getCode().equals(KeyCode.DELETE)) { 
						  
							  alert.setHeaderText("Confirmação de exclusão de informações!");
		       				  Optional<ButtonType> result = alert.showAndWait();
							  if (result.get() == ButtonType.OK){
		                                   lista.remove(selectedIndex1);
							  }              
					  }	  
					  if (selectedIndex1 < 0 && keyEvent.getCode().equals(KeyCode.DELETE)) {
						    textFieldIdentificacao.setText(TableTorreLoad.getMaxIdTorre(lista));
							textFieldNome.setText("");
							textFieldAndar.setText("");
							textFieldNApartamentosPorAndar.setText("");
							comboBoxLocalidade.getSelectionModel().selectFirst();	
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
		    tableViewTorre.setItems(listaFiltrada);
	    }
			
	private void botoesOnOf() {
    	if (flag == 1 || flag == 0) {
    		tableViewTorre.setDisable(true);
    			textFieldPesquisa.setDisable(true);
    	}
		if (flag == 1) { // Significa inclusao
		       btNovaTorre.setDisable(false); //  habilitar
		       btExcluirTorre.setDisable(true);
		       btConfirmarTorre.setDisable(true);
		   }
		if (flag == 0) { // Significa atualizacao
			   btNovaTorre.setDisable(true); // deshabilitar
		       btExcluirTorre.setDisable(true);
		       btConfirmarTorre.setDisable(false);
		}
	}


    private void habilitarTodosControles() {
		tableViewTorre.setDisable(false);
		textFieldPesquisa.setDisable(false);
		btNovaTorre.setDisable(false); //  habilitar
		btExcluirTorre.setDisable(false);
		btConfirmarTorre.setDisable(false);
     }
  
	// --------------------------------------------------------- VALIDAÇÕES --------------------------------------------------------------------
    
	  private void validarCampos() {
	          
	    	  
	    	// integer
	      	UnaryOperator<Change> integerFilter = change -> {
	      	    String input = change.getText();
	      	    if (input.matches("[0-9]*")) { 
	      	        return change;
	      	    }
	      	    return null;
	      	};
	    	
	    	textFieldAndar.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
	    	textFieldNApartamentosPorAndar.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
	      		          
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
	            		   btNovaTorre.setDisable(true); //  desabilitar
	            		   btConfirmarTorre.setDisable(true);
	            		   textFieldNome.setPromptText("Não é permitido em branco!");
	            		   textFieldNome.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
	            		   
	            	} else {
	                    	 textFieldNome.setStyle(null);
	                    	 
	                     }
	                 });

	            
	            textFieldAndar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
	            	if (!newValue.equals(oldValue)) {
	            		botoesOnOf();
	            	} if (textFieldAndar.getText() == null || textFieldAndar.getText().replaceAll(" ","").isEmpty()) { 
	            		btNovaTorre.setDisable(true); //  desabilitar
	            		   btConfirmarTorre.setDisable(true);
	            		textFieldAndar.setPromptText("Não é permitido em branco!");
	            		textFieldAndar.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
	                     } else textFieldAndar.setStyle(null);
	                 });
	            
	            textFieldNApartamentosPorAndar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
	            	if (!newValue.equals(oldValue)) {
	            		botoesOnOf();
	            	} if (textFieldNApartamentosPorAndar.getText() == null || textFieldNApartamentosPorAndar.getText().replaceAll(" ","").isEmpty()) { 
	            		btNovaTorre.setDisable(true); //  desabilitar
	         		   btConfirmarTorre.setDisable(true);
	         		  textFieldNApartamentosPorAndar.setPromptText("Não é permitido em branco!");
	         		 textFieldNApartamentosPorAndar.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
	                     } else textFieldNApartamentosPorAndar.setStyle(null);
	                 });
	            	          
	      }
	      
	  // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------
	       
	
	
	
	
	
   
}

