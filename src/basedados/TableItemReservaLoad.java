package basedados;

import java.util.Comparator;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemReserva;

public abstract class TableItemReservaLoad {
	
   public static ObservableList<ItemReserva> load(){ 

	   ObservableList<ItemReserva> lista = FXCollections.observableArrayList();	   
	   
	   // retorna por enquanto lista vazia
	   
	   return lista;
   }
   
   
   public static String getMaxIdItemReserva(ObservableList<ItemReserva> lista) {

	   ItemReserva maxId = lista
			   .stream()
			   .max(Comparator.comparing(ItemReserva::getId))
			   .orElseThrow(NoSuchElementException::new);
	   
	   return String.valueOf(maxId.getId()+1);
	  
	}

}
