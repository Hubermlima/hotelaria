package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty; 

public class Pais {
	   private SimpleIntegerProperty id;
	   private SimpleStringProperty nome; 
	   
	   public Pais() {
	    	
	    }
	   public Pais(Integer id, String nome) {
			    	
			    	this.id = new SimpleIntegerProperty(id);  	
			    	this.nome = new SimpleStringProperty(nome);
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
	@Override
	public String toString() {
		return  String.valueOf(getNome());
	}

	
	   
		
}
