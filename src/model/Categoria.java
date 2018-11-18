package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty; 

public class Categoria {
	
   private SimpleIntegerProperty id;
   private SimpleStringProperty nome;
   private SimpleStringProperty descricao;
   private SimpleIntegerProperty nAdultos;
   private SimpleIntegerProperty nCriancas;
   private SimpleStringProperty precoCategoria;
   
    public Categoria() {
    	
    }
    public Categoria(Integer id, String nome, String descricao, Integer nAdultos, Integer nCriancas, 
      String precoCategoria) {
    	
    	this.id = new SimpleIntegerProperty(id);  	
    	this.nome = new SimpleStringProperty(nome);
    	this.descricao = new SimpleStringProperty(descricao);
    	this.nAdultos = new SimpleIntegerProperty(nAdultos);
    	this.nCriancas = new SimpleIntegerProperty(nCriancas);
    	this.precoCategoria = new SimpleStringProperty(precoCategoria);
   
}
    public Categoria(Integer id, String nome, String precoCategoria, Integer nAdultos, Integer nCriancas) {
    	    	
    	    	this.id = new SimpleIntegerProperty(id);  	
    	    	this.nome = new SimpleStringProperty(nome);
    	    	this.precoCategoria = new SimpleStringProperty(precoCategoria);
    	    	this.nAdultos = new SimpleIntegerProperty(nAdultos);
    	    	this.nCriancas = new SimpleIntegerProperty(nCriancas);
    	   
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
	public final SimpleStringProperty precoCategoriaProperty() {
		return this.precoCategoria;
	}
	
	public final String getPrecoCategoria() {
		return this.precoCategoriaProperty().get();
	}
	
	public final void setPrecoCategoria(final String precoCategoria) {
		this.precoCategoriaProperty().set(precoCategoria);
	}
	@Override
	public String toString() {
		return  String.valueOf(getNome());
	}
	

    
}
