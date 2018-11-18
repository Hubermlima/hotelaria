package controller;

import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import basedados.TableCategoriaLoad;
import basedados.TableTorreLoad;
import basedados.TableUnidadeHabitacionalLoad;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import model.Categoria;
import model.Torre;
import model.UnidadeHabitacional;
import javafx.scene.control.TextArea;

public class ControllerUnidadeHabitacional implements Initializable{

	@FXML private TableView<UnidadeHabitacional> tableViewUnidadeHabitacional;
	@FXML private TableColumn<UnidadeHabitacional, Integer> tableColumnIdentificacao;
	@FXML private TableColumn<UnidadeHabitacional, String> tableColumnNome;
	@FXML private TableColumn<UnidadeHabitacional, Integer> tableColumnQuarto;
	@FXML private TableColumn<UnidadeHabitacional, String> tableColumnNumero;
	@FXML private TableColumn<UnidadeHabitacional, Integer> tableColumnAndar;
	
	@FXML private Button btNovaUnidade;
	@FXML private Button btExcluirUnidade;
	@FXML private Button btConfirmarUnidade;
	@FXML private Button btCancelarUnidade;
	
	@FXML private TextField textFieldIdentificacao;
	@FXML private TextField textFieldNome;
	@FXML private TextField textFieldQuarto;
	@FXML private TextField textFieldNumeroUH;
	@FXML private TextField textFieldAndar;
	@FXML private TextArea textFieldDescricao;
	@FXML private TextField textFieldPrecoDiaria;
	
	//@FXML private TextField textFieldCategoria;
	
	@FXML private ComboBox<Categoria> comboBoxCategoria;
	@FXML private ComboBox<Torre> comboBoxTorre;
	
	@FXML private TextField textFieldPesquisa;
	@FXML private Label labelStatus;
	private ObservableList<UnidadeHabitacional> lista;
	private ObservableList<Categoria> listaCategorias;
	private ObservableList<Torre> listaTorres;
	private FilteredList<UnidadeHabitacional> listaFiltrada;
	
	Integer flag, selectedIndex, selectedIndexCombo;
    
	Alert alert;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	   	
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de inclusão/alteração de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
    	
    	// popular observalelists CATEGORIA
		lista = TableUnidadeHabitacionalLoad.load();
		listaCategorias = TableCategoriaLoad.loadParcial();
		listaTorres = TableTorreLoad.load();
		
		// popular tableview
   		tableViewUnidadeHabitacional.setItems(lista);
   		lista.sort(Comparator.comparing(UnidadeHabitacional::getNome));
   		
   		// popular combobox CATEGORIA
   		comboBoxCategoria.getItems().clear();
   		comboBoxCategoria.setItems(listaCategorias);
   		listaCategorias.sort(Comparator.comparing(Categoria::getNome));
   			
   	    // popular combobox TORRE
   		comboBoxTorre.getItems().clear();
   		comboBoxTorre.setItems(listaTorres);
   		listaTorres.sort(Comparator.comparing(Torre::getNome));
   		
   		// Justificar colunas
		tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
		tableColumnQuarto.setStyle( "-fx-alignment: CENTER;");
		tableColumnNumero.setStyle( "-fx-alignment: CENTER;");
		tableColumnAndar.setStyle( "-fx-alignment: CENTER;");
        
   		// Detalhar o primeiro item do tableview
   		selectedIndex = 0;
   		tableViewUnidadeHabitacional.getSelectionModel().select(selectedIndex);
   		UnidadeHabitacional unidadeHabitacional = tableViewUnidadeHabitacional.getSelectionModel().getSelectedItem();
   		validarCampos();
   		if (unidadeHabitacional!=null) {
	    	flag = 2;
			textFieldIdentificacao.setText(String.valueOf(unidadeHabitacional.getId()));
			textFieldNome.setText(unidadeHabitacional.getNome());
			textFieldQuarto.setText(String.valueOf(unidadeHabitacional.getNQuartos()));
			textFieldNumeroUH.setText(unidadeHabitacional.getNumero());
			textFieldAndar.setText(String.valueOf(unidadeHabitacional.getAndar()));
			comboBoxTorre.setValue(unidadeHabitacional.getTorre());
			textFieldDescricao.setText(unidadeHabitacional.getDescricao());
			comboBoxCategoria.setValue(unidadeHabitacional.getCategoria());
    		textFieldPrecoDiaria.setText(unidadeHabitacional.getCategoria().getPrecoCategoria());
			
			flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
	    }
	    
	    comboBoxCategoria.valueProperty().addListener(new ChangeListener<Categoria>() {
			@Override
			public void changed(ObservableValue<? extends Categoria> observable, Categoria oldValue, Categoria newValue) {
				textFieldPrecoDiaria.setText(newValue.getPrecoCategoria());
				botoesOnOf();	
			 }
		});
   		
	    
	    comboBoxTorre.valueProperty().addListener(new ChangeListener<Torre>() {
			@Override
			public void changed(ObservableValue<? extends Torre> observable, Torre oldValue, Torre newValue) {
				botoesOnOf();	
			 }
		});
	    
	    
	 // 1. Envolva o ObservableList em uma FilteredList (inicialmente exibe todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);
	    
    	

    }
     
 // Incluir e gravar uma nova categoria
    @FXML public void onNovaUnidade() {   // Nova e gravar categoria
	
		// Preparar tela
		if (btNovaUnidade.getText().equals("Nova UH")) {
	            
			    flag = 1; // modo de inclusao de categoria
			    botoesOnOf();
			    btNovaUnidade.setText("Gravar");
	            
				textFieldIdentificacao.setText(TableUnidadeHabitacionalLoad.getMaxIdUH(lista));
				textFieldNome.setText("");
				textFieldQuarto.setText("");
				textFieldNumeroUH.setText("");
				textFieldAndar.setText("");
				comboBoxTorre.getSelectionModel().selectFirst();
				textFieldDescricao.setText("");
				comboBoxCategoria.getSelectionModel().selectFirst();
				
				textFieldNome.requestFocus(); // O cursor se posiciona em nome	  
		  }
		else { // Efetuar gravacao da nova UH
		
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	
					flag = 0;
					btNovaUnidade.setText("Nova UH");
					habilitarTodosControles();
					lista.add(new UnidadeHabitacional(Integer.valueOf(textFieldIdentificacao.getText()),
			                textFieldNome.getText(),  
			                Integer.valueOf(textFieldQuarto.getText()),
			                textFieldNumeroUH.getText(),
			                Integer.valueOf(textFieldAndar.getText()),
			                comboBoxTorre.getValue(),
			                textFieldDescricao.getText(),
                            comboBoxCategoria.getValue())); 
			                
					lista.sort(Comparator.comparing(UnidadeHabitacional::getNome));
					btNovaUnidade.setText("Nova UH");
				}
		}
	}

	
	@FXML public void onAtualizarUnidade() { // Atualizar
		
		alert.setHeaderText("Confirmação de alteração de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				habilitarTodosControles();
				lista.set(selectedIndex, new UnidadeHabitacional(Integer.valueOf(textFieldIdentificacao.getText()),
		                textFieldNome.getText(),  
		                Integer.valueOf(textFieldQuarto.getText()),
		                textFieldNumeroUH.getText(),
		                Integer.valueOf(textFieldAndar.getText()),
		                comboBoxTorre.getValue(),
		                textFieldDescricao.getText(),
		                comboBoxCategoria.getValue()));
				
				lista.sort(Comparator.comparing(UnidadeHabitacional::getNome));
		        tableViewUnidadeHabitacional.getSelectionModel().select(selectedIndex);
			}
	}
	
	@FXML public void onExcluirUnidade() {
	   
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewUnidadeHabitacional.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					textFieldIdentificacao.setText("");
					textFieldNome.setText("");
					textFieldQuarto.setText("");
					textFieldNumeroUH.setText("");
					textFieldAndar.setText("");
					comboBoxTorre.getSelectionModel().selectFirst();
					textFieldDescricao.setText("");
					comboBoxCategoria.getSelectionModel().selectFirst();
					
				}
		}		
			
     }
	
	@FXML public void onCancelarUnidade() {
		
		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
   
				flag = 0;
				btNovaUnidade.setText("Nova UH");
				habilitarTodosControles();
				tableViewUnidadeHabitacional.getSelectionModel().select(selectedIndex);
		   		
				UnidadeHabitacional unidadeHabitacional = tableViewUnidadeHabitacional.getSelectionModel().getSelectedItem();
			    if (unidadeHabitacional!=null) {
					flag = 2;
					textFieldIdentificacao.setText(String.valueOf(unidadeHabitacional.getId()));
					textFieldNome.setText(unidadeHabitacional.getNome());
					textFieldQuarto.setText(String.valueOf(unidadeHabitacional.getNQuartos()));
					textFieldNumeroUH.setText(unidadeHabitacional.getNumero());
					textFieldAndar.setText(String.valueOf(unidadeHabitacional.getAndar()));
					comboBoxTorre.setValue(unidadeHabitacional.getTorre());
					textFieldDescricao.setText(unidadeHabitacional.getDescricao());
					comboBoxCategoria.setValue(unidadeHabitacional.getCategoria());
		    		textFieldPrecoDiaria.setText(unidadeHabitacional.getCategoria().getPrecoCategoria());
					
					
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
			    }
		}    
	}

	
	@FXML public void onKeyPressedTableView() {
		tableViewUnidadeHabitacional.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	
		    	selectedIndex = tableViewUnidadeHabitacional.getSelectionModel().getSelectedIndex();
		    	
		    	UnidadeHabitacional unidadeHabitacional = tableViewUnidadeHabitacional.getSelectionModel().getSelectedItem();
			    if (unidadeHabitacional!=null) {
					flag = 2;
					textFieldIdentificacao.setText(String.valueOf(unidadeHabitacional.getId()));
					textFieldNome.setText(unidadeHabitacional.getNome());
					textFieldQuarto.setText(String.valueOf(unidadeHabitacional.getNQuartos()));
					textFieldNumeroUH.setText(unidadeHabitacional.getNumero());
					textFieldAndar.setText(String.valueOf(unidadeHabitacional.getAndar()));
					comboBoxTorre.setValue(unidadeHabitacional.getTorre());
					textFieldDescricao.setText(unidadeHabitacional.getDescricao());
					comboBoxCategoria.setValue(unidadeHabitacional.getCategoria());
		    		textFieldPrecoDiaria.setText(unidadeHabitacional.getCategoria().getPrecoCategoria());
						
					flag = 0; // Inicializa com o flag = 0, ou seja, atualizacao
					btNovaUnidade.setDisable(false); //  habilitar
				    btExcluirUnidade.setDisable(false);
				    btConfirmarUnidade.setDisable(false);
			    };
		    }
		});
		
		
		tableViewUnidadeHabitacional.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
			
			  
					  int selectedIndex1 = tableViewUnidadeHabitacional.getSelectionModel().getSelectedIndex();
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
							textFieldQuarto.setText("");
							textFieldNumeroUH.setText("");
							textFieldAndar.setText("");
							comboBoxTorre.getSelectionModel().selectFirst();
							textFieldDescricao.setText("");
							comboBoxCategoria.getSelectionModel().selectFirst();
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
		    tableViewUnidadeHabitacional.setItems(listaFiltrada);
	    }
			
    private void botoesOnOf() {
    	if (flag == 1 || flag == 0) {
    			tableViewUnidadeHabitacional.setDisable(true);
    			textFieldPesquisa.setDisable(true);
    	}
		if (flag == 1) { // Significa inclusao
		       btNovaUnidade.setDisable(false); //  habilitar
		       btExcluirUnidade.setDisable(true);
		       btConfirmarUnidade.setDisable(true);
		   }
		if (flag == 0) { // Significa atualizacao
			   btNovaUnidade.setDisable(true); // deshabilitar
		       btExcluirUnidade.setDisable(true);
		       btConfirmarUnidade.setDisable(false);
		}
	}

    private void habilitarTodosControles() {
    
		tableViewUnidadeHabitacional.setDisable(false);
		textFieldPesquisa.setDisable(false);
		btNovaUnidade.setDisable(false); //  habilitar
		btExcluirUnidade.setDisable(false);
		btConfirmarUnidade.setDisable(false);
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
    
      	textFieldQuarto.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
      	textFieldAndar.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
      	
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
          textFieldPrecoDiaria.setTextFormatter(new TextFormatter<>(filterMonetary));
          
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
            		   btNovaUnidade.setDisable(true); //  desabilitar
            		   btConfirmarUnidade.setDisable(true);
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
            		btNovaUnidade.setDisable(true); //  desabilitar
         		   btConfirmarUnidade.setDisable(true);
            		textFieldAndar.setPromptText("Não é permitido em branco!");
            		textFieldAndar.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                     } else textFieldAndar.setStyle(null);
                 });
            
            textFieldQuarto.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
            	if (!newValue.equals(oldValue)) {
            		botoesOnOf();
            	} if (textFieldQuarto.getText() == null || textFieldQuarto.getText().replaceAll(" ","").isEmpty()) { 
            		btNovaUnidade.setDisable(true); //  desabilitar
         		   btConfirmarUnidade.setDisable(true);
            		textFieldQuarto.setPromptText("Não é permitido em branco!");
            		textFieldQuarto.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                     } else textFieldQuarto.setStyle(null);
                 });
            
            textFieldNumeroUH.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
            	if (!newValue.equals(oldValue)) {
            		botoesOnOf();
            	} if (textFieldNumeroUH.getText() == null || textFieldNumeroUH.getText().replaceAll(" ","").isEmpty()) { 
            		btNovaUnidade.setDisable(true); //  desabilitar
         		   btConfirmarUnidade.setDisable(true);
            		textFieldNumeroUH.setPromptText("Não é permitido em branco!");
            		textFieldNumeroUH.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                     } else textFieldNumeroUH.setStyle(null);
                 });
           
            
            textFieldDescricao.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
            	if (!newValue.equals(oldValue)) {
            		botoesOnOf();
            	} if (textFieldDescricao.getText() == null || textFieldDescricao.getText().replaceAll(" ","").isEmpty()) { 
            		btNovaUnidade.setDisable(true); //  desabilitar
         		   btConfirmarUnidade.setDisable(true);
            		textFieldDescricao.setPromptText("Não é permitido em branco!");
            		textFieldDescricao.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                     } else textFieldDescricao.setStyle(null);
                 });
          
      }
      
  // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------
       
  
}

