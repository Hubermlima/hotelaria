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
		   lista.add(new AgenciaViagem(5,"SERVI�OS EM VIAGEM", "25,00"));
		   
		   return lista;
	   }
	
   public static ObservableList<AgenciaViagem> load(){ 
	   ObservableList<AgenciaViagem> lista = FXCollections.observableArrayList();	   
	   lista.add(new AgenciaViagem(1, "AGENCIA DE VIAGEM DISNEY", 
			                      "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto King Superior imposs�vel de ignorar.", "10,85"));
	   lista.add(new AgenciaViagem(2, "VIAGEM SEGURO", "A partir dessas acomoda��es no Las Vegas Deluxe Room, voc� pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em m�rmore italiano. No Trump, o luxo est� inclu�do.","5,5"));
	   lista.add(new AgenciaViagem(3, "AGENCIA EXCLUSIVA", "A partir destas acomoda��es Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em m�rmore italiano."+
			                      "No Trump, o luxo est� inclu�do.","15,00"));
	   lista.add(new AgenciaViagem(4,"BOA VIAGEM", "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto Queen Superior imposs�vel de ignorar.","20,00"));
	   lista.add(new AgenciaViagem(5,"SERVI�OS EM VIAGEM", "Entre em sua luxuosa su�te de um quarto em Manaus e sinta o mundo derreter. Com vistas panor�micas impressionantes da Manaus Strip, a sua espa�osa su�te"+ 
			                      "de luxo possui �reas de estar luxuosas, uma cozinha de alto padr�o em estilo europeu e banheira de m�rmore italiano.","25,00"));
	   
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
