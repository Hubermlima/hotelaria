package controller;

import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import basedados.TableAgenciaViagemLoad;
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
import model.AgenciaViagem;
import javafx.scene.control.TextArea;

public class ControllerAgenciaViagem implements Initializable{

	@FXML private TableView<AgenciaViagem> tableViewAgenciaViagem;
	@FXML private TableColumn<AgenciaViagem, Integer> tableColumnIdentificacao;
	@FXML private TableColumn<AgenciaViagem, String> tableColumnNome;
	@FXML private TableColumn<AgenciaViagem, String> tableColumnValorComissao;
	
	@FXML private Button btNovaAgenciaViagem;
	@FXML private Button btExcluirAgenciaViagem;
	@FXML private Button btConfirmarAgenciaViagem;
	@FXML private Button btCancelarAgenciaViagem;
	@FXML private TextField textFieldIdentificacao;
	@FXML private TextField textFieldNome;
	@FXML private TextArea textFieldDescricao;
	@FXML private TextField textFieldValorComissao;
	@FXML private TextField textFieldPesquisa;
	@FXML private Label labelStatus;
	
	ObservableList<AgenciaViagem> lista;
	FilteredList<AgenciaViagem> listaFiltrada;
	Integer flag, selectedIndex;

	Alert alert;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de inclusão/alteração de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
         
    	// Popular observaalelist AgenciaViagem
		lista = TableAgenciaViagemLoad.load();
		
		// popular Tableview
   		tableViewAgenciaViagem.setItems(lista); 
   		lista.sort(Comparator.comparing(AgenciaViagem::getNome));
   		
   	        // Justificar colunas
   			tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
   			tableColumnValorComissao.setStyle( "-fx-alignment: CENTER-RIGHT;");
   	        
   		// Detalhar o primeiro item do Tableview
   		selectedIndex = 0;
   		tableViewAgenciaViagem.getSelectionModel().select(selectedIndex);
   		AgenciaViagem agenciaViagem = tableViewAgenciaViagem.getSelectionModel().getSelectedItem();
   		validarCampos();
	    if (agenciaViagem!=null) {
			flag=2;
	    	textFieldIdentificacao.setText(String.valueOf(agenciaViagem.getId()));
			textFieldNome.setText(agenciaViagem.getNome());
			textFieldDescricao.setText(agenciaViagem.getDescricao());
			textFieldValorComissao.setText(String.valueOf(agenciaViagem.getValorComissao()));	
			flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
	    }
	   
	  
	    // 1. Envolva o ObservableList em uma FilteredList (inicialmente exiba todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);

    }
    
	// Incluir e gravar uma nova AgenciaViagem
	
	@FXML public void onNovaAgencia() {   // Nova e gravar AgenciaViagem
	
		// Preparar tela
		if (btNovaAgenciaViagem.getText().equals("Nova agência")) {            
	           
			    flag = 1; // modo de inclusao de AgenciaViagem
			    botoesOnOf();
			    btNovaAgenciaViagem.setText("Gravar");
	            
				textFieldIdentificacao.setText(TableAgenciaViagemLoad.getMaxIdAgenciaViagem(lista));
				textFieldNome.setText("");
				textFieldDescricao.setText("");
				textFieldValorComissao.setText("");
				textFieldNome.requestFocus(); // O cursor se posiciona em nome	  
		  }
		else { // Efetuar gravacao da nova AgenciaViagem
		
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	
					flag = 0;
					btNovaAgenciaViagem.setText("Nova agência");
					habilitarTodosControles();
				       
					lista.add(new AgenciaViagem(Integer.valueOf(textFieldIdentificacao.getText()),
			                textFieldNome.getText(), 
			                textFieldDescricao.getText(), 
			                textFieldValorComissao.getText()));	
					lista.sort(Comparator.comparing(AgenciaViagem::getNome));
					btNovaAgenciaViagem.setText("Nova agência");
		   }
		}
	}

	@FXML public void onAtualizar() { // Atualizar
		
		alert.setHeaderText("Confirmação de alteração de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				habilitarTodosControles();
		        lista.set(selectedIndex, new AgenciaViagem(Integer.valueOf(textFieldIdentificacao.getText()),
		        		textFieldNome.getText(), 
		                textFieldDescricao.getText(), 
		                textFieldValorComissao.getText()));		
		        lista.sort(Comparator.comparing(AgenciaViagem::getNome));
		        tableViewAgenciaViagem.getSelectionModel().select(selectedIndex);
			}
	}

	@FXML public void onExcluirAgenciaViagem() {
	 
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewAgenciaViagem.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					textFieldIdentificacao.setText("");
					textFieldNome.setText("");
					textFieldDescricao.setText("");
					textFieldValorComissao.setText("");
				}
		}	
			
     }
	
	@FXML public void onCancelar() {
		
		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
	
				flag = 0;
				btNovaAgenciaViagem.setText("Nova agência");
				habilitarTodosControles();
				tableViewAgenciaViagem.getSelectionModel().select(selectedIndex);
		   		AgenciaViagem agenciaViagem = tableViewAgenciaViagem.getSelectionModel().getSelectedItem();
			    if (agenciaViagem!=null) {
			    	flag = 2;
					textFieldIdentificacao.setText(String.valueOf(agenciaViagem.getId()));
					textFieldNome.setText(agenciaViagem.getNome());
					textFieldDescricao.setText(agenciaViagem.getDescricao());
					textFieldValorComissao.setText(agenciaViagem.getValorComissao());
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
			    }
		}    
	}

	@FXML public void onKeyPressedTableView() {
		tableViewAgenciaViagem.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        	
		    	selectedIndex = tableViewAgenciaViagem.getSelectionModel().getSelectedIndex();
		    	AgenciaViagem agenciaViagem = tableViewAgenciaViagem.getSelectionModel().getSelectedItem();
			    if (agenciaViagem!=null) {
			    	flag = 2;
					textFieldIdentificacao.setText(String.valueOf(agenciaViagem.getId()));
					textFieldNome.setText(agenciaViagem.getNome());
					textFieldDescricao.setText(agenciaViagem.getDescricao());
					textFieldValorComissao.setText(agenciaViagem.getValorComissao());
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
					btNovaAgenciaViagem.setDisable(false); //  habilitar
				    btExcluirAgenciaViagem.setDisable(false);
				    btConfirmarAgenciaViagem.setDisable(false);
			    };
		    }
		});
		
		
		tableViewAgenciaViagem.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
			  
					  int selectedIndex1 = tableViewAgenciaViagem.getSelectionModel().getSelectedIndex();
					  if (selectedIndex1 >= 0 && keyEvent.getCode().equals(KeyCode.DELETE)) { 
						  
						  alert.setHeaderText("Confirmação de exclusão de informações!");
	       				  Optional<ButtonType> result = alert.showAndWait();
						  if (result.get() == ButtonType.OK){
	                                   lista.remove(selectedIndex1);
						  }              
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
		    tableViewAgenciaViagem.setItems(listaFiltrada);
	    }
		
	private void botoesOnOf() {
    	if (flag == 1 || flag == 0) {
    			tableViewAgenciaViagem.setDisable(true);
    			textFieldPesquisa.setDisable(true);
    	}
		if (flag == 1) { // Significa inclusao
		       btNovaAgenciaViagem.setDisable(false); //  habilitar
		       btExcluirAgenciaViagem.setDisable(true);
		       btConfirmarAgenciaViagem.setDisable(true);
		   }
		if (flag == 0) { // Significa atualizacao
			btNovaAgenciaViagem.setDisable(true); // deshabilitar
			btExcluirAgenciaViagem.setDisable(true);
			btConfirmarAgenciaViagem.setDisable(false);
		}
	}

    private void habilitarTodosControles() {
		tableViewAgenciaViagem.setDisable(false);
		textFieldPesquisa.setDisable(false);
		btNovaAgenciaViagem.setDisable(false); //  habilitar
		btExcluirAgenciaViagem.setDisable(false);
		btConfirmarAgenciaViagem.setDisable(false);
     }

// --------------------------------------------------------- VALIDAÇÕES --------------------------------------------------------------------
    
    private void validarCampos() {
            
      	  
        	//textFieldPorcentagemDesconto.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        	
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
            textFieldValorComissao.setTextFormatter(new TextFormatter<>(filterMonetary));
            
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
              		   btNovaAgenciaViagem.setDisable(true); //  desabilitar
              		   btConfirmarAgenciaViagem.setDisable(true);
              		   textFieldNome.setPromptText("Não é permitido em branco!");
              		   textFieldNome.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
              		   
              	} else {
                      	 textFieldNome.setStyle(null);
                      	 
                       }
                   });

              
              textFieldDescricao.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
              	if (!newValue.equals(oldValue)) {
              		botoesOnOf();
              	} if (textFieldDescricao.getText() == null || textFieldDescricao.getText().replaceAll(" ","").isEmpty()) { 
              		btNovaAgenciaViagem.setDisable(true); //  desabilitar
           		   btConfirmarAgenciaViagem.setDisable(true);
           		textFieldDescricao.setPromptText("Não é permitido em branco!");
           		textFieldDescricao.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                       } else textFieldDescricao.setStyle(null);
                   });
              
              textFieldValorComissao.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
              	if (!newValue.equals(oldValue)) {
              		botoesOnOf();
              	} if (textFieldValorComissao.getText() == null || textFieldValorComissao.getText().replaceAll(" ","").isEmpty()) { 
              		btNovaAgenciaViagem.setDisable(true); //  desabilitar
              		btConfirmarAgenciaViagem.setDisable(true);
           		textFieldValorComissao.setPromptText("Não é permitido em branco!");
           		textFieldValorComissao.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                       } else textFieldValorComissao.setStyle(null);
                   });
              
        
        }
        
    // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------
         

}