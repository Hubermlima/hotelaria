package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TipoPromocional;

public abstract class TableTipoPromocionalLoad {
	
   public static ObservableList<TipoPromocional> load(){ 

	   ObservableList<TipoPromocional> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new TipoPromocional(1, "NENHUM", 
               "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Las Vegas."+ 
               "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do ch�o ao teto."+
               "Todos tornam o Quarto King Superior imposs�vel de ignorar.", "0,00"));
	   lista.add(new TipoPromocional(2, "CORPORATIVO", 
			                      "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto King Superior imposs�vel de ignorar.", "10,85"));
	   lista.add(new TipoPromocional(3, "REUNI�O SEMIN�RIO", "A partir dessas acomoda��es no Las Vegas Deluxe Room, voc� pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em m�rmore italiano. No Trump, o luxo est� inclu�do.","5,5"));
	   lista.add(new TipoPromocional(4, "CART�O TRUMP HOTELS", "A partir destas acomoda��es Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em m�rmore italiano."+
			                      "No Trump, o luxo est� inclu�do.","15,00"));
	   lista.add(new TipoPromocional(5,"FUNCION�RIO", "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto Queen Superior imposs�vel de ignorar.","20,00"));
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
