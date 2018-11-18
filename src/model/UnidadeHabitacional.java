package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty; 

public class UnidadeHabitacional {
	
   private SimpleIntegerProperty id;
   private SimpleStringProperty nome;
   private SimpleIntegerProperty nQuartos;
   private SimpleStringProperty numero;
   private SimpleIntegerProperty andar;
   private SimpleStringProperty descricao;
   private Torre torre;
   private Categoria categoria;
   
   public UnidadeHabitacional() {
    	
    }
    public UnidadeHabitacional(Integer id, String nome, Integer nQuartos, String numero, Integer andar, Torre torre, String descricao, 
    		                   Categoria categoria) {
    	
    	this.id = new SimpleIntegerProperty(id);
    	this.nome = new SimpleStringProperty(nome);
    	this.nQuartos = new SimpleIntegerProperty(nQuartos);
    	this.numero = new SimpleStringProperty(numero);
    	this.andar = new SimpleIntegerProperty(andar);
    	this.torre = torre;
    	this.descricao = new SimpleStringProperty(descricao);
    	this.categoria = categoria;
     
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
	
	public final SimpleIntegerProperty nQuartosProperty() {
		return this.nQuartos;
	}
	
	public final int getNQuartos() {
		return this.nQuartosProperty().get();
	}
	
	public final void setNQuartos(final int nQuartos) {
		this.nQuartosProperty().set(nQuartos);
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
	
	public final SimpleIntegerProperty andarProperty() {
		return this.andar;
	}
	
	public final int getAndar() {
		return this.andarProperty().get();
	}
	
	public final void setAndar(final int andar) {
		this.andarProperty().set(andar);
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
	public Torre getTorre() {
		return torre;
	}
	public void setTorre(Torre torre) {
		this.torre = torre;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
    
     	
	
	
}
