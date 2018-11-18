package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TipoPromocional;

public abstract class TableTipoPromocionalLoad {
	
   public static ObservableList<TipoPromocional> load(){ 

	   ObservableList<TipoPromocional> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new TipoPromocional(1, "NENHUM", 
               "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Las Vegas."+ 
               "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do chão ao teto."+
               "Todos tornam o Quarto King Superior impossível de ignorar.", "0,00"));
	   lista.add(new TipoPromocional(2, "CORPORATIVO", 
			                      "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto King Superior impossível de ignorar.", "10,85"));
	   lista.add(new TipoPromocional(3, "REUNIÃO SEMINÁRIO", "A partir dessas acomodações no Las Vegas Deluxe Room, você pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em mármore italiano. No Trump, o luxo está incluído.","5,5"));
	   lista.add(new TipoPromocional(4, "CARTÃO TRUMP HOTELS", "A partir destas acomodações Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em mármore italiano."+
			                      "No Trump, o luxo está incluído.","15,00"));
	   lista.add(new TipoPromocional(5,"FUNCIONÁRIO", "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto Queen Superior impossível de ignorar.","20,00"));
	   return lista;
   }
   public static String getMaxIdTipoPromocional(ObservableList<TipoPromocional> lista) {
		
	   Integer max = 0, id = 0;
       for (TipoPromocional cat : lista) {
            id = cat.getId();
    		if (id > max) {
            	max = id;
            }
        }
		return String.valueOf(max+1);
	}

}
