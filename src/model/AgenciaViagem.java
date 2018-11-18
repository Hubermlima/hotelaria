package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty; 

public class AgenciaViagem {
	
	   private SimpleIntegerProperty id;
	   private SimpleStringProperty nome;
	   private SimpleStringProperty descricao;
	   private SimpleStringProperty valorComissao;
	   
	   public AgenciaViagem() {
	    	
	    }
	   
	   public AgenciaViagem(Integer id, String nome, String valorComissao) {
	    	
	    	this.id = new SimpleIntegerProperty(id);  	
	    	this.nome = new SimpleStringProperty(nome);
	    	this.valorComissao = new SimpleStringProperty(valorComissao);
	    	
	}
	   public AgenciaViagem(Integer id, String nome, String descricao, String valorComissao) {
			    	
			    	this.id = new SimpleIntegerProperty(id);  	
			    	this.nome = new SimpleStringProperty(nome);
			    	this.descricao = new SimpleStringProperty(descricao);
			    	this.valorComissao = new SimpleStringProperty(valorComissao);
			    	
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
	
	public final SimpleStringProperty valorComissaoProperty() {
		return this.valorComissao;
	}
	
	public final String getValorComissao() {
		return this.valorComissaoProperty().get();
	}
	
	public final void setValorComissao(final String valorComissao) {
		this.valorComissaoProperty().set(valorComissao);
	}
	
	@Override
	public String toString() {
		return  String.valueOf(getNome());
	}
		
}
