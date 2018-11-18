package model;

import java.util.Optional;

import javafx.collections.ObservableList;

public class QuartoDuplicado implements RegraNegocio {

	private ObservableList<ItemReserva> listaReservados;
	private String numero;
	
	public QuartoDuplicado(ObservableList<ItemReserva> listaReservados, String numero) {
		this.listaReservados = listaReservados;
		this.numero = numero;
	}

	@Override
	public boolean regra() {
		Optional<ItemReserva> result = this.listaReservados.stream()
				.filter(o -> o.getNumero().equals(this.numero))
				.findAny();

        if (!result.isPresent()) { // Quarto não se repete, logo, a regra foi satisfeita
	        return true;
        }
	    return false; // Quarto se repete, logo, a regra foi quebrada
		
	}

	public ObservableList<ItemReserva> getListaReservados() {
		return listaReservados;
	}

	public void setListaReservados(ObservableList<ItemReserva> listaReservados) {
		this.listaReservados = listaReservados;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
    
    
}
