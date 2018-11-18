package model;


import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty; 

public class Hospede {
	
	   private SimpleIntegerProperty id;
	   
	   private SimpleIntegerProperty titulo;  // ajeitar para combobox na view
	   private SimpleStringProperty nome;
	   private SimpleStringProperty sobrenome;
	   private LocalDate dataAniversario;
	   private SimpleIntegerProperty genero;
	   
	   private SimpleIntegerProperty tipoDocumento;
	   private SimpleStringProperty numeroDocumento;
	   private SimpleStringProperty orgaoExpedidor;
	   
	   private SimpleStringProperty nacionalidade;
	   private SimpleIntegerProperty logradouro; // AJEITAR NA VIEW
	   private SimpleStringProperty nomeRua;
	   private SimpleStringProperty numeroCasa;
	   private SimpleStringProperty complemento;
	   private SimpleStringProperty bairro;
	   private SimpleStringProperty codigoPostal;
	   private SimpleStringProperty cidade;
	   private SimpleStringProperty estado;
	  
	   private SimpleStringProperty profissao;
	   private SimpleIntegerProperty motivoViagem;
	   private LocalDate dataUltimaVisita;
	  
	   private SimpleStringProperty empresa;
	   private SimpleStringProperty emailEmpresa;
	   private SimpleStringProperty foneEmpresa;
	   
	   private SimpleStringProperty primeiroEmail;
	   private SimpleStringProperty segundoEmail;
	   private SimpleStringProperty primeiroFone;
	   private SimpleStringProperty segundoFone;
	   
	   private Pais pais;
       private AgenciaViagem agencia;
       
	   public Hospede() {
	    	
	    }
	   public Hospede(Integer id, Integer titulo, String sobrenome, String nome) {
		    this.id = new SimpleIntegerProperty(id);
		    this.titulo = new SimpleIntegerProperty(titulo);
	    	this.sobrenome = new SimpleStringProperty(sobrenome);
	    	this.nome = new SimpleStringProperty(nome);
	
	   }
	   public Hospede(Integer id, 
			          Integer titulo, 
			          String nome, 
			          String sobrenome, 
			          LocalDate dataAniversario, 
			          Integer genero, 
			          Integer tipoDocumento, 
			          String numeroDocumento, 
			          String orgaoExpedidor,
			          String nacionalidade, 
			          Integer logradouro, 
			          String nomeRua, 
			          String numeroCasa,
			          String complemento, 
			          String bairro, 
			          String codigoPostal,
			          String cidade,
			          String estado, 
			          String profissao, 
			          Integer motivoViagem, 
			          LocalDate dataUltimaVisita, 
			          String empresa, 
			          String emailEmpresa, 
			          String foneEmpresa, 
			          String primeiroEmail, 
			          String segundoEmail, 
			          String primeiroFone,
			          String segundoFone) {
			    	
			    	this.id = new SimpleIntegerProperty(id);  	
			    	this.titulo = new SimpleIntegerProperty(titulo);
			    	this.nome = new SimpleStringProperty(nome);
			    	this.sobrenome = new SimpleStringProperty(sobrenome);
			    	this.dataAniversario = dataAniversario;
			    	this.genero = new SimpleIntegerProperty(genero);
			    	this.tipoDocumento = new SimpleIntegerProperty(tipoDocumento);
			    	this.numeroDocumento = new SimpleStringProperty(numeroDocumento);
			    	this.orgaoExpedidor = new SimpleStringProperty(orgaoExpedidor);
			    	this.nacionalidade = new SimpleStringProperty(nacionalidade);
			    	this.logradouro = new SimpleIntegerProperty(logradouro);
			    	this.nomeRua = new SimpleStringProperty(nomeRua);
			    	this.numeroCasa = new SimpleStringProperty(numeroCasa);
			    	this.complemento = new SimpleStringProperty(complemento);
			    	this.bairro = new SimpleStringProperty(bairro);
			    	this.codigoPostal = new SimpleStringProperty(codigoPostal);
			    	this.cidade = new SimpleStringProperty(cidade);
			    	this.estado = new SimpleStringProperty(estado);
			    	//this.pais = new SimpleIntegerProperty(pais);  	
			    	this.profissao = new SimpleStringProperty(profissao);
			    	this.motivoViagem = new SimpleIntegerProperty(motivoViagem);
			    	this.dataUltimaVisita = dataUltimaVisita;
			    	this.empresa = new SimpleStringProperty(empresa);
			    	this.emailEmpresa = new SimpleStringProperty(emailEmpresa);
			    	this.foneEmpresa = new SimpleStringProperty(foneEmpresa);
			    	this.primeiroEmail = new SimpleStringProperty(primeiroEmail);
			    	this.segundoEmail = new SimpleStringProperty(segundoEmail);
			    	this.primeiroFone = new SimpleStringProperty(primeiroFone);
			    	this.segundoFone = new SimpleStringProperty(segundoFone);
			}
	   
	   
	@Override
	public String toString() {
		return  TituloTratamento.getNome(getTitulo()) + " " + getSobrenome() + ", " + getNome();
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
	
    public final SimpleIntegerProperty generoProperty() {
		return this.genero;
	}
	
	public final int getGenero() {
		return this.generoProperty().get();
	}
	
	public final void setGenero(final int genero) {
		this.generoProperty().set(genero);
	}
	
	public final SimpleIntegerProperty tipoDocumentoProperty() {
		return this.tipoDocumento;
	}
	
	public final int getTipoDocumento() {
		return this.tipoDocumentoProperty().get();
	}
	
	public final void setTipoDocumento(final int tipoDocumento) {
		this.tipoDocumentoProperty().set(tipoDocumento);
	}
	
	public final SimpleStringProperty numeroDocumentoProperty() {
		return this.numeroDocumento;
	}
	
	public final String getNumeroDocumento() {
		return this.numeroDocumentoProperty().get();
	}
	
	public final void setNumeroDocumento(final String numeroDocumento) {
		this.numeroDocumentoProperty().set(numeroDocumento);
	}
	
	public final SimpleStringProperty orgaoExpedidorProperty() {
		return this.orgaoExpedidor;
	}
	
	public final String getOrgaoExpedidor() {
		return this.orgaoExpedidorProperty().get();
	}
	
	public final void setOrgaoExpedidor(final String orgaoExpedidor) {
		this.orgaoExpedidorProperty().set(orgaoExpedidor);
	}
	
	public final SimpleStringProperty nacionalidadeProperty() {
		return this.nacionalidade;
	}
	
	public final String getNacionalidade() {
		return this.nacionalidadeProperty().get();
	}
	
	public final void setNacionalidade(final String nacionalidade) {
		this.nacionalidadeProperty().set(nacionalidade);
	}
	
	
	public final SimpleStringProperty nomeRuaProperty() {
		return this.nomeRua;
	}
	
	public final String getNomeRua() {
		return this.nomeRuaProperty().get();
	}
	
	public final void setNomeRua(final String nomeRua) {
		this.nomeRuaProperty().set(nomeRua);
	}
	
	public final SimpleStringProperty numeroCasaProperty() {
		return this.numeroCasa;
	}
	
	public final String getNumeroCasa() {
		return this.numeroCasaProperty().get();
	}
	
	public final void setNumeroCasa(final String numeroCasa) {
		this.numeroCasaProperty().set(numeroCasa);
	}
	
	public final SimpleStringProperty complementoProperty() {
		return this.complemento;
	}
	
	public final String getComplemento() {
		return this.complementoProperty().get();
	}
	
	public final void setComplemento(final String complemento) {
		this.complementoProperty().set(complemento);
	}
	
	public final SimpleStringProperty cidadeProperty() {
		return this.cidade;
	}
	
	public final String getCidade() {
		return this.cidadeProperty().get();
	}
	
	public final void setCidade(final String cidade) {
		this.cidadeProperty().set(cidade);
	}
	
	public final SimpleStringProperty estadoProperty() {
		return this.estado;
	}
	
	public final String getEstado() {
		return this.estadoProperty().get();
	}
	
	public final void setEstado(final String estado) {
		this.estadoProperty().set(estado);
	}
	
	
	public final SimpleStringProperty profissaoProperty() {
		return this.profissao;
	}
	
	public final String getProfissao() {
		return this.profissaoProperty().get();
	}
	
	public final void setProfissao(final String profissao) {
		this.profissaoProperty().set(profissao);
	}
	
	public final SimpleIntegerProperty motivoViagemProperty() {
		return this.motivoViagem;
	}
	
	public final int getMotivoViagem() {
		return this.motivoViagemProperty().get();
	}
	
	public final void setMotivoViagem(final int motivoViagem) {
		this.motivoViagemProperty().set(motivoViagem);
	}
	
	public final SimpleStringProperty empresaProperty() {
		return this.empresa;
	}
	
	public final String getEmpresa() {
		return this.empresaProperty().get();
	}
	
	public final void setEmpresa(final String empresa) {
		this.empresaProperty().set(empresa);
	}
	
	public final SimpleStringProperty emailEmpresaProperty() {
		return this.emailEmpresa;
	}
	
	public final String getEmailEmpresa() {
		return this.emailEmpresaProperty().get();
	}
	
	public final void setEmailEmpresa(final String emailEmpresa) {
		this.emailEmpresaProperty().set(emailEmpresa);
	}
	
	public final SimpleStringProperty foneEmpresaProperty() {
		return this.foneEmpresa;
	}
	
	public final String getFoneEmpresa() {
		return this.foneEmpresaProperty().get();
	}
	
	public final void setFoneEmpresa(final String foneEmpresa) {
		this.foneEmpresaProperty().set(foneEmpresa);
	}
	
	public final SimpleStringProperty primeiroEmailProperty() {
		return this.primeiroEmail;
	}
	
	public final String getPrimeiroEmail() {
		return this.primeiroEmailProperty().get();
	}
	
	public final void setPrimeiroEmail(final String primeiroEmail) {
		this.primeiroEmailProperty().set(primeiroEmail);
	}
	
	public final SimpleStringProperty segundoEmailProperty() {
		return this.segundoEmail;
	}
	
	public final String getSegundoEmail() {
		return this.segundoEmailProperty().get();
	}
	
	public final void setSegundoEmail(final String segundoEmail) {
		this.segundoEmailProperty().set(segundoEmail);
	}
	
	public final SimpleStringProperty primeiroFoneProperty() {
		return this.primeiroFone;
	}
	
	public final String getPrimeiroFone() {
		return this.primeiroFoneProperty().get();
	}
	
	public final void setPrimeiroFone(final String primeiroFone) {
		this.primeiroFoneProperty().set(primeiroFone);
	}
	
	public final SimpleStringProperty segundoFoneProperty() {
		return this.segundoFone;
	}
	
	public final String getSegundoFone() {
		return this.segundoFoneProperty().get();
	}
	
	public final void setSegundoFone(final String segundoFone) {
		this.segundoFoneProperty().set(segundoFone);
	}
	
	public final SimpleIntegerProperty tituloProperty() {
		return this.titulo;
	}
	
	public final int getTitulo() {
		return this.tituloProperty().get();
	}
	
	public final void setTitulo(final int titulo) {
		this.tituloProperty().set(titulo);
	}
	
	public final SimpleIntegerProperty logradouroProperty() {
		return this.logradouro;
	}
	
	public final int getLogradouro() {
		return this.logradouroProperty().get();
	}
	
	public final void setLogradouro(final int logradouro) {
		this.logradouroProperty().set(logradouro);
	}
	public final SimpleStringProperty bairroProperty() {
		return this.bairro;
	}
	
	public final String getBairro() {
		return this.bairroProperty().get();
	}
	
	public final void setBairro(final String bairro) {
		this.bairroProperty().set(bairro);
	}
	public LocalDate getDataAniversario() {
		return dataAniversario;
	}
	public void setDataAniversario(LocalDate dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	public final SimpleStringProperty codigoPostalProperty() {
		return this.codigoPostal;
	}
	
	public final String getCodigoPostal() {
		return this.codigoPostalProperty().get();
	}
	
	public final void setCodigoPostal(final String codigoPostal) {
		this.codigoPostalProperty().set(codigoPostal);
	}
	public LocalDate getDataUltimaVisita() {
		return dataUltimaVisita;
	}
	public void setDataUltimaVisita(LocalDate dataUltimaVisita) {
		this.dataUltimaVisita = dataUltimaVisita;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public final SimpleStringProperty sobrenomeProperty() {
		return this.sobrenome;
	}
	
	public final String getSobrenome() {
		return this.sobrenomeProperty().get();
	}
	
	public final void setSobrenome(final String sobrenome) {
		this.sobrenomeProperty().set(sobrenome);
	}
	public AgenciaViagem getAgencia() {
		return agencia;
	}
	public void setAgencia(AgenciaViagem agencia) {
		this.agencia = agencia;
	}
	
    	
	
	

}
