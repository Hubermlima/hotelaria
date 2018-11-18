package model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javafx.collections.ObservableList;
import util.Utilitarios;

public class ResumoReserva {
         ObservableList<ItemReserva> listaReserva;
         private Integer noites;
         private Integer nAdultos;
         private Double sumReservas;
         private Double sumServicos;
         private Double sumGeral;
         private Double descontoPermanencia;
         private Double porcentagemTipoDesconto;
         private Double porcentagemAgenciaViagem;
         private Double valorDescontoTipoPromocional;
         private Double valorDescontoAgenciaViagem;
         private Double valorDescontoAdultos;
         private Double valorTotalDescontos;
         private Double valorTotalLiquido;

         NumberFormat formatWithComma = Utilitarios.meuFormato(); //numerico -> String com virgula (2.5 -> 2,5)
         NumberFormat formatWithPoint = NumberFormat.getInstance(Locale.FRANCE);  // String -> numerico com ponto (2,5 -> 2.5)
         
         public ResumoReserva(ObservableList<ItemReserva> listaReserva, 
        		              Integer noites,
        		              Double porcentagemTipoDesconto,
        		              Integer nAdultos,
        		              Double porcentagemAgenciaViagem) {
			this.listaReserva = listaReserva;
			this.noites = noites;
			this.porcentagemTipoDesconto = porcentagemTipoDesconto;
			this.nAdultos = nAdultos;
			this.porcentagemAgenciaViagem = porcentagemAgenciaViagem;
		}
		
		public String items() {
			return String.valueOf(listaReserva.size());
           
		}
        
		public String sumReservas() throws ParseException{
			sumReservas = listaReserva.stream()
					.mapToDouble(o -> Double.valueOf(o.getTotalDiaria().replaceAll(",", ".")))
					.sum();
			
			return formatWithComma.format(sumReservas);
			
		}
		
		public String sumServicos() {
			sumServicos = listaReserva.stream()
					.mapToDouble(o -> Double.valueOf(o.getTotalServico().replaceAll(",", ".")))
					.sum();
			
            return formatWithComma.format(sumServicos);
		}
	
		public String somaGeralGeral() {
			this.sumGeral =  this.sumReservas + this.sumServicos;
			return formatWithComma.format(this.sumGeral);
	
		}
	
		
		public String descontoPermanencia() {
			
			this.descontoPermanencia = 0.0;
			if (noites > 10) {
				this.descontoPermanencia =  this.sumGeral * 0.05;
			}
			return formatWithComma.format(this.descontoPermanencia);
		}

		public String descontoTipoPromocional() {
			this.valorDescontoTipoPromocional =  this.sumGeral * porcentagemTipoDesconto/100;
			return formatWithComma.format(this.valorDescontoTipoPromocional);
	
		}
		
		public String descontoAgenciaViagem() {
			this.valorDescontoAgenciaViagem =  this.sumGeral * porcentagemAgenciaViagem/100;
			return formatWithComma.format(this.valorDescontoAgenciaViagem);
	
		}
		
		public String descontoNumeroAdultos() {
			this.valorDescontoAdultos = 0.0;
			if (nAdultos > 15) {
				this.valorDescontoAdultos =  this.sumGeral * 0.07;
			}
			return formatWithComma.format(this.valorDescontoAdultos);
	
		}
		
		public String totalDescontos() {
			
			this.valorTotalDescontos = this.valorDescontoAdultos +
					                   this.valorDescontoTipoPromocional +
					                   this.descontoPermanencia+
					                   this.valorDescontoAgenciaViagem;
			
			return formatWithComma.format(valorTotalDescontos);
	
		}
		
		public String totalLiquido() {
			this.valorTotalLiquido = this.sumGeral - this.valorTotalDescontos;
			return formatWithComma.format(valorTotalLiquido);
	
		}

		public ObservableList<ItemReserva> getListaReserva() {
			return listaReserva;
		}

		public void setListaReserva(ObservableList<ItemReserva> listaReserva) {
			this.listaReserva = listaReserva;
		}

		public Integer getNoites() {
			return noites;
		}

		public void setNoites(Integer noites) {
			this.noites = noites;
		}

		public Integer getnAdultos() {
			return nAdultos;
		}

		public void setnAdultos(Integer nAdultos) {
			this.nAdultos = nAdultos;
		}

		public Double getSumReservas() {
			return sumReservas;
		}

		public void setSumReservas(Double sumReservas) {
			this.sumReservas = sumReservas;
		}

		public Double getSumServicos() {
			return sumServicos;
		}

		public void setSumServicos(Double sumServicos) {
			this.sumServicos = sumServicos;
		}

		public Double getSumGeral() {
			return sumGeral;
		}

		public void setSumGeral(Double sumGeral) {
			this.sumGeral = sumGeral;
		}

		public Double getDescontoPermanencia() {
			return descontoPermanencia;
		}

		public void setDescontoPermanencia(Double descontoPermanencia) {
			this.descontoPermanencia = descontoPermanencia;
		}

		public Double getPorcentagemTipoDesconto() {
			return porcentagemTipoDesconto;
		}

		public void setPorcentagemTipoDesconto(Double porcentagemTipoDesconto) {
			this.porcentagemTipoDesconto = porcentagemTipoDesconto;
		}

		public Double getValorDescontoTipoPromocional() {
			return valorDescontoTipoPromocional;
		}

		public void setValorDescontoTipoPromocional(Double valorDescontoTipoPromocional) {
			this.valorDescontoTipoPromocional = valorDescontoTipoPromocional;
		}

		public Double getValorDescontoAdultos() {
			return valorDescontoAdultos;
		}

		public void setValorDescontoAdultos(Double valorDescontoAdultos) {
			this.valorDescontoAdultos = valorDescontoAdultos;
		}

		public Double getValorTotalDescontos() {
			return valorTotalDescontos;
		}

		public void setValorTotalDescontos(Double valorTotalDescontos) {
			this.valorTotalDescontos = valorTotalDescontos;
		}

		public Double getValorTotalLiquido() {
			return valorTotalLiquido;
		}

		public void setValorTotalLiquido(Double valorTotalLiquido) {
			this.valorTotalLiquido = valorTotalLiquido;
		}

		public NumberFormat getFormatWithComma() {
			return formatWithComma;
		}

		public void setFormatWithComma(NumberFormat formatWithComma) {
			this.formatWithComma = formatWithComma;
		}

		public NumberFormat getFormatWithPoint() {
			return formatWithPoint;
		}

		public void setFormatWithPoint(NumberFormat formatWithPoint) {
			this.formatWithPoint = formatWithPoint;
		}

		public Double getValorDescontoAgenciaViagem() {
			return valorDescontoAgenciaViagem;
		}

		public void setValorDescontoAgenciaViagem(Double valorDescontoAgenciaViagem) {
			this.valorDescontoAgenciaViagem = valorDescontoAgenciaViagem;
		}

		public Double getPorcentagemAgenciaViagem() {
			return porcentagemAgenciaViagem;
		}

		public void setPorcentagemAgenciaViagem(Double porcentagemAgenciaViagem) {
			this.porcentagemAgenciaViagem = porcentagemAgenciaViagem;
		}
		
		
		
         
}
