package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Usuario;

public abstract class TableUsuarioLoad {
	
   public static ObservableList<Usuario> load(){ 

	   ObservableList<Usuario> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Usuario("h", "1"));
	   lista.add(new Usuario("uninorte", "6yiQ54*@"));
	   
	   return lista;
   }
   

}
