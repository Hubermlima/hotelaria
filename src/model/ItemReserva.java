package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList; 

public class ItemReserva {
	
   private SimpleIntegerProperty id;
   private UnidadeHabitacional unidadeHabitacional;
   private SimpleStringProperty nome;
   private SimpleStringProperty numero;
   private ObservableList<Servico> servico;
   private SimpleIntegerProperty nAdultos;
   private SimpleIntegerProperty nCriancas;
   private SimpleStringProperty totalDiaria;
   private SimpleStringProperty totalServico;
   
   public ItemReserva() {}
   
   public ItemReserva(Integer id, 
		              UnidadeHabitacional unidadeHabitacional, 
		              String nome,
		              String numero, 
		              ObservableList<Servico> servico, 
		              Integer nAdultos,
		              Integer nCriancas, 
		              String totalDiaria, 
		              String totalServico) {

	this.id = new SimpleIntegerProperty(id);
	this.unidadeHabitacional = unidadeHabitacional;
	this.nome = new SimpleStringProperty(nome);
	this.numero = new SimpleStringProperty(numero);
	this.servico = servico;
	this.nAdultos = new SimpleIntegerProperty(nAdultos);
	this.nCriancas = new SimpleIntegerProperty(nCriancas);
	this.totalDiaria = new SimpleStringProperty(totalDiaria);
	this.totalServico = new SimpleStringProperty(totalServico);
}


public final SimpleIntegerProperty idProperty() {
	return this.id;
}


public final int getId() {
	return this.idProperty().get();
}


public final void setId(final int id) {
	this.idProperty().set(id);
}


public final SimpleIntegerProperty nAdultosProperty() {
	return this.nAdultos;
}


public final int getNAdultos() {
	return this.nAdultosProperty().get();
}


public final void setNAdultos(final int nAdultos) {
	this.nAdultosProperty().set(nAdultos);
}


public final SimpleIntegerProperty nCriancasProperty() {
	return this.nCriancas;
}


public final int getNCriancas() {
	return this.nCriancasProperty().get();
}


public final void setNCriancas(final int nCriancas) {
	this.nCriancasProperty().set(nCriancas);
}


public final SimpleStringProperty totalDiariaProperty() {
	return this.totalDiaria;
}


public final String getTotalDiaria() {
	return this.totalDiariaProperty().get();
}


public final void setTotalDiaria(final String totalDiaria) {
	this.totalDiariaProperty().set(totalDiaria);
}


public final SimpleStringProperty totalServicoProperty() {
	return this.totalServico;
}


public final String getTotalServico() {
	return this.totalServicoProperty().get();
}


public final void setTotalServico(final String totalServico) {
	this.totalServicoProperty().set(totalServico);
}

public UnidadeHabitacional getUnidadeHabitacional() {
	return unidadeHabitacional;
}

public void setUnidadeHabitacional(UnidadeHabitacional unidadeHabitacional) {
	this.unidadeHabitacional = unidadeHabitacional;
}

public ObservableList<Servico> getServico() {
	return servico;
}

public void setServico(ObservableList<Servico> servico) {
	this.servico = servico;
}

public final SimpleStringProperty nomeProperty() {
	return this.nome;
}


public final String getNome() {
	return this.nomeProperty().get();
}


public final void setNome(final String nome) {
	this.nomeProperty().set(nome);
}


public final SimpleStringProperty numeroProperty() {
	return this.numero;
}


public final String getNumero() {
	return this.numeroProperty().get();
}


public final void setNumero(final String numero) {
	this.numeroProperty().set(numero);
}


   
    
}
