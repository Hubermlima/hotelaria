package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty; 

public class Servico {
	   private SimpleIntegerProperty id;
	   private SimpleStringProperty nome;
	   private SimpleStringProperty descricao;
	   private SimpleStringProperty valorTarifa;
	   private SimpleBooleanProperty myCheck;
	   
	   public Servico() {
	    	
	    }
	   public Servico(Integer id, String nome, String descricao, String valorTarifa) {
			    	
			    	this.id = new SimpleIntegerProperty(id);  	
			    	this.nome = new SimpleStringProperty(nome);
			    	this.descricao = new SimpleStringProperty(descricao);
			    	this.valorTarifa = new SimpleStringProperty(valorTarifa);
			    	
			}
	   
	   
	public Servico(Integer id, String nome, String descricao, String valorTarifa, Boolean myCheck) {
		this.id = new SimpleIntegerProperty(id);  	
    	this.nome = new SimpleStringProperty(nome);
    	this.descricao = new SimpleStringProperty(descricao);
    	this.valorTarifa = new SimpleStringProperty(valorTarifa);
    	this.myCheck = new SimpleBooleanProperty(myCheck);
    	
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
	
	public final SimpleStringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final SimpleStringProperty descricaoProperty() {
		return this.descricao;
	}
	
	public final String getDescricao() {
		return this.descricaoProperty().get();
	}
	
	public final void setDescricao(final String descricao) {
		this.descricaoProperty().set(descricao);
	}
	
	public final SimpleStringProperty valorTarifaProperty() {
		return this.valorTarifa;
	}
	
	public final String getValorTarifa() {
		return this.valorTarifaProperty().get();
	}
	
	public final void setValorTarifa(final String valorTarifa) {
		this.valorTarifaProperty().set(valorTarifa);
	}
	public final SimpleBooleanProperty myCheckProperty() {
		return this.myCheck;
	}
	
	public final boolean isMyCheck() {
		return this.myCheckProperty().get();
	}
	
	public final void setMyCheck(final boolean myCheck) {
		this.myCheckProperty().set(myCheck);
	}
	
	
}
