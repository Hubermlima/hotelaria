package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import basedados.TableAgenciaViagemLoad;
import basedados.TableHospedeLoad;
import basedados.TablePaisLoad;
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
import model.DocumentoIdentificacao;
import model.Genero;
import model.Hospede;
import model.Logradouro;
import model.MotivoViagem;
import model.Pais;
import model.TituloTratamento;
import util.Utilitarios;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;

public class ControllerHospede implements Initializable{

	@FXML private TableView<Hospede> tableViewHospede;
	@FXML private TableColumn<Hospede, Integer> tableColumnIdentificacao;
	@FXML TableColumn<Hospede, String> tableColumnSobrenome;
	@FXML private TableColumn<Hospede, String> tableColumnNome;
	
	@FXML private Button btNovaHospede;
	@FXML private Button btExcluirHospede;
	@FXML private Button btConfirmarHospede;
	@FXML private Button btCancelarHospede;
	
	@FXML AnchorPane splitPaneLadoEsquerdo;
	@FXML AnchorPane splitPaneLadoDireito;
	@FXML TextField textFieldPesquisa;
	@FXML TextField textFieldIdentificacao;
	@FXML TextField textFieldNome;
	@FXML TextField textFieldSobrenome;
	@FXML DatePicker datePickerAniversario;
	@FXML TextField textFieldNumeroDocumento;
	@FXML TextField textFieldOrgaoExpedidor;
	@FXML TextField textFieldNacionalidade;
	@FXML TextField textFieldNomeRua;
	@FXML TextField textFieldNumeroResidencia;
	@FXML TextField textFieldComplemento;
	@FXML TextField textFieldCidade;
	@FXML TextField textFieldCodigoPostal;
	@FXML TextField textFieldEstado;
	@FXML TextField textFieldBairro;
	@FXML TextField textFieldProfissao;
	@FXML DatePicker datePickerUltimaVisita;
	@FXML TextField textFieldEmpresa;
	@FXML TextField textFieldEmailEmpresa;
	@FXML TextField textFieldTelefoneEmpresa;
	@FXML TextField textFieldPrimeiroEmail;
	@FXML TextField textFieldSegundoEmail;
	@FXML TextField textFieldPrimeiroTelefone;
	@FXML TextField textFieldSegundoTelefone;
	
	@FXML private Label labelStatus;

	@FXML ComboBox<Pais> comboBoxPais;
	@FXML ComboBox<String> comboBoxTipoDocumento;
	@FXML ComboBox<String> comboBoxGenero;
	@FXML ComboBox<String> comboBoxTitulo;
	@FXML ComboBox<String> comboBoxLogradouro;
	@FXML ComboBox<String> comboBoxMotivoViagem;
	@FXML ComboBox<AgenciaViagem> comboBoxAgencia;
	
	ObservableList<Hospede> lista;
	FilteredList<Hospede> listaFiltrada;
	ObservableList<Pais> listaPaises;
	ObservableList<AgenciaViagem> listaAgencias;
	
	Integer selectedIndex;
    
	Alert alert;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de inclusão/alteração de informações!");
    	alert.setContentText("Deseja realmente seguir em frente?");
         
    	// Popular observaaleList Hospede, pais, agencias
		lista = TableHospedeLoad.load();
		listaPaises = TablePaisLoad.load();
		listaAgencias = TableAgenciaViagemLoad.load();
		
		
		// Justificar colunas
		tableColumnIdentificacao.setStyle( "-fx-alignment: CENTER;");
        
		// popular e ordenar Tableview
		tableViewHospede.getItems().clear();
   		tableViewHospede.setItems(lista); 
   		lista.sort(Comparator.comparing(Hospede::getSobrenome));
   		
   	    // popular combobox paises
   		comboBoxPais.getItems().clear();
   		comboBoxPais.setItems(listaPaises);
   		listaPaises.sort(Comparator.comparing(Pais::getNome));
   		
   	    // popular combobox agencias de viagens
   		comboBoxAgencia.getItems().clear();
   		comboBoxAgencia.setItems(listaAgencias);
   		listaAgencias.sort(Comparator.comparing(AgenciaViagem::getNome));
   			
   		/*
   		 * Popular combos ENUMs
   		 */
   	    // TITULO
   		comboBoxTitulo.getItems().clear(); 
   	    for(TituloTratamento titulo : TituloTratamento.values()){
   	    	comboBoxTitulo.getItems().add(titulo.getDescricao());
         }
   	    // GENERO
   		comboBoxGenero.getItems().clear(); 
   	    for(Genero genero : Genero.values()){
   	    	comboBoxGenero.getItems().add(genero.getDescricao());
         }
   	    // DOCUMENTO IDENTIFICACAO
   		comboBoxTipoDocumento.getItems().clear(); 
   	    for(DocumentoIdentificacao tipo : DocumentoIdentificacao.values()){
   	    	comboBoxTipoDocumento.getItems().add(tipo.getDescricao());
         }
      	// LOGRADOURO
   		comboBoxLogradouro.getItems().clear(); 
   	    for(Logradouro logradouro : Logradouro.values()){
   	    	comboBoxLogradouro.getItems().add(logradouro.getDescricao());
         }
   	     // MOTIVO VIAGEM
   	     comboBoxMotivoViagem.getItems().clear(); 
	     for(MotivoViagem motivo : MotivoViagem.values()){
	    	 comboBoxMotivoViagem.getItems().add(motivo.getDescricao());
         }
   	    
   		validarCampos();
   		
	    // 1. Envolva o ObservableList em uma FilteredList (inicialmente exiba todos os dados).
        listaFiltrada = new FilteredList <> (lista, s -> true);
	    
        // mostrar o primeiro registro
        selectedIndex = 0;
        tableViewHospede.getSelectionModel().select(selectedIndex);
        Hospede Hospede = tableViewHospede.getSelectionModel().getSelectedItem();
        showDetails(Hospede);
        
        tableViewHospede.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	showDetails(newSelection);
            }
        });

       // verificar a lista ordenada aqui
    }
        
    private void showDetails(Hospede Hospede) {
    	
    	if (Hospede != null) {
    		
  		    selectedIndex = tableViewHospede.getSelectionModel().getSelectedIndex();
        	textFieldIdentificacao.setText(String.valueOf(Hospede.getId()));
        	textFieldNome.setText(Hospede.getNome());
        	textFieldSobrenome.setText(Hospede.getSobrenome());
        	datePickerAniversario.setValue(Hospede.getDataAniversario());
        	textFieldNumeroDocumento.setText(Hospede.getNumeroDocumento());
        	textFieldOrgaoExpedidor.setText(Hospede.getOrgaoExpedidor());
        	textFieldNacionalidade.setText(Hospede.getNacionalidade());
        	textFieldNomeRua.setText(Hospede.getNomeRua());
        	textFieldNumeroResidencia.setText(Hospede.getNumeroCasa());
        	textFieldComplemento.setText(Hospede.getComplemento());
        	textFieldBairro.setText(Hospede.getBairro());
        	textFieldCodigoPostal.setText(Hospede.getCodigoPostal());
        	textFieldCidade.setText(Hospede.getCidade());
        	textFieldEstado.setText(Hospede.getEstado());
        	textFieldProfissao.setText(Hospede.getProfissao());
        	datePickerUltimaVisita.setValue(Hospede.getDataUltimaVisita());
        	textFieldEmpresa.setText(Hospede.getEmpresa());
        	textFieldEmailEmpresa.setText(Hospede.getEmailEmpresa());
        	textFieldTelefoneEmpresa.setText(Hospede.getFoneEmpresa());
        	textFieldPrimeiroEmail.setText(Hospede.getPrimeiroEmail());
        	textFieldSegundoEmail.setText(Hospede.getSegundoEmail());
        	textFieldPrimeiroTelefone.setText(Hospede.getPrimeiroFone());
        	textFieldSegundoTelefone.setText(Hospede.getSegundoFone());
        	
        	comboBoxLogradouro.setValue(Logradouro.getNome(Hospede.getLogradouro()).toString());
        	comboBoxTitulo.setValue(TituloTratamento.getNome(Hospede.getTitulo()).toString());
        	comboBoxGenero.setValue(Genero.getNome(Hospede.getGenero()).toString());
        	comboBoxTipoDocumento.setValue(DocumentoIdentificacao.getNome(Hospede.getTipoDocumento()).toString());
        	comboBoxMotivoViagem.setValue(MotivoViagem.getNome(Hospede.getMotivoViagem()).toString());

        	comboBoxPais.setValue(Hospede.getPais());
        	comboBoxAgencia.setValue(Hospede.getAgencia());
        	
			splitPaneLadoEsquerdo.setDisable(false);
        } else {
        	textFieldIdentificacao.setText(TableHospedeLoad.getMaxIdHospede(lista));
        	textFieldNome.setText("");
        	textFieldSobrenome.setText("");
        	datePickerAniversario.setValue(LocalDate.now());
        	textFieldNumeroDocumento.setText("");
        	textFieldOrgaoExpedidor.setText("");
        	textFieldNacionalidade.setText("");
        	textFieldNomeRua.setText("");
        	textFieldNumeroResidencia.setText("");
        	textFieldComplemento.setText("");
        	textFieldBairro.setText("");
        	textFieldCodigoPostal.setText("");
        	textFieldCidade.setText("");
        	textFieldEstado.setText("");
        	textFieldProfissao.setText("");
        	datePickerUltimaVisita.setValue(LocalDate.now());
        	textFieldEmpresa.setText("");
        	textFieldEmailEmpresa.setText("");
        	textFieldTelefoneEmpresa.setText("");
        	textFieldPrimeiroEmail.setText("");
        	textFieldSegundoEmail.setText("");
        	textFieldPrimeiroTelefone.setText("");
        	textFieldSegundoTelefone.setText("");

        	comboBoxTitulo.getSelectionModel().selectFirst();
        	comboBoxLogradouro.getSelectionModel().selectFirst();
        	comboBoxGenero.getSelectionModel().selectFirst();
        	comboBoxTipoDocumento.getSelectionModel().selectFirst();
        	comboBoxPais.getSelectionModel().selectFirst();
        	comboBoxMotivoViagem.getSelectionModel().selectFirst();
        	comboBoxAgencia.getSelectionModel().selectFirst();
        	comboBoxTitulo.requestFocus(); // O cursor se posiciona em titulo	  		  }
            splitPaneLadoEsquerdo.setDisable(true);
        }
        
    }
        
    
	// Incluir e gravar uma nova Hospede
	
	@FXML public void onNovaHospede() {   // Nova e gravar Hospede
	
				textFieldIdentificacao.setText(TableHospedeLoad.getMaxIdHospede(lista));
				showDetails(null);
				splitPaneLadoEsquerdo.setDisable(true);
	}

	@FXML public void onAtualizar() { // Incluir/Atualizar
		
		Pais pais = new Pais();
		pais = comboBoxPais.getValue();
		AgenciaViagem agencia = new AgenciaViagem();
		agencia = comboBoxAgencia.getValue();
		
		Hospede hospede = new Hospede(Integer.valueOf(textFieldIdentificacao.getText()),
            	TituloTratamento.getCodigo(comboBoxTitulo.getValue().toString()),
	            textFieldNome.getText(),
                textFieldSobrenome.getText(),
                datePickerAniversario.getValue(),
                Genero.getCodigo(comboBoxGenero.getValue().toString()),
                DocumentoIdentificacao.getCodigo(comboBoxTipoDocumento.getValue().toString()),
                textFieldNumeroDocumento.getText(),
                textFieldOrgaoExpedidor.getText(),
                textFieldNacionalidade.getText(),
                Logradouro.getCodigo(comboBoxLogradouro.getValue().toString()),
                textFieldNomeRua.getText(),
                textFieldNumeroResidencia.getText(),
                textFieldComplemento.getText(),
                textFieldBairro.getText(),
                textFieldCodigoPostal.getText(),
                textFieldCidade.getText(),
                textFieldEstado.getText(), 
                textFieldProfissao.getText(),
                MotivoViagem.getCodigo(comboBoxMotivoViagem.getValue().toString()),
                datePickerUltimaVisita.getValue(),
                textFieldEmpresa.getText(),
                textFieldEmailEmpresa.getText(),
                textFieldTelefoneEmpresa.getText(),
                textFieldPrimeiroEmail.getText(),
                textFieldSegundoEmail.getText(),
                textFieldPrimeiroTelefone.getText(),
                textFieldSegundoTelefone.getText());
		hospede.setPais(pais);
		hospede.setAgencia(agencia);
		
		if (splitPaneLadoEsquerdo.isDisable() == true) {  // significa inclusao
			alert.setHeaderText("Confirmação de inclusão de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	            lista.add(hospede);
			}
		} else {
			alert.setHeaderText("Confirmação de alteração de informações!");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
		        lista.set(selectedIndex, hospede);
			}
		}	
		tableViewHospede.getSelectionModel().select(selectedIndex);
        lista.sort(Comparator.comparing(Hospede::getSobrenome));
        splitPaneLadoEsquerdo.setDisable(false);
	}
	
	@FXML public void onExcluirHospede() {
	
		alert.setHeaderText("Confirmação de exclusão de informações!");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
    	
				int selectedIndex = tableViewHospede.getSelectionModel().getSelectedIndex();
				if (selectedIndex>=0)
				     lista.remove(selectedIndex);
				else {
					showDetails(null);
				}
		}		
			
     }
	
	@FXML public void onCancelar() {
		
		alert.setHeaderText("Confirmação de cancelamento de informações!");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
	
				tableViewHospede.getSelectionModel().select(selectedIndex);
		   		Hospede Hospede = tableViewHospede.getSelectionModel().getSelectedItem();
			    showDetails(Hospede);
		}    
	}

	
	@FXML public void onKeyPressedTableView() {
		
		
		tableViewHospede.setOnKeyPressed( new EventHandler<KeyEvent>() {
		  @Override
		  public void handle( final KeyEvent keyEvent ) {
			  
					  int selectedIndex1 = tableViewHospede.getSelectionModel().getSelectedIndex();
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
		            listaFiltrada.setPredicate(s -> s.getSobrenome().toUpperCase().contains(filter.toUpperCase()));
		        }
		    });
		    tableViewHospede.setItems(listaFiltrada);
		    
	    }

 // --------------------------------------------------------- VALIDAÇÕES --------------------------------------------------------------------
    
    private void validarCampos() {
      	    
            textFieldNome.setTextFormatter(new TextFormatter<>(Utilitarios.filterMaxLength));
         // botao Confirmar desabilitado enquanto existir um dos campos abaixo em branco 
            btConfirmarHospede.disableProperty().bind(textFieldSobrenome.textProperty().isEmpty().or
                      (textFieldNome.textProperty().isEmpty().or
                      (textFieldNumeroDocumento.textProperty().isEmpty().or
                      (textFieldOrgaoExpedidor.textProperty().isEmpty().or
                      (textFieldNacionalidade.textProperty().isEmpty().or
                      (textFieldNomeRua.textProperty().isEmpty().or
                      (textFieldNumeroResidencia.textProperty().isEmpty().or		  
                      (textFieldComplemento.textProperty().isEmpty().or
                      (textFieldBairro.textProperty().isEmpty().or
                      (textFieldCidade.textProperty().isEmpty().or
                      (textFieldEstado.textProperty().isEmpty().or
                      (textFieldEmpresa.textProperty().isEmpty().or 
                      (textFieldEmailEmpresa.textProperty().isEmpty().or
                      (textFieldTelefoneEmpresa.textProperty().isEmpty().or
                      (textFieldPrimeiroEmail.textProperty().isEmpty().or
                      (textFieldSegundoEmail.textProperty().isEmpty().or
                      (textFieldPrimeiroTelefone.textProperty().isEmpty().or
                      (textFieldSegundoTelefone.textProperty().isEmpty()))))))))))))))))));
       
        }
       
        
    // ------------------------------------------------------ FIM VALIDAÇÕES --------------------------------------------------------------------

}