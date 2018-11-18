package basedados;

import java.util.Comparator;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Categoria;

public abstract class TableCategoriaLoad {
	
   public static ObservableList<Categoria> load(){ 

	   ObservableList<Categoria> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Categoria(1, "QUARTO KING SUPERIOR", 
			                      "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto King Superior imposs�vel de ignorar.", 3, 1, "108,75"));
	   lista.add(new Categoria(2, "QUARTO QUEEN DELUXE", "A partir dessas acomoda��es no Las Vegas Deluxe Room, voc� pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em m�rmore italiano. No Trump, o luxo est� inclu�do.", 4,1,"135,00"));
	   lista.add(new Categoria(3, "QUARTO KING DELUXE", "A partir destas acomoda��es Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da pr�pria sala. Relaxe na sua �rea de estar confort�vel, com uma televis�o HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em m�rmore italiano."+
			                      "No Trump, o luxo est� inclu�do.", 5,2,"205,01"));
	   lista.add(new Categoria(4,"QUARTO QUEEN SUPERIOR", "Os h�spedes do Trump International Hotel Manaus s�o imediatamente cativados pelas requintadas entradas em m�rmore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decora��o elegante e moderna e mobili�rio de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do ch�o ao teto."+
			                      "Todos tornam o Quarto Queen Superior imposs�vel de ignorar.",4,2,"123,75"));
	   lista.add(new Categoria(5,"SUITE DELUXE COM 1 QUARTO", "Entre em sua luxuosa su�te de um quarto em Manaus e sinta o mundo derreter. Com vistas panor�micas impressionantes da Manaus Strip, a sua espa�osa su�te"+ 
			                      "de luxo possui �reas de estar luxuosas, uma cozinha de alto padr�o em estilo europeu e banheira de m�rmore italiano.", 2,1, "176,25"));
	   
	   return lista;
   }
   
   public static ObservableList<Categoria> loadParcial(){ 

	   ObservableList<Categoria> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Categoria(1, "QUARTO KING SUPERIOR", "108,75", 3, 1));
	   lista.add(new Categoria(2, "QUARTO QUEEN DELUXE", "135,00", 4, 1));
	   lista.add(new Categoria(3, "QUARTO KING DELUXE", "205,01", 5, 2));
	   lista.add(new Categoria(4,"QUARTO QUEEN SUPERIOR", "123,75", 2, 1));
	   lista.add(new Categoria(5,"SUITE DELUXE COM 1 QUARTO", "176,25", 2, 0));
	   
	   return lista;
   }
   public static String getMaxIdCategoria(ObservableList<Categoria> lista) {

	   Categoria maxId = lista
			   .stream()
			   .max(Comparator.comparing(Categoria::getId))
			   .orElseThrow(NoSuchElementException::new);
	   
	   return String.valueOf(maxId.getId()+1);
	  
	}

}
