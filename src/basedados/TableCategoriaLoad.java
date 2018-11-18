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
			                      "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto King Superior impossível de ignorar.", 3, 1, "108,75"));
	   lista.add(new Categoria(2, "QUARTO QUEEN DELUXE", "A partir dessas acomodações no Las Vegas Deluxe Room, você pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em mármore italiano. No Trump, o luxo está incluído.", 4,1,"135,00"));
	   lista.add(new Categoria(3, "QUARTO KING DELUXE", "A partir destas acomodações Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em mármore italiano."+
			                      "No Trump, o luxo está incluído.", 5,2,"205,01"));
	   lista.add(new Categoria(4,"QUARTO QUEEN SUPERIOR", "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto Queen Superior impossível de ignorar.",4,2,"123,75"));
	   lista.add(new Categoria(5,"SUITE DELUXE COM 1 QUARTO", "Entre em sua luxuosa suíte de um quarto em Manaus e sinta o mundo derreter. Com vistas panorâmicas impressionantes da Manaus Strip, a sua espaçosa suíte"+ 
			                      "de luxo possui áreas de estar luxuosas, uma cozinha de alto padrão em estilo europeu e banheira de mármore italiano.", 2,1, "176,25"));
	   
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
