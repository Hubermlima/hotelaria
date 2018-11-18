package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AgenciaViagem;

public abstract class TableAgenciaViagemLoad {

	public static ObservableList<AgenciaViagem> reLoad(){ 
		   ObservableList<AgenciaViagem> lista = FXCollections.observableArrayList();	   
		   lista.add(new AgenciaViagem(1, "AGENCIA DE VIAGEM DISNEY", "10,85"));
		   lista.add(new AgenciaViagem(2, "VIAGEM SEGURO", "5,5"));
		   lista.add(new AgenciaViagem(3, "AGENCIA EXCLUSIVA","15,00"));
		   lista.add(new AgenciaViagem(4,"BOA VIAGEM", "20,00"));
		   lista.add(new AgenciaViagem(5,"SERVIÇOS EM VIAGEM", "25,00"));
		   
		   return lista;
	   }
	
   public static ObservableList<AgenciaViagem> load(){ 
	   ObservableList<AgenciaViagem> lista = FXCollections.observableArrayList();	   
	   lista.add(new AgenciaViagem(1, "AGENCIA DE VIAGEM DISNEY", 
			                      "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto King Superior impossível de ignorar.", "10,85"));
	   lista.add(new AgenciaViagem(2, "VIAGEM SEGURO", "A partir dessas acomodações no Las Vegas Deluxe Room, você pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em mármore italiano. No Trump, o luxo está incluído.","5,5"));
	   lista.add(new AgenciaViagem(3, "AGENCIA EXCLUSIVA", "A partir destas acomodações Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em mármore italiano."+
			                      "No Trump, o luxo está incluído.","15,00"));
	   lista.add(new AgenciaViagem(4,"BOA VIAGEM", "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto Queen Superior impossível de ignorar.","20,00"));
	   lista.add(new AgenciaViagem(5,"SERVIÇOS EM VIAGEM", "Entre em sua luxuosa suíte de um quarto em Manaus e sinta o mundo derreter. Com vistas panorâmicas impressionantes da Manaus Strip, a sua espaçosa suíte"+ 
			                      "de luxo possui áreas de estar luxuosas, uma cozinha de alto padrão em estilo europeu e banheira de mármore italiano.","25,00"));
	   
	   return lista;
   }
   public static String getMaxIdAgenciaViagem(ObservableList<AgenciaViagem> lista) {
		
	   Integer max = 0, id = 0;
       for (AgenciaViagem cat : lista) {
            id = cat.getId();
    		if (id > max) {
            	max = id;
            }
        }
		return String.valueOf(max+1);
	}

}
