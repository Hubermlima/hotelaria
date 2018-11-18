package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Torre;

public abstract class TableTorreLoad {
	   public static ObservableList<Torre> load(){ 

		   ObservableList<Torre> lista = FXCollections.observableArrayList();	   
		  
		   lista.add(new Torre(1, "TORRE NANTES", 1, 2, 3 ));
		   lista.add(new Torre(2, "TORRE BOURDEAUX", 4, 1, 5 ));
		   lista.add(new Torre(3, "TORRE MARSEILE", 3, 3, 4));
		   lista.add(new Torre(4, "TORRE PARIS", 7, 4, 4));
		   lista.add(new Torre(5, "TORRE LYON", 9, 3, 3));
		   
		   return lista;
	   }
	   public static String getMaxIdTorre(ObservableList<Torre> lista) {
			
		   Integer max = 0, id = 0;
	       for (Torre cat : lista) {
	            id = cat.getId();
	    		if (id > max) {
	            	max = id;
	            }
	        }
			return String.valueOf(max+1);
		}

        
}
