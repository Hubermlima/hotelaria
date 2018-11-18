package controller;

import java.net.URL;

import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import basedados.TableTipoPromocionalLoad;
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
import model.TipoPromocional;
import javafx.scene.control.TextArea;

public class ControllerTipoPromocional implements Initializable{

	@FXML private TableView<TipoPromocional> tableViewTipoPromocional;
	@FXML private TableColumn<TipoPromocional, Integer> tableColumnIdentificacao;
	@FXML private TableColumn<TipoPromocional, String> tableColumnNome;
	@FXML private TableColumn<TipoPromocional, String> tableColumnPorcentagemDesconto;
	
	@FXML private Button btNovaTipoPromocional;
	@FXML private Button btExcluirTipoPromocional;
	@FXML private Button btConfirmarTipoPromocional;
	@FXML private Button btCancelarTipoPromocional;
	
	@FXML private TextField textFieldIdentificacao;
	@FXML private TextField textFieldNome;
	@FXML private TextArea textFieldDescricao;
	@FXML private TextField textFieldPorcentagemDesconto;
	@FXML private TextField textFieldPesquisa;
	@FXML private Label labelStatus;
	
	ObservableList<TipoPromocional> lista;
	FilteredList<TipoPromocional> listaFiltrada;
	Integer flag, selectedIndex;
	
	Alert alert;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de inclusão/alteração de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
         
    	// Popular observaalelist TipoPromocional
		lista = TableTipoPromocionalLoad.load();
		
		// Justificar colunas
		tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
		tableColumnPorcentagemDesconto.setStyle( "-fx-alignment: CENTER-RIGHT;");
        
		// popular Tableview
		tableViewTipoPromocional.getItems().clear();
   		tableViewTipoPromocional.setItems(lista); 
   		lista.sort(Comparator.comparing(TipoPromocional::getNome));
   		// Detalhar o primeiro item do Tableview
   		selectedIndex = 0;
   		tableViewTipoPromocional.getSelectionModel().select(selectedIndex);
   		TipoPromocional TipoPromocional = tableViewTipoPromocional.getSelectionModel().getSelectedItem();
   		validarCampos();
   		if (TipoPromocional!=null) {
   			flag=2;
			textFieldIdentificacao.setText(String.valueOf(TipoPromocional.getId()));
			textFieldNome.setText(TipoPromocional.getNome());
			textFieldDescricao.setText(TipoPromocional.getDescricao());
			textFieldPorcentagemDesconto.setText(String.valueOf(TipoPromocional.getPorcentagemDesconto()));
			
			flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
	    }
	
   	
	 // 1. Envolva o ObservableList em uma FilteredList (inicialmente exiba todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);
        validarCampos();  
        
    }
    
	// Incluir e gravar uma nova TipoPromocional
	
	@FXML public void onNovaTipoPromocional() {   // Nova e gravar TipoPromocional
	
		// Preparar tela
		if (btNovaTipoPromocional.getText().equals("Novo Tipo")) {            
	            
			    flag = 1; // modo de inclusao de TipoPromocional
			    botoesOnOf();
			    btNovaTipoPromocional.setText("Gravar");
	            
				textFieldIdentificacao.setText(TableTipoPromocionalLoad.getMaxIdTipoPromocional(lista));
				textFieldNome.setText("");
				textFieldDescricao.setText("");
				textFieldPorcentagemDesconto.setText("");
				textFieldNome.requestFocus(); // O cursor se posiciona em nome	  
		  }
		else { // Efetuar gravacao da nova TipoPromocional
			
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	
					flag = 0;
					btNovaTipoPromocional.setText("Novo Tipo");
					habilitarTodosControles();
				       
					lista.add(new TipoPromocional(Integer.valueOf(textFieldIdentificacao.getText()),
			                textFieldNome.getText(), 
			                textFieldDescricao.getText(), 
			                textFieldPorcentagemDesconto.getText()));	
					lista.sort(Comparator.comparing(TipoPromocional::getNome));
					btNovaTipoPromocional.setText("Novo Tipo");
				}
		}
	}

	@FXML public void onAtualizar() { // Atualizar
		
		alert.setHeaderText("Confirmação de alteração de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
			habilitarTodosControles();
	        lista.set(selectedIndex, new TipoPromocional(Integer.valueOf(textFieldIdentificacao.getText()),
	        		textFieldNome.getText(), 
	                textFieldDescricao.getText(), 
	                textFieldPorcentagemDesconto.getText()));		
	        tableViewTipoPromocional.getSelectionModel().select(selectedIndex);
	        lista.sort(Comparator.comparing(TipoPromocional::getNome));
		}
	}
	
	@FXML public void onExcluirTipoPromocional() {
	
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewTipoPromocional.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					textFieldIdentificacao.setText("");
					textFieldNome.setText("");
					textFieldDescricao.setText("");
					textFieldPorcentagemDesconto.setText("");
				}
		}		
			
     }
	
	@FXML public void onCancelar() {
		
		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
	
				flag = 0;
				btNovaTipoPromocional.setText("Novo Tipo");
				habilitarTodosControles();
				tableViewTipoPromocional.getSelectionModel().select(selectedIndex);
		   		TipoPromocional TipoPromocional = tableViewTipoPromocional.getSelectionModel().getSelectedItem();
			    if (TipoPromocional!=null) {
			    	flag=2;
					textFieldIdentificacao.setText(String.valueOf(TipoPromocional.getId()));
					textFieldNome.setText(TipoPromocional.getNome());
					textFieldDescricao.setText(TipoPromocional.getDescricao());
					textFieldPorcentagemDesconto.setText(TipoPromocional.getPorcentagemDesconto());
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
			    }
		}	    
	}

	@FXML public void onKeyPressedTableView() {
		tableViewTipoPromocional.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
		    	selectedIndex = tableViewTipoPromocional.getSelectionModel().getSelectedIndex();
		    	TipoPromocional TipoPromocional = tableViewTipoPromocional.getSelectionModel().getSelectedItem();
			    if (TipoPromocional!=null) {
			    	flag=2;
					textFieldIdentificacao.setText(String.valueOf(TipoPromocional.getId()));
					textFieldNome.setText(TipoPromocional.getNome());
					textFieldDescricao.setText(TipoPromocional.getDescricao());
					textFieldPorcentagemDesconto.setText(TipoPromocional.getPorcentagemDesconto());
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
					btNovaTipoPromocional.setDisable(false); //  habilitar
				    btExcluirTipoPromocional.setDisable(false);
				    btConfirmarTipoPromocional.setDisable(false);
			    };
		    }
		});
		
		
		tableViewTipoPromocional.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
			  
					  int selectedIndex1 = tableViewTipoPromocional.getSelectionModel().getSelectedIndex();
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
							textFieldDescricao.setText("");
							textFieldPorcentagemDesconto.setText("");
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
		    tableViewTipoPromocional.setItems(listaFiltrada);
	    }
			
	private void botoesOnOf() {
    	if (flag == 1 || flag == 0) {
    			tableViewTipoPromocional.setDisable(true);
    			textFieldPesquisa.setDisable(true);
    	}
		if (flag == 1) { // Significa inclusao
		       btNovaTipoPromocional.setDisable(false); //  habilitar
		       btExcluirTipoPromocional.setDisable(true);
		       btConfirmarTipoPromocional.setDisable(true);
		   }
		if (flag == 0) { // Significa atualizacao
			   btNovaTipoPromocional.setDisable(true); // deshabilitar
		       btExcluirTipoPromocional.setDisable(true);
		       btConfirmarTipoPromocional.setDisable(false);
		}
	}

    private void habilitarTodosControles() {
		tableViewTipoPromocional.setDisable(false);
		textFieldPesquisa.setDisable(false);
		btNovaTipoPromocional.setDisable(false); //  habilitar
		btExcluirTipoPromocional.setDisable(false);
		btConfirmarTipoPromocional.setDisable(false);
     }

 // --------------------------------------------------------- VALIDAÇÕES --------------------------------------------------------------------
    
    private void validarCampos() {
            
      	   	
        	//monetary
        	UnaryOperator<TextFormatter.Change> filterMonetary = new UnaryOperator<TextFormatter.Change>() {

                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {

                    if (t.isReplaced()) 
                        if(t.getText().matches("[^0-9]"))
                            t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                    

                    if (t.isAdded()) {
                        if (t.getControlText().contains(",")) {
                            if (t.getText().matches("[^0-9]")) {
                                t.setText("");
                            }
                        } else if (t.getText().matches("[^0-9\\,]")) {
                            t.setText("");
                        }
                    }

                    return t;
                }
            };
            textFieldPorcentagemDesconto.setTextFormatter(new TextFormatter<>(filterMonetary));
            
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
              		   btNovaTipoPromocional.setDisable(true); //  desabilitar
              		   btConfirmarTipoPromocional.setDisable(true);
              		   textFieldNome.setPromptText("Não é permitido em branco!");
              		   textFieldNome.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
              		   
              	} else {
                      	 textFieldNome.setStyle(null);
                      	 
                       }
                   });

              
              textFieldPorcentagemDesconto.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
              	if (!newValue.equals(oldValue)) {
              		botoesOnOf();
              	} if (textFieldPorcentagemDesconto.getText() == null || textFieldPorcentagemDesconto.getText().replaceAll(" ","").isEmpty()) { 
              		btNovaTipoPromocional.setDisable(true); //  desabilitar
           		   btConfirmarTipoPromocional.setDisable(true);
           		textFieldPorcentagemDesconto.setPromptText("Não é permitido em branco!");
              		textFieldPorcentagemDesconto.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                       } else textFieldPorcentagemDesconto.setStyle(null);
                   });
              
              textFieldDescricao.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
              	if (!newValue.equals(oldValue)) {
              		botoesOnOf();
              	} if (textFieldDescricao.getText() == null || textFieldDescricao.getText().replaceAll(" ","").isEmpty()) { 
              		btNovaTipoPromocional.setDisable(true); //  desabilitar
           		   btConfirmarTipoPromocional.setDisable(true);
           		textFieldDescricao.setPromptText("Não é permitido em branco!");
              		textFieldDescricao.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                       } else textFieldDescricao.setStyle(null);
                   });
              
                 
        }
        
    // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------
         

}