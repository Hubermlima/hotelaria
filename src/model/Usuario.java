package model;

import javafx.beans.property.SimpleStringProperty; 

public class Usuario {
	
	   private SimpleStringProperty login;
	   private SimpleStringProperty senha;
	   
	   public Usuario() {
	    	
	    }
	   public Usuario(String login, String senha) {
			    	
			    	this.login = new SimpleStringProperty(login);
			    	this.senha = new SimpleStringProperty(senha);
			    	
			}
	public final SimpleStringProperty loginProperty() {
		return this.login;
	}
	
	public final String getLogin() {
		return this.loginProperty().get();
	}
	
	public final void setLogin(final String login) {
		this.loginProperty().set(login);
	}
	
	public final SimpleStringProperty senhaProperty() {
		return this.senha;
	}
	
	public final String getSenha() {
		return this.senhaProperty().get();
	}
	
	public final void setSenha(final String senha) {
		this.senhaProperty().set(senha);
	}
	
		
		
}
