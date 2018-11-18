package model;

import javafx.beans.property.SimpleStringProperty;

public class UnidadesHabitacionaisDisponivel {
	private SimpleStringProperty numero;
	private SimpleStringProperty nome;
	private SimpleStringProperty diaria;
	private SimpleStringProperty checkIn;
	private SimpleStringProperty checkOut;
    private UnidadeHabitacional unidadeHabitacional;
    
	public UnidadesHabitacionaisDisponivel(String numero, 
			                               String nome, 
			                               String diaria, 
			                               String checkIn, 
			                               String checkOut,
			                               UnidadeHabitacional unidadeHabitacional) {
		
		this.numero = new SimpleStringProperty(numero);
		this.nome = new SimpleStringProperty(nome);
		this.diaria = new SimpleStringProperty(diaria);
		this.checkIn = new SimpleStringProperty(checkIn);
		this.checkOut = new SimpleStringProperty(checkOut);
		this.unidadeHabitacional = unidadeHabitacional;
	}

	public UnidadeHabitacional getUnidadeHabitacional() {
		return unidadeHabitacional;
	}

	public void setUnidadeHabitacional(UnidadeHabitacional unidadeHabitacional) {
		this.unidadeHabitacional = unidadeHabitacional;
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
	

	public final SimpleStringProperty nomeProperty() {
		return this.nome;
	}
	

	public final String getNome() {
		return this.nomeProperty().get();
	}
	

	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	

	public final SimpleStringProperty diariaProperty() {
		return this.diaria;
	}
	

	public final String getDiaria() {
		return this.diariaProperty().get();
	}
	

	public final void setDiaria(final String diaria) {
		this.diariaProperty().set(diaria);
	}
	

	public final SimpleStringProperty checkInProperty() {
		return this.checkIn;
	}
	

	public final String getCheckIn() {
		return this.checkInProperty().get();
	}
	

	public final void setCheckIn(final String checkIn) {
		this.checkInProperty().set(checkIn);
	}
	

	public final SimpleStringProperty checkOutProperty() {
		return this.checkOut;
	}
	

	public final String getCheckOut() {
		return this.checkOutProperty().get();
	}
	

	public final void setCheckOut(final String checkOut) {
		this.checkOutProperty().set(checkOut);
	}
	
		
      
}
