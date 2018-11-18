package model;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList; 

public class Reserva {
	
   private SimpleIntegerProperty id;
   private LocalDateTime data;
   private LocalDateTime dataCheckIn;
   private LocalDateTime dataCheckOut;
   private SimpleIntegerProperty status;
   TipoPromocional tipoPromocional;
   private SimpleStringProperty numeroFNRH;
   private Hospede hospede;
   private ObservableList<ItemReserva> itensReserva;

public Reserva(Integer id, LocalDateTime data, LocalDateTime dataCheckIn, LocalDateTime dataCheckOut,
		Integer status, TipoPromocional tipoPromocional, String numeroFNRH, Hospede hospede) {
	this.id = new SimpleIntegerProperty(id);
	this.data = data;
	this.dataCheckIn = dataCheckIn;
	this.dataCheckOut = dataCheckOut;
	this.status = new SimpleIntegerProperty(status);
	this.tipoPromocional = tipoPromocional;
	this.numeroFNRH = new SimpleStringProperty(numeroFNRH);
	this.hospede = hospede;
}


public LocalDateTime getData() {
	return data;
}


public void setData(LocalDateTime data) {
	this.data = data;
}


public LocalDateTime getDataCheckIn() {
	return dataCheckIn;
}


public void setDataCheckIn(LocalDateTime dataCheckIn) {
	this.dataCheckIn = dataCheckIn;
}


public LocalDateTime getDataCheckOut() {
	return dataCheckOut;
}


public void setDataCheckOut(LocalDateTime dataCheckOut) {
	this.dataCheckOut = dataCheckOut;
}


public TipoPromocional getTipoPromocional() {
	return tipoPromocional;
}


public void setTipoPromocional(TipoPromocional tipoPromocional) {
	this.tipoPromocional = tipoPromocional;
}


public Hospede getHospede() {
	return hospede;
}


public void setHospede(Hospede hospede) {
	this.hospede = hospede;
}


public ObservableList<ItemReserva> getItensReserva() {
	return itensReserva;
}


public void setItensReserva(ObservableList<ItemReserva> itensReserva) {
	this.itensReserva = itensReserva;
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


public final SimpleIntegerProperty statusProperty() {
	return this.status;
}


public final int getStatus() {
	return this.statusProperty().get();
}


public final void setStatus(final int status) {
	this.statusProperty().set(status);
}


public final SimpleStringProperty numeroFNRHProperty() {
	return this.numeroFNRH;
}


public final String getNumeroFNRH() {
	return this.numeroFNRHProperty().get();
}


public final void setNumeroFNRH(final String numeroFNRH) {
	this.numeroFNRHProperty().set(numeroFNRH);
}


   
}