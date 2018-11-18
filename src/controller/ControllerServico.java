package controller;

import java.net.URL;
import util.Utilitarios;

import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import basedados.TableServicoLoad;
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
import model.Servico;
import javafx.scene.control.TextArea;

public class ControllerServico implements Initializable{

	@FXML private TableView<Servico> tableViewServico;
	@FXML private TableColumn<Servico, Integer> tableColumnIdentificacao;
	@FXML private TableColumn<Servico, String> tableColumnNome;
	@FXML private TableColumn<Servico, String> tableColumnValor;
	
	@FXML private Button btNovaServico;
	@FXML private Button btExcluirServico;
	@FXML private Button btConfirmarServico;
	@FXML private Button btCancelarServico;

	@FXML private TextField textFieldIdentificacao;
	@FXML private TextField textFieldNome;
	@FXML private TextArea textFieldDescricao;
	@FXML private TextField textFieldTarifaServico;
	@FXML private TextField textFieldPesquisa;
	@FXML private Label labelStatus;
	
	ObservableList<Servico> lista;
	FilteredList<Servico> listaFiltrada;
	Integer flag, selectedIndex;
	
	Alert alert;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de inclusão/alteração de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
    	
    	// Popular observaalelist Servico
		lista = TableServicoLoad.load();
		
		// Justificar colunas
		tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
		tableColumnValor.setStyle( "-fx-alignment: CENTER-RIGHT;");
        
		// popular Tableview
		tableViewServico.getItems().clear();
   		tableViewServico.setItems(lista); 
   		lista.sort(Comparator.comparing(Servico::getNome));
   		
   		// Detalhar o primeiro item do Tableview
   		selectedIndex = 0;
   		tableViewServico.getSelectionModel().select(selectedIndex);
   		Servico servico = tableViewServico.getSelectionModel().getSelectedItem();
   		validarCampos();
   		if (servico!=null) {
	    	flag=2;
			textFieldIdentificacao.setText(String.valueOf(servico.getId()));
			textFieldNome.setText(servico.getNome());
			textFieldDescricao.setText(servico.getDescricao());
			textFieldTarifaServico.setText(String.valueOf(servico.getValorTarifa()));
			
			flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
	    }
	    
	 // 1. Envolva o ObservableList em uma FilteredList (inicialmente exiba todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);
	    
       
    }
    
	// Incluir e gravar uma nova Servico
	
	@FXML public void onNovaServico() {   // Nova e gravar Servico
	
		// Preparar tela
		if (btNovaServico.getText().equals("Novo Serviço")) {            
	            
			    flag = 1; // modo de inclusao de Servico
			    botoesOnOf();
			    btNovaServico.setText("Gravar");
	            
				textFieldIdentificacao.setText(TableServicoLoad.getMaxIdServico(lista));
				textFieldNome.setText("");
				textFieldDescricao.setText("");
				textFieldTarifaServico.setText("");
				textFieldNome.requestFocus(); // O cursor se posiciona em nome	  
		  }
		else { // Efetuar gravacao da nova Servico
		
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	
					flag = 0;
					btNovaServico.setText("Novo Serviço");
					habilitarTodosControles();
				       
					lista.add(new Servico(Integer.valueOf(textFieldIdentificacao.getText()),
			                textFieldNome.getText(), 
			                textFieldDescricao.getText(), 
			                textFieldTarifaServico.getText()));	
					lista.sort(Comparator.comparing(Servico::getNome));
					btNovaServico.setText("Novo Serviço");
				}
		}
	}

	@FXML public void onAtualizar() { // Atualizar
		
		alert.setHeaderText("Confirmação de alteração de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				habilitarTodosControles();
		        lista.set(selectedIndex, new Servico(Integer.valueOf(textFieldIdentificacao.getText()),
		        		textFieldNome.getText(), 
		                textFieldDescricao.getText(), 
		                textFieldTarifaServico.getText()));		
		        tableViewServico.getSelectionModel().select(selectedIndex);
		        lista.sort(Comparator.comparing(Servico::getNome));
			}
	}

	@FXML public void onExcluirServico() {
	 
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewServico.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					textFieldIdentificacao.setText("");
					textFieldNome.setText("");
					textFieldDescricao.setText("");
					textFieldTarifaServico.setText("");
				}
		}		
			
     }
	
	@FXML public void onCancelar() {
		
		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
	
				flag = 0;
				btNovaServico.setText("Novo Serviço");
				habilitarTodosControles();
				tableViewServico.getSelectionModel().select(selectedIndex);
		   		Servico servico = tableViewServico.getSelectionModel().getSelectedItem();
			    if (servico!=null) {
			    	flag=2;
					textFieldIdentificacao.setText(String.valueOf(servico.getId()));
					textFieldNome.setText(servico.getNome());
					textFieldDescricao.setText(servico.getDescricao());
					textFieldTarifaServico.setText(servico.getValorTarifa());
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
			    }
		}    	    
	}

	@FXML public void onMaskNome() {
		botoesOnOf();
		Utilitarios.maxField(textFieldNome, 25);
	    Utilitarios.maskTudoMaiuculoTextField(textFieldNome);
	    
	}

	@FXML public void onKeyPressedTableView() {
		tableViewServico.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
		    	selectedIndex = tableViewServico.getSelectionModel().getSelectedIndex();
		    	Servico servico = tableViewServico.getSelectionModel().getSelectedItem();
			    if (servico!=null) {
					flag=2;
			    	textFieldIdentificacao.setText(String.valueOf(servico.getId()));
					textFieldNome.setText(servico.getNome());
					textFieldDescricao.setText(servico.getDescricao());
					textFieldTarifaServico.setText(servico.getValorTarifa());
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
					btNovaServico.setDisable(false); //  habilitar
				    btExcluirServico.setDisable(false);
				    btConfirmarServico.setDisable(false);
			    };
		    }
		});
		
		
		tableViewServico.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
			  
					  int selectedIndex1 = tableViewServico.getSelectionModel().getSelectedIndex();
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
							textFieldTarifaServico.setText("");	
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
		    tableViewServico.setItems(listaFiltrada);
	    }
			
	private void botoesOnOf() {
    	if (flag == 1 || flag == 0) {
    		tableViewServico.setDisable(true);
    		textFieldPesquisa.setDisable(true);
    	}
		if (flag == 1) { // Significa inclusao
		       btNovaServico.setDisable(false); //  habilitar
		       btExcluirServico.setDisable(true);
		       btConfirmarServico.setDisable(true);
		   }
		if (flag == 0) { // Significa atualizacao
			   btNovaServico.setDisable(true); // deshabilitar
		       btExcluirServico.setDisable(true);
		       btConfirmarServico.setDisable(false);
		}
	}

    private void habilitarTodosControles() {
		tableViewServico.setDisable(false);
		textFieldPesquisa.setDisable(false);
		btNovaServico.setDisable(false); //  habilitar
		btExcluirServico.setDisable(false);
		btConfirmarServico.setDisable(false);
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
            textFieldTarifaServico.setTextFormatter(new TextFormatter<>(filterMonetary));
            
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
              		   btNovaServico.setDisable(true); //  desabilitar
              		   btConfirmarServico.setDisable(true);
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
                		btNovaServico.setDisable(true); //  desabilitar
             		    btConfirmarServico.setDisable(true);
                		textFieldDescricao.setPromptText("Não é permitido em branco!");
                		textFieldDescricao.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                         } else textFieldDescricao.setStyle(null);
                     });

              textFieldTarifaServico.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
              	if (!newValue.equals(oldValue)) {
              		botoesOnOf();
              	} if (textFieldTarifaServico.getText() == null || textFieldTarifaServico.getText().replaceAll(" ","").isEmpty()) { 
                 		btNovaServico.setDisable(true); //  desabilitar
           	      	    btConfirmarServico.setDisable(true);
           		        textFieldTarifaServico.setPromptText("Não é permitido em branco!");
           		        textFieldTarifaServico.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                       } else textFieldTarifaServico.setStyle(null);
                   });
              
                              
        }
        
    // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------

}