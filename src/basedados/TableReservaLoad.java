package basedados;

//import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import model.Hospede;
//import model.ItemReserva;
import model.Reserva;
//import model.TipoPromocional;

public abstract class TableReservaLoad {
	
   public static ObservableList<Reserva> load(){ 
       
	   ObservableList<Reserva> lista = FXCollections.observableArrayList();
	   /*
	   ObservableList<ItemReserva> listaItensReserva = FXCollections.observableArrayList();
	  
	   Hospede hospede = new Hospede (1, 1, "SMITH", "ADAM");
	   TipoPromocional tipoPromocional = new TipoPromocional (1, "CORPORATIVO", "10,85");
       listaItensReserva = TableItemReservaLoad.load();
       
	   lista.add(new Reserva(1,
			                 LocalDateTime.of(2018, 10, 28, 14, 0),
	                         LocalDateTime.of(2018, 10, 10, 14, 0),
	                         LocalDateTime.of(2018, 10, 20, 14, 0),
	                         1,
	                         tipoPromocional,
			                 "N24610",
			                 hospede,
			                 listaItensReserva));
	   */   
	   return lista;
   }
   
      public static String getMaxIdReserva(ObservableList<Reserva> lista) {

   	   Reserva maxId = lista
   			   .stream()
   			   .max(Comparator.comparing(Reserva::getId))
   			   .orElseThrow(NoSuchElementException::new);
   	   return String.valueOf(maxId.getId()+1);

      }

}
