package controller;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import basedados.TableHospedeLoad;
import basedados.TableItemReservaLoad;
import basedados.TableReservaLoad;
import basedados.TableServicoLoad;
import basedados.TableTipoPromocionalLoad;
import basedados.TableUnidadeHabitacionalLoad;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import model.Hospede;
import model.ItemReserva;
import model.LotacaoAdulto;
import model.LotacaoCrianca;
import model.QuartoDuplicado;
import model.RegraNegocio;
import model.Reserva;
import model.ResumoReserva;
import model.Servico;
import model.TipoPromocional;
import model.UnidadeHabitacional;
import model.UnidadesHabitacionaisDisponivel;
import util.Utilitarios;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

public class ControllerReserva implements Initializable{

	private ObservableList<Reserva> lista;
	private ObservableList<Hospede> listaHospedes;
	private ObservableList<UnidadeHabitacional> listaUH;
	private ObservableList<TipoPromocional> listaTipoPromocional;
	ObservableList<UnidadesHabitacionaisDisponivel> listaDisponibilidade = FXCollections.observableArrayList();
	ObservableList<ItemReserva> listaItemReserva = FXCollections.observableArrayList();
	private ObservableList<Servico> listaServicos;
	
	Reserva reserva;
	ItemReserva itemReserva;
	UnidadesHabitacionaisDisponivel unidadesHabitacionaisDisponivel;
	LotacaoAdulto lotacaoAdulto;
	LotacaoCrianca lotacaoCrianca;
	RegraNegocio quartoDuplicado;
	RegraNegocio criancasOnly;
	private Integer selectedIndex;
	private String dataIn;
	private String dataOut;
	LocalTime horaCheck;
    LocalDate dataCheck;
    LocalDateTime dataCkeckOut;
    LocalDateTime dataCkeckIn;
    IntegerSpinnerValueFactory svf;
	Integer idItem;
	
	// Transformações numéricas
	NumberFormat formatWithComma = Utilitarios.meuFormato(); //numerico -> String com virgula (2.5 -> 2,5)
	/*
	 *  double a = 50000.00;
        System.out.println(formatWithCommon.format(a));
	 */
	
	NumberFormat formatWithPoint = NumberFormat.getInstance(Locale.FRANCE);  // String -> numerico com ponto (2,5 -> 2.5)
	/*
	 * format.parse("2,5").doubleValue()
	 */
	
    Alert alert;
    
	@FXML DatePicker datePickerDataOperacao;
	@FXML TextField numeroReserva;
	@FXML TextField textFieldFNRH;
	@FXML AnchorPane splitEsquerdo;
	@FXML ComboBox<Hospede> comboBoxHospede;
	@FXML TextField textFieldNumeroAdultos;
	@FXML TextField textFieldNumeroCriancas;
	@FXML DatePicker datePickerCkeckin;
	@FXML Button buttonVerificarDisponibilidade;
	@FXML Button buttonAdicionarServicos;
	@FXML AnchorPane splitCentro;
	
	@FXML TableView<UnidadesHabitacionaisDisponivel> tableViewUnidadesDisponiveis;
	@FXML TableColumn<UnidadesHabitacionaisDisponivel, String> tableColumnNumero;
	@FXML TableColumn<UnidadesHabitacionaisDisponivel, String> tableColumnNome;
	@FXML TableColumn<UnidadesHabitacionaisDisponivel, String> tableColumnValor;
	@FXML TableColumn<UnidadesHabitacionaisDisponivel, String> tableColumnCheckIn;
	@FXML TableColumn<UnidadesHabitacionaisDisponivel, String> tableColumnCheckOut;
	
	@FXML TableView<Servico> tableViewServicos;
	@FXML TableColumn<Servico, String> tableColumnServicosNome;
	@FXML TableColumn<Servico, String> tableColumnServicosValor;
	@FXML TableColumn<Servico, Boolean>  tableColumnServicosMyCheck;
	
	@FXML TableView<ItemReserva> tableViewItensReserva;
	@FXML TableColumn<ItemReserva, String> tableColumnNomeItens;
	@FXML TableColumn<ItemReserva, String> tableColumnNumeroItens;
	@FXML TableColumn<ItemReserva, Integer> tableColumnNAdultosItens;
	@FXML TableColumn<ItemReserva, Integer> tableColumnNCriancasItens;
	@FXML TableColumn<ItemReserva, String> tableColumnTotalDiariaItens;
	@FXML TableColumn<ItemReserva, String> tableColumnTotalServicoItens;
	@FXML TableColumn<ItemReserva, Void> tableColumnButton;
	
	@FXML Spinner<Integer> spinnerNoite;
	@FXML Button buttonConfirmarServicos;
	@FXML Button buttonConfirmarAlocacao;
	@FXML Button buttonSelecionarServico;
	
	@FXML Label labelTipoDesconto;
	@FXML Label labelAgencia;
	
	@FXML AnchorPane splitDireito;
	
	@FXML TextField textFieldNomeQuarto;
	@FXML TextField textFieldNumeroQuarto;
	@FXML TextField textFieldAndarQuarto;
	@FXML TextField textFieldQuantidadeQuartos;
	@FXML TextField textFieldTorre;
	@FXML TextField textFieldCategoria;
	@FXML TextArea textAreaDescricao;
	@FXML TextField textFieldFaltaAlocarAdultos;
	@FXML TextField textFieldFaltaAlocarCriancas;
	@FXML TextField textFieldCheckOut;
	@FXML TextField textFieldPrecoTarifa;
	@FXML TextField textFieldTotalServicos;
	@FXML TextField textFieldNoites;
	@FXML TextField textFieldUnidadesAlocadas;
	@FXML TextField textFieldTotalGeralReserva;
	@FXML TextField textFieldTotalGeralServicos;
	@FXML TextField textFieldDescontoPermanencia;
	@FXML TextField textFieldTipoPromocional;
	@FXML TextField textFieldDescontoGrupo;
	@FXML TextField textFieldTotalDesconto;
	@FXML TextField textFieldTotalGeralGeral;
	@FXML TextField textFieldTotalLiquido;
	@FXML TextField textFieldEntradaEspecie;
	@FXML TextField textFieldSaldoDevedor;
	@FXML TextField textFieldComissaoAgencia;
	
	@FXML TabPane tabPanePrincipal;
	@FXML TabPane tabpaneDetalhesUnidade;
	@FXML Tab tabServicos;

	@FXML ComboBox<Integer> comboBoxAlocarAdultos;
	@FXML ComboBox<Integer> comboBoxAlocarCriancas;
	@FXML ComboBox<TipoPromocional> comboBoxTipoPromocional;
	@FXML Button buttonRetornaUH;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	// Inicializa a lotação 
    	lotacaoAdulto = new LotacaoAdulto(0, 0);
    	lotacaoCrianca = new LotacaoCrianca(0, 0);
    	
    	horaCheck = LocalTime.of(14, 0); // CheckIn sempre as 14:00
    	
    	// Formata os campos para receber apenas números
    	validarCampos();
    	
    	alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Confirmação de nova reserva!");
    	alert.setContentText("Deseja realmente seguir em frente?");
    
    	// Justificar colunas tableViewUnidadesDisponiveis
    	tableColumnNumero.setStyle( "-fx-alignment: CENTER;");
    	tableColumnValor.setStyle( "-fx-alignment: CENTER-RIGHT;");
    	tableColumnCheckIn.setStyle( "-fx-alignment: CENTER;");
    	tableColumnCheckOut.setStyle( "-fx-alignment: CENTER;");
    	
    	// Justificar colunas tableViewItensReserva
    	tableColumnNumeroItens.setStyle( "-fx-alignment: CENTER;");;
    	tableColumnNAdultosItens.setStyle( "-fx-alignment: CENTER;");
    	tableColumnNCriancasItens.setStyle( "-fx-alignment: CENTER;");
    	tableColumnTotalDiariaItens.setStyle( "-fx-alignment: CENTER-RIGHT;");
    	tableColumnTotalServicoItens.setStyle( "-fx-alignment: CENTER-RIGHT;");
    	tableColumnButton = new TableColumn<ItemReserva, Void>("Ação");
    	tableColumnButton.setStyle( "-fx-alignment: CENTER;");
    	
    	// Popular observablelists
    	lista = TableReservaLoad.load();
    	listaUH = TableUnidadeHabitacionalLoad.load();
    	listaServicos = TableServicoLoad.reLoadChecked();
    	listaTipoPromocional = TableTipoPromocionalLoad.load();
    	
    	// popular e ordenar Tableview itens reserva
    	tableViewItensReserva.getItems().clear();
    	tableViewItensReserva.setItems(listaItemReserva); 
    	listaItemReserva.sort(Comparator.comparing(ItemReserva::getNome));
    	
    	// Carregar um novo id
    	if (lista.size() == 0)
    		 numeroReserva.setText("1");
    	else
    		 numeroReserva.setText(TableReservaLoad.getMaxIdReserva(lista));
    	
    	// Carregar a data atual
    	datePickerDataOperacao.setValue(LocalDate.now());
    	// Carregar o numero FNRH
    	textFieldFNRH.setText("");
    	
    	// popular combobox Hospede
    	listaHospedes = TableHospedeLoad.loadParcial();
   		comboBoxHospede.getItems().clear();
   		comboBoxHospede.setItems(listaHospedes);
   		listaHospedes.sort(Comparator.comparing(Hospede::getSobrenome));
   		comboBoxHospede.getSelectionModel().selectFirst();
   		labelAgencia.setText("9. Agente: " + comboBoxHospede.getValue().getAgencia().getNome() + " (" + comboBoxHospede.getValue().getAgencia().getValorComissao() + "%)");
   		
   	    // popular combobox TIPO PROMOCIONAL
    	listaTipoPromocional = TableTipoPromocionalLoad.load();
    	comboBoxTipoPromocional.getItems().clear();
    	comboBoxTipoPromocional.setItems(listaTipoPromocional);
    	listaTipoPromocional.sort(Comparator.comparing(TipoPromocional::getNome));
    	comboBoxTipoPromocional.getSelectionModel().selectFirst();
    	labelTipoDesconto.setText("7. Tipo de desconto: " + comboBoxTipoPromocional.getValue().getNome() + " (" + comboBoxTipoPromocional.getValue().getPorcentagemDesconto() + "%)");
   		
        // preencher adultos
   		textFieldNumeroAdultos.setText("1");
   		//preencher criancas
   		textFieldNumeroCriancas.setText("0");
   		
   		// preencher spinner ate 30
   		svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30);
   	    spinnerNoite.setValueFactory(svf);
   	    textFieldNoites.setText(String.valueOf(spinnerNoite.getValue())); // Campo pertence ao Extrato
   	    
   	    // Carregar a data checkIn
    	datePickerCkeckin.setValue(LocalDate.now());
    	manipuladorData();
    
    	//Setando o CheckboxCell na coluna do tableViewServicos
    	tableColumnServicosMyCheck.setCellValueFactory(p -> new SimpleBooleanProperty(p.getValue() != null));
    	tableColumnServicosMyCheck.setCellFactory(param -> new CheckBoxCell(tableViewServicos));
    	// Justificar colunas tableViewServicos
    	tableColumnServicosValor.setStyle( "-fx-alignment: CENTER-RIGHT;");
    	tableColumnServicosMyCheck.setStyle( "-fx-alignment: CENTER;");
    	tableViewServicos.setItems(listaServicos);
    	listaServicos.sort(Comparator.comparing(Servico::getNome));
    	
    	// Listener em quantidade de noites
    	spinnerNoite.valueProperty().addListener((obs, oldValue, newValue) -> {
    	  	manipuladorData();
    	  	textFieldNoites.setText(String.valueOf(spinnerNoite.getValue()));
        });
    	
    	// Listener em selecao de adultos
    	comboBoxAlocarAdultos.setOnAction((e) -> {
    	  if (comboBoxAlocarAdultos.getSelectionModel().getSelectedItem() != null) {
    		    verificarLotacaoAdulto(comboBoxAlocarAdultos.getSelectionModel().getSelectedItem());
    		}
    	});
    	
    	// Listener em selecao de crianças
    	comboBoxAlocarCriancas.setOnAction((e) -> {
    	   if (comboBoxAlocarCriancas.getSelectionModel().getSelectedItem() != null) {
    		   verificarLotacaoCrianca(comboBoxAlocarCriancas.getSelectionModel().getSelectedItem());
    	   }	
    	});
    	
    	// Listener em Data de entrada
    	datePickerCkeckin.valueProperty().addListener((obs, oldValue, newValue) -> {
                     manipuladorData();    	       
        });
    	
    	// Listener em tableView de Unidades Disponiveis
    	tableViewUnidadesDisponiveis.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    	  if (newSelection != null) {
    		quartoDuplicado = new QuartoDuplicado(listaItemReserva, newSelection.getNumero());
    		//showDetailsUHDisponiveis(newSelection);
    		if (!quartoDuplicado.regra())	{
        		Utilitarios.showAlert("Trump Hotels - RESERVAS", 
                                      "PROCESSO DE ALOCAÇÃO", 
                                      "Operação inválida: Unidade Habitacional já alocada!");
        		tabpaneDetalhesUnidade.setDisable(true);
        		tabServicos.setDisable(true);
        	} else {
        		tabpaneDetalhesUnidade.setDisable(false);
        		tabServicos.setDisable(false);
        	  }
    		showDetailsUHDisponiveis(newSelection);
    		
    		}
        });
    	
    	// Listener em comboBox de selecao de Promocao
    	comboBoxTipoPromocional.valueProperty().addListener(new ChangeListener<TipoPromocional>() {
			@Override
			public void changed(ObservableValue<? extends TipoPromocional> observable, TipoPromocional oldValue, TipoPromocional newValue) {
				labelTipoDesconto.setText("7. Tipo de desconto: " + newValue.getNome() + " (" + newValue.getPorcentagemDesconto() + "%)");
			 }
		});
    	
    	comboBoxHospede.valueProperty().addListener(new ChangeListener<Hospede>() {
			@Override
			public void changed(ObservableValue<? extends Hospede> observable, Hospede oldValue, Hospede newValue) {
				labelAgencia.setText("9. Agente: " + newValue.getAgencia().getNome() + " (" + newValue.getAgencia().getValorComissao() + "%)");
			 }
		});
    	
    	textFieldEntradaEspecie.textProperty().addListener((observable, oldValue, newValue) -> {
    		try {
    		    if (!newValue.isEmpty()) {
				textFieldSaldoDevedor.setText(formatWithComma.format(
								             formatWithPoint.parse(textFieldTotalLiquido.getText()).doubleValue() -
								             formatWithPoint.parse(newValue).doubleValue()
								));
    		    }
			} catch (ParseException e) {
				System.out.println(e);
				e.printStackTrace();
			}
    	});

    	// Ir para tab de serviços (next)
    	buttonSelecionarServico.setOnAction((e) -> {
     	   tabPanePrincipal.getSelectionModel().select(1);
     	});
    	
    	// Ir para tab de unidades (previous)
    	buttonRetornaUH.setOnAction((e) -> {
     	   tabPanePrincipal.getSelectionModel().select(0);
     	});
    	
         // Rotina que acrescenta um botão ao TableView Itens Reserva 
         Callback<TableColumn<ItemReserva, Void>, TableCell<ItemReserva, Void>> cellFactory = new Callback<TableColumn<ItemReserva, Void>, TableCell<ItemReserva, Void>>() {
             @Override
             public TableCell<ItemReserva, Void> call(final TableColumn<ItemReserva, Void> param) {
                 final TableCell<ItemReserva, Void> cell = new TableCell<ItemReserva, Void>() {

                     private final Button btn = new Button("Remover");

                     {
                         // Adiciona um evento ao botão recem adicionado
                    	 btn.setOnAction((ActionEvent event) -> {
                        	 ItemReserva data = getTableView().getItems().get(getIndex());
                        	 alert.setHeaderText("Confirmação de exclusão de quarto alocado!");
     	       				  Optional<ButtonType> result = alert.showAndWait();
     						  if (result.get() == ButtonType.OK){
     	                                 lotacaoAdulto.remove(data.getNAdultos());
     	                                 lotacaoCrianca.remove(data.getNCriancas());
     	                                 
     	                                 textFieldFaltaAlocarAdultos.setText(String.valueOf(lotacaoAdulto.faltaAlocar()));
     	                                 textFieldFaltaAlocarCriancas.setText(String.valueOf(lotacaoCrianca.faltaAlocar()));
     	                               
     	                                 listaItemReserva.remove(data);
     	                               
     	                                try {
   										resumeReserva();
   								    	} catch (ParseException e) {
   										// TODO Auto-generated catch block
   										e.printStackTrace();
   									}
     						  }
                        	 
                         });
                     }

                     @Override
                     public void updateItem(Void item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty) {
                             setGraphic(null);
                         } else {
                             setGraphic(btn);
                         }
                     }
                 };
                 return cell;
             }
         };
         tableColumnButton.setCellFactory(cellFactory);
         tableViewItensReserva.getColumns().add(tableColumnButton);
    	
         
    } // FIM DO INICIALIZE

    //Classe responsavel por renderizar o checkbox na tableview Servicos
    private class CheckBoxCell extends TableCell<Servico, Boolean> {
    	final CheckBox checkBox = new CheckBox();
        public CheckBoxCell(final TableView<Servico> tableView) {
            checkBox.selectedProperty().addListener(a -> {
                if (this.checkBox.isSelected()) { 
                	// se esta checado
                    int rowIndex = getTableRow().getIndex();
                    tableView.getSelectionModel().select(rowIndex);
                    
                    try {
						textFieldTotalServicos.setText(formatWithComma.format(formatWithPoint.parse(textFieldTotalServicos.getText()).doubleValue() +
								formatWithPoint.parse(tableView.getSelectionModel().getSelectedItem().getValorTarifa()).doubleValue() 
							                            ));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } else { 
                	// se não esta checado
                	int rowIndex = getTableRow().getIndex();
                    tableView.getSelectionModel().select(rowIndex);
                    
                    try {
						textFieldTotalServicos.setText(formatWithComma.format(formatWithPoint.parse(textFieldTotalServicos.getText()).doubleValue() -
								formatWithPoint.parse(tableView.getSelectionModel().getSelectedItem().getValorTarifa()).doubleValue() 
							                            ));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                
                
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(checkBox);
            } else {
                setGraphic(null);
            }
        }
    }
    
    private void showDetailsUHDisponiveis(UnidadesHabitacionaisDisponivel unidadeDisponivel) {
    	
    	if (unidadeDisponivel != null) {
    	    this.unidadesHabitacionaisDisponivel = unidadeDisponivel;
  		    selectedIndex = tableViewUnidadesDisponiveis.getSelectionModel().getSelectedIndex();
  		    textFieldNomeQuarto.setText(unidadeDisponivel.getUnidadeHabitacional().getNome());
  		    textFieldNumeroQuarto.setText(unidadeDisponivel.getUnidadeHabitacional().getNumero());
  		    textFieldAndarQuarto.setText(String.valueOf(unidadeDisponivel.getUnidadeHabitacional().getAndar()));
   		    textFieldQuantidadeQuartos.setText(String.valueOf(unidadeDisponivel.getUnidadeHabitacional().getNQuartos()));
  		    textFieldTorre.setText(unidadeDisponivel.getUnidadeHabitacional().getTorre().getNome());
  		    textFieldCategoria.setText(unidadeDisponivel.getUnidadeHabitacional().getCategoria().getNome());
  		    textAreaDescricao.setText(unidadeDisponivel.getUnidadeHabitacional().getDescricao());
    	    textFieldPrecoTarifa.setText(unidadeDisponivel.getUnidadeHabitacional().getCategoria().getPrecoCategoria());
   
    	    // popular comboBoxs
    	    comboBoxAlocarAdultos.getItems().clear();
    	    IntStream
    	      .rangeClosed(1,unidadeDisponivel.getUnidadeHabitacional().getCategoria().getNAdultos())
    	      .boxed()
    	      .forEach(comboBoxAlocarAdultos.getItems()::add);
    	    comboBoxAlocarAdultos.getSelectionModel().selectFirst();
    	    
    	    comboBoxAlocarCriancas.getItems().clear();
    	    IntStream
    	       .rangeClosed(0,unidadeDisponivel.getUnidadeHabitacional().getCategoria().getNCriancas())
    	       .boxed()
    	       .forEach(comboBoxAlocarCriancas.getItems()::add);
    	    comboBoxAlocarCriancas.getSelectionModel().selectFirst();
    	    
    	}
        
    }

    @FXML public void onVerificarDisponibilidade() {
    
    	lotacaoAdulto.setLotacaoMaxAdulto(Integer.valueOf(textFieldNumeroAdultos.getText()));
    	lotacaoCrianca.setLotacaoMaxCrianca(Integer.valueOf(textFieldNumeroCriancas.getText()));

    	textFieldFaltaAlocarAdultos.setText(String.valueOf(lotacaoAdulto.faltaAlocar()));
        textFieldFaltaAlocarCriancas.setText(String.valueOf(lotacaoCrianca.faltaAlocar()));
    	
    	listaDisponibilidade.clear();
		DateTimeFormatter aFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm"); 

    	for (UnidadeHabitacional unidadeHabitacional : listaUH) {
    		
    		dataIn = dataCkeckIn.format(aFormatter);
    		dataOut = dataCkeckOut.format(aFormatter);

    		for (Reserva reserva : lista) {
    			
    			for (ItemReserva itemReserva : reserva.getItensReserva()) {
    				
		    			if (itemReserva.getUnidadeHabitacional().getNumero().equals(unidadeHabitacional.getNumero())) {
		    				
		    				if (dataCkeckIn.isBefore(reserva.getDataCheckIn())) 
		    					dataOut = String.valueOf(reserva.getDataCheckIn().format(aFormatter));
		    				else            
		    				
		    				if (dataCkeckIn.isEqual(reserva.getDataCheckIn())) {
		    					
		    					if (dataCkeckOut.isAfter(reserva.getDataCheckOut()))
		    						dataIn = String.valueOf(reserva.getDataCheckOut().format(aFormatter));
		    					 else {
		    						dataIn = "Indisponivel";
		    						dataOut = "Indisponivel";}
		    					}
		    						
		    			    else
		    				
		    				if (dataCkeckIn.isAfter(reserva.getDataCheckIn())) {
		    					if (dataCkeckOut.isAfter(reserva.getDataCheckOut())) 
		    						dataIn = String.valueOf(reserva.getDataCheckOut().format(aFormatter));
		    					 else {
		    						dataIn = "Indisponivel";
		    						dataOut = "Indisponivel";}
		    				}
		    			    break;
		    		
		    			} // fim if
	    			
    			} // fim for itensReserva	
    		
    		} // fim for reserva
    	
    		listaDisponibilidade.add(new UnidadesHabitacionaisDisponivel(unidadeHabitacional.getNumero(),
					                            unidadeHabitacional.getNome(),
					                            unidadeHabitacional.getCategoria().getPrecoCategoria(),
					                            dataIn,
					                    		dataOut,
					                    		unidadeHabitacional));
    		
    		} // fim for unidade habitacional
    	
	    	// popular e ordenar Tableview
	    	//tableViewUnidadesDisponiveis.getItems().clear(); ????
	    	tableViewUnidadesDisponiveis.setItems(listaDisponibilidade); 
	    	listaDisponibilidade.sort(Comparator.comparing(UnidadesHabitacionaisDisponivel::getDiaria));
	    	
	    	selectedIndex = 0;
	        tableViewUnidadesDisponiveis.getSelectionModel().select(selectedIndex);
	        unidadesHabitacionaisDisponivel = tableViewUnidadesDisponiveis.getSelectionModel().getSelectedItem();
	        showDetailsUHDisponiveis(unidadesHabitacionaisDisponivel);
	        
    	}
    	
    private void manipuladorData() {
    	        dataCheck = LocalDate.of(datePickerCkeckin.getValue().getYear(), 
                datePickerCkeckin.getValue().getMonthValue(), 
                datePickerCkeckin.getValue().getDayOfMonth());
    			dataCheck = dataCheck.plusDays(spinnerNoite.getValue());
    			textFieldCheckOut.setText(dataCheck.getDayOfMonth() + "/" +
            	dataCheck.getMonthValue() + "/" +
            	dataCheck.getYear() + " | " + horaCheck);
    		
    			dataCkeckIn = LocalDateTime.of(datePickerCkeckin.getValue(), horaCheck);
    			dataCkeckOut = LocalDateTime.of(dataCheck, horaCheck);

    }
    
    @FXML public void onActionConfirmaAlocacao() throws ParseException {
     
    	// verifica primeiro a lotacao...
    	if (comboBoxAlocarAdultos.getSelectionModel().getSelectedItem() != null) {
		    verificarLotacaoAdulto(comboBoxAlocarAdultos.getSelectionModel().getSelectedItem());
		}
    	if (comboBoxAlocarCriancas.getSelectionModel().getSelectedItem() != null) {
		    verificarLotacaoCrianca(comboBoxAlocarCriancas.getSelectionModel().getSelectedItem());
		}
    	if (buttonConfirmarAlocacao.isDisable() == false) {
           if (listaItemReserva.size() == 0) { // Significa que é o primeiro item, logo, efetuar criacao do objeto Reserva tambem
            	idItem = 1;
            	reserva = new Reserva(Integer.valueOf(numeroReserva.getText()),
            			              LocalDateTime.of(datePickerDataOperacao.getValue(), horaCheck),
            			              dataCkeckIn,
            			              dataCkeckOut,
            			              1,
            			              comboBoxTipoPromocional.getValue(),
            			              textFieldFNRH.getText(),
            			              comboBoxHospede.getValue());
               
            } else idItem = Integer.valueOf(TableItemReservaLoad.getMaxIdItemReserva(listaItemReserva));
    
     
            listaItemReserva.add(new ItemReserva(idItem,
            		                      unidadesHabitacionaisDisponivel.getUnidadeHabitacional(),
            		                      unidadesHabitacionaisDisponivel.getNome(),
            		                      unidadesHabitacionaisDisponivel.getNumero(),
            		                      listaServicos,
            		                      comboBoxAlocarAdultos.getValue(),
            		                      comboBoxAlocarCriancas.getValue(),
            		                      String.valueOf(formatWithComma.format(formatWithPoint.parse(unidadesHabitacionaisDisponivel.getUnidadeHabitacional().getCategoria().getPrecoCategoria()).doubleValue() * 
                		                      		spinnerNoite.getValue())),
            		                      textFieldTotalServicos.getText()
            		                       ));

            reserva.setItensReserva(listaItemReserva);
            listaItemReserva.sort(Comparator.comparing(ItemReserva::getNome));
            
            lotacaoAdulto.add(comboBoxAlocarAdultos.getValue());
            lotacaoCrianca.add(comboBoxAlocarCriancas.getValue());
            
            textFieldFaltaAlocarAdultos.setText(String.valueOf(lotacaoAdulto.faltaAlocar()));
            textFieldFaltaAlocarCriancas.setText(String.valueOf(lotacaoCrianca.faltaAlocar()));
            
            resumeReserva();
    	}
	}
    
    private void resumeReserva() throws ParseException {
        
    	// Criando construtor
    	ResumoReserva resumeReserva = new ResumoReserva(listaItemReserva, 
                Integer.valueOf(textFieldNoites.getText()),
                formatWithPoint.parse(comboBoxTipoPromocional.getValue().getPorcentagemDesconto()).doubleValue(),
                Integer.valueOf(textFieldNumeroAdultos.getText()),
                formatWithPoint.parse(comboBoxHospede.getValue().getAgencia().getValorComissao()).doubleValue()
                );

    	textFieldUnidadesAlocadas.setText(resumeReserva.items()); 
        textFieldTotalGeralReserva.setText(resumeReserva.sumReservas());
        textFieldTotalGeralServicos.setText(resumeReserva.sumServicos());
        textFieldTotalGeralGeral.setText(resumeReserva.somaGeralGeral());
        textFieldDescontoPermanencia.setText(resumeReserva.descontoPermanencia());
        textFieldTipoPromocional.setText(resumeReserva.descontoTipoPromocional());
        textFieldDescontoGrupo.setText(resumeReserva.descontoNumeroAdultos());
        textFieldComissaoAgencia.setText(resumeReserva.descontoAgenciaViagem());
        textFieldTotalDesconto.setText(resumeReserva.totalDescontos());
        textFieldTotalLiquido.setText(resumeReserva.totalLiquido());
        if (textFieldEntradaEspecie.getText().isEmpty()) {
        	textFieldSaldoDevedor.setText(textFieldTotalLiquido.getText());
        }
        try {
        	
        } catch (Exception e) {}
    }
    

    private void verificarLotacaoAdulto(Integer adultos) {
    	lotacaoAdulto.setAdulto(adultos);	
	   	 if (!lotacaoAdulto.regra()) {
			Utilitarios.showAlert("Trump Hotels - RESERVAS", 
		                          "PROCESSO DE ALOCAÇÃO", 
                                 "Operação inválida: Super-lotação de adultos!");
			buttonConfirmarAlocacao.setDisable(true);
		 } else {
               if (lotacaoCrianca.isStatus()) {			 
			       buttonConfirmarAlocacao.setDisable(false);
               }
		   }
    }
    private void verificarLotacaoCrianca(Integer criancas) {
    	lotacaoCrianca.setCrianca(criancas);	
	   	 if (!lotacaoCrianca.regra()) {
			Utilitarios.showAlert("Trump Hotels - RESERVAS", 
		                          "PROCESSO DE ALOCAÇÃO", 
                                 "Operação inválida: Super-lotação de crianças!");
			buttonConfirmarAlocacao.setDisable(true);
		 } else {
               if (lotacaoAdulto.isStatus()) {			 
			       buttonConfirmarAlocacao.setDisable(false);
               }
		   }
    }
	// ------------------------------------------------FORMATAÇÕES E VALIDAÇÕES --------------------------------------------------------------------

	private void validarCampos() {
      	  
		textFieldNumeroAdultos.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, Utilitarios.integerFilter));
		textFieldNumeroCriancas.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, Utilitarios.integerFilter));
		textFieldEntradaEspecie.setTextFormatter(new TextFormatter<>(Utilitarios.filterMonetary));		
	}


    // ------------------------------------------------ FIM FORMATAÇÕES E VALIDAÇÕES --------------------------------------------------------------------

	public ObservableList<TipoPromocional> getListaTipoPromocional() {
		return listaTipoPromocional;
	}


	public void setListaTipoPromocional(ObservableList<TipoPromocional> listaTipoPromocional) {
		this.listaTipoPromocional = listaTipoPromocional;
	}


	
}