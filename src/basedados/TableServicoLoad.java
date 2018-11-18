package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Servico;

public abstract class TableServicoLoad {
	
   public static ObservableList<Servico> load(){ 

	   ObservableList<Servico> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Servico(1, "PISCINA", 
			                      "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto King Superior imposs�vel de ignorar.", "108,75"));
	   lista.add(new Servico(2, "CAF� DA MANH�", "A partir dessas acomoda��es no Las Vegas Deluxe Room, voc� pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em m�rmore italiano. No Trump, o luxo est� inclu�do.","135,00"));
	   lista.add(new Servico(3, "ESTACIONAMENTO", "A partir destas acomoda��es Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em m�rmore italiano."+
			                      "No Trump, o luxo est� inclu�do.","123,75"));
	   lista.add(new Servico(4,"CAMPO DE GOLFE", "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto Queen Superior imposs�vel de ignorar.","231,67"));
	   lista.add(new Servico(5,"LAGO DE PESCA", "Entre em sua luxuosa su�te de um quarto em Manaus e sinta o mundo derreter. Com vistas panor�micas impressionantes da Manaus Strip, a sua espa�osa su�te"+ 
			                      "de luxo possui �reas de estar luxuosas, uma cozinha de alto padr�o em estilo europeu e banheira de m�rmore italiano.","176,25"));
	    
	   lista.add(new Servico(6,"SERVI�O DE QUARTO", "Entre em sua luxuosa su�te de um quarto em Manaus e sinta o mundo derreter. Com vistas panor�micas impressionantes da Manaus Strip, a sua espa�osa su�te"+ 
               "de luxo possui �reas de estar luxuosas, uma cozinha de alto padr�o em estilo europeu e banheira de m�rmore italiano.","199,96"));

	   return lista;
   }
   
   public static ObservableList<Servico> reLoadChecked(){ 

	   ObservableList<Servico> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Servico(1, "PISCINA", 
               "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Las Vegas."+ 
               "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do ch�o ao teto."+
               "Todos tornam o Quarto King Superior imposs�vel de ignorar.", "108,75", false));
lista.add(new Servico(2, "CAF� DA MANH�", "A partir dessas acomoda��es no Las Vegas Deluxe Room, voc� pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
               "combinadas apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
               "banheira em m�rmore italiano. No Trump, o luxo est� inclu�do.","135,00", false));
lista.add(new Servico(3, "ESTACIONAMENTO", "A partir destas acomoda��es Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
               "apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em m�rmore italiano."+
               "No Trump, o luxo est� inclu�do.","123,75", false));
lista.add(new Servico(4,"CAMPO DE GOLFE", "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Manaus."+ 
               "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do ch�o ao teto."+
               "Todos tornam o Quarto Queen Superior imposs�vel de ignorar.","231,67", false));
lista.add(new Servico(5,"LAGO DE PESCA", "Entre em sua luxuosa su�te de um quarto em Manaus e sinta o mundo derreter. Com vistas panor�micas impressionantes da Manaus Strip, a sua espa�osa su�te"+ 
               "de luxo possui �reas de estar luxuosas, uma cozinha de alto padr�o em estilo europeu e banheira de m�rmore italiano.","176,25", false));

lista.add(new Servico(6,"SERVI�O DE QUARTO", "Entre em sua luxuosa su�te de um quarto em Manaus e sinta o mundo derreter. Com vistas panor�micas impressionantes da Manaus Strip, a sua espa�osa su�te"+ 
"de luxo possui �reas de estar luxuosas, uma cozinha de alto padr�o em estilo europeu e banheira de m�rmore italiano.","199,96", false));

	   
	   return lista;
   }
   public static String getMaxIdServico(ObservableList<Servico> lista) {
		
	   Integer max = 0, id = 0;
       for (Servico cat : lista) {
            id = cat.getId();
    		if (id > max) {
            	max = id;
            }
        }
		return String.valueOf(max+1);
	}

}
