package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty; 

public class TipoPromocional {
	
	   private SimpleIntegerProperty id;
	   private SimpleStringProperty nome;
	   private SimpleStringProperty descricao;
	   private SimpleStringProperty porcentagemDesconto;
	   
	   public TipoPromocional() {
	    	
	    }
	   public TipoPromocional(Integer id, String nome, String porcentagemDesconto) {
	    	
	    	this.id = new SimpleIntegerProperty(id);  	
	    	this.nome = new SimpleStringProperty(nome);
	    	this.porcentagemDesconto = new SimpleStringProperty(porcentagemDesconto);
	    	
	}
	   public TipoPromocional(Integer id, String nome, String descricao, String porcentagemDesconto) {
			    	
			    	this.id = new SimpleIntegerProperty(id);  	
			    	this.nome = new SimpleStringProperty(nome);
			    	this.descricao = new SimpleStringProperty(descricao);
			    	this.porcentagemDesconto = new SimpleStringProperty(porcentagemDesconto);
			    	
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
	
	public final SimpleStringProperty porcentagemDescontoProperty() {
		return this.porcentagemDesconto;
	}
	
	public final String getPorcentagemDesconto() {
		return this.porcentagemDescontoProperty().get();
	}
	
	public final void setPorcentagemDesconto(final String porcentagemDesconto) {
		this.porcentagemDescontoProperty().set(porcentagemDesconto);
	}
	@Override
	public String toString() {
		return getNome();
	}
	
		
		
}
