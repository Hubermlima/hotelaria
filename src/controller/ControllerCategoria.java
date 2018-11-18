package controller;

import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import basedados.TableCategoriaLoad;
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
import javafx.util.converter.IntegerStringConverter;
import model.Categoria;
import util.Utilitarios;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class ControllerCategoria implements Initializable{

	@FXML private TableView<Categoria> tableViewCategoria;
	@FXML private TableColumn<Categoria, Integer> tableColumnIdentificacao;
	@FXML private TableColumn<Categoria, String> tableColumnValor;
	@FXML private TableColumn<Categoria, String> tableColumnNome;
	@FXML private Button btNovaCategoria;
	@FXML private Button btExcluirCategoria;
	@FXML private Button btConfirmarCategoria;
	@FXML private Button btCancelarCategoria;
	
	@FXML private TextField textFieldPrecoCategoria;
	@FXML private TextField textFieldCriancas;
	@FXML private TextField textFieldAdultos;
	@FXML private TextArea textFieldDescricao;
	@FXML private TextField textFieldNome;
	@FXML private TextField textFieldPesquisa;
	@FXML private TextField textFieldIdentificacao;
	@FXML private Label labelStatus;
	
	private ObservableList<Categoria> lista;
	private FilteredList<Categoria> listaFiltrada;
	private Integer selectedIndex;
    
	Alert alert;
	@FXML AnchorPane splitPaneLadoEsquerdo;
	@FXML AnchorPane splitPaneLadoDireito;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de inclusão/alteração de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
         
    	// Popular observaalelist categoria
		lista = TableCategoriaLoad.load();
		
		// Justificar colunas
		tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
		tableColumnValor.setStyle( "-fx-alignment: CENTER-RIGHT;");
        
		// popular e ordenar Tableview
		tableViewCategoria.getItems().clear();
   		tableViewCategoria.setItems(lista); 
   		lista.sort(Comparator.comparing(Categoria::getNome));
   		
   		validarCampos();
   		
	    // 1. Envolva o ObservableList em uma FilteredList (inicialmente exiba todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);
	    
        // mostrar o primeiro registro
        selectedIndex = 0;
        tableViewCategoria.getSelectionModel().select(selectedIndex);
        Categoria categoria = tableViewCategoria.getSelectionModel().getSelectedItem();
        showDetails(categoria);
        
        tableViewCategoria.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	showDetails(newSelection);
            }
        });

    }
        
    private void showDetails(Categoria categoria) {
    	
    	if (categoria != null) {
  		    selectedIndex = tableViewCategoria.getSelectionModel().getSelectedIndex();
        	textFieldIdentificacao.setText(String.valueOf(categoria.getId()));
			textFieldNome.setText(categoria.getNome());
			textFieldDescricao.setText(categoria.getDescricao());
			textFieldAdultos.setText(String.valueOf(categoria.getNAdultos()));
			textFieldCriancas.setText(String.valueOf(categoria.getNCriancas()));
			textFieldPrecoCategoria.setText(categoria.getPrecoCategoria());
			splitPaneLadoEsquerdo.setDisable(false);
        } else {
			textFieldNome.setText("");
			textFieldDescricao.setText("");
			textFieldAdultos.setText("");
			textFieldCriancas.setText("");
			textFieldPrecoCategoria.setText("");
			textFieldNome.requestFocus(); // O cursor se posiciona em nome	  		  }
            splitPaneLadoEsquerdo.setDisable(true);
        }
        
    }
        
    
	// Incluir e gravar uma nova categoria
	
	@FXML public void onNovaCategoria() {   // Nova e gravar categoria
	
		        if (lista.size() == 0)
		        	textFieldIdentificacao.setText("1");
		        else
		        	textFieldIdentificacao.setText(TableCategoriaLoad.getMaxIdCategoria(lista));
				showDetails(null);
				splitPaneLadoEsquerdo.setDisable(true);
	}

	@FXML public void onAtualizar() { // Incluir/Atualizar
		
		
		if (splitPaneLadoEsquerdo.isDisable() == true) {
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	
			    lista.add(new Categoria(Integer.valueOf(textFieldIdentificacao.getText()),
	                textFieldNome.getText(), 
	                textFieldDescricao.getText() , 
	                Integer.valueOf(textFieldAdultos.getText()),
	                Integer.valueOf(textFieldCriancas.getText()),
	                textFieldPrecoCategoria.getText())); 
			}
		} else {
		
			alert.setHeaderText("Confirmação de alteração de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
		        lista.set(selectedIndex, new Categoria(Integer.valueOf(textFieldIdentificacao.getText()),
		                textFieldNome.getText(), 
		                textFieldDescricao.getText() , 
		                Integer.valueOf(textFieldAdultos.getText()),
		                Integer.valueOf(textFieldCriancas.getText()),
		                textFieldPrecoCategoria.getText()));		
			}
		}	
		tableViewCategoria.getSelectionModel().select(selectedIndex);
        lista.sort(Comparator.comparing(Categoria::getNome));
        splitPaneLadoEsquerdo.setDisable(false);
	}
	
	@FXML public void onExcluirCategoria() {
	
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewCategoria.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					textFieldIdentificacao.setText("");
					textFieldNome.setText("");
					textFieldDescricao.setText("");
					textFieldAdultos.setText("");
					textFieldCriancas.setText("");
					textFieldPrecoCategoria.setText("");
				}
		}		
			
     }
	
	@FXML public void onCancelar() {
		
		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
	
				tableViewCategoria.getSelectionModel().select(selectedIndex);
		   		Categoria categoria = tableViewCategoria.getSelectionModel().getSelectedItem();
			    showDetails(categoria);
		}    
	}

	
	@FXML public void onKeyPressedTableView() {
		
		tableViewCategoria.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
			  
					  int selectedIndex1 = tableViewCategoria.getSelectionModel().getSelectedIndex();
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
		    tableViewCategoria.setItems(listaFiltrada);
		    
	    }

	
    public ObservableList<Categoria> getLista() {
		return lista;
	}

	public void setLista(ObservableList<Categoria> lista) {
		this.lista = lista;
	}

	public FilteredList<Categoria> getListaFiltrada() {
		return listaFiltrada;
	}

	public void setListaFiltrada(FilteredList<Categoria> listaFiltrada) {
		this.listaFiltrada = listaFiltrada;
	}

	public Integer getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(Integer selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	// --------------------------------------------------------- VALIDAÇÕES --------------------------------------------------------------------

	private void validarCampos() {
      	  
        	textFieldAdultos.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, Utilitarios.integerFilter));
        	textFieldCriancas.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, Utilitarios.integerFilter));
            textFieldPrecoCategoria.setTextFormatter(new TextFormatter<>(Utilitarios.filterMonetary));
            textFieldNome.setTextFormatter(new TextFormatter<>(Utilitarios.filterMaxLength));
            
            // botao Confirmar desabilitado enquanto existir um dos campos abaixo em branco
            btConfirmarCategoria.disableProperty().bind(textFieldNome.textProperty().isEmpty().or
                      (textFieldDescricao.textProperty().isEmpty().or
                      (textFieldCriancas.textProperty().isEmpty().or
                      (textFieldPrecoCategoria.textProperty().isEmpty().or
                      (textFieldAdultos.textProperty().isEmpty())))));
        
	}        
    // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------

}