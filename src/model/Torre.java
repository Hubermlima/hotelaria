package model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList; 

public class Torre {

	   private SimpleIntegerProperty id;
	   private SimpleStringProperty nome;
	   private SimpleIntegerProperty nAndar;
	   private SimpleIntegerProperty local;
	   private SimpleIntegerProperty nApartamentosPorAndar;
	   private ObservableList<UnidadeHabitacional> lista = FXCollections.observableArrayList();	
	  
	   public Torre() {
	    	
	    }
	   
	   public Torre(Integer id, String nome, Integer nAndar, Integer local, Integer nApartamentosPorAndar,
			     ObservableList<UnidadeHabitacional> lista) {
		
		this.id = new SimpleIntegerProperty(id);  	
    	this.nome = new SimpleStringProperty(nome);
    	this.nAndar = new SimpleIntegerProperty(nAndar);
    	this.local = new SimpleIntegerProperty(local);
    	this.nApartamentosPorAndar = new SimpleIntegerProperty(nApartamentosPorAndar);
    	this.lista = lista; 
    
	   }
    	public Torre(Integer id, String nome, Integer nAndar, Integer local, Integer nApartamentosPorAndar) {
	
			this.id = new SimpleIntegerProperty(id);  	
			this.nome = new SimpleStringProperty(nome);
			this.nAndar = new SimpleIntegerProperty(nAndar);
			this.local = new SimpleIntegerProperty(local);
			this.nApartamentosPorAndar = new SimpleIntegerProperty(nApartamentosPorAndar);
        }
	public Torre(Integer id, String nome) {
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
	
	public final SimpleIntegerProperty nAndarProperty() {
		return this.nAndar;
	}
	
	public final int getNAndar() {
		return this.nAndarProperty().get();
	}
	
	public final void setNAndar(final int nAndar) {
		this.nAndarProperty().set(nAndar);
	}
	
	public final SimpleIntegerProperty localProperty() {
		return this.local;
	}
	
	public final int getLocal() {
		return this.localProperty().get();
	}
	
	public final void setLocal(final int local) {
		this.localProperty().set(local);
	}
	
	public final SimpleIntegerProperty nApartamentosPorAndarProperty() {
		return this.nApartamentosPorAndar;
	}
	
	public final int getNApartamentosPorAndar() {
		return this.nApartamentosPorAndarProperty().get();
	}
	
	public final void setNApartamentosPorAndar(final int nApartamentosPorAndar) {
		this.nApartamentosPorAndarProperty().set(nApartamentosPorAndar);
	}
	public ObservableList<UnidadeHabitacional> getLista() {
		return lista;
	}
	public void setLista(ObservableList<UnidadeHabitacional> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return String.valueOf(getNome()) ;
	}
	   
	
}
