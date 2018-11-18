package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pais;

public abstract class TablePaisLoad {
	
   public static ObservableList<Pais> load(){ 

	   ObservableList<Pais> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Pais(1, "Estados Unidos"));
	   lista.add(new Pais(1, "Brasil"));
	   lista.add(new Pais(1, "Japão"));
	   lista.add(new Pais(1, "Inglaterra"));
	   lista.add(new Pais(1, "Canadá"));
	   
	   return lista;
   }
   public static String getMaxIdPais(ObservableList<Pais> lista) {
		
	   Integer max = 0, id = 0;
       for (Pais cat : lista) {
            id = cat.getId();
    		if (id > max) {
            	max = id;
            }
        }
		return String.valueOf(max+1);
	}

}
