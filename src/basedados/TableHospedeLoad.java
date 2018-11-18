package basedados;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AgenciaViagem;
import model.Hospede;
import model.Pais;

public abstract class TableHospedeLoad {
   
	public static ObservableList<Hospede> load(){ 
	   ObservableList<Hospede> lista = FXCollections.observableArrayList();	 
	   
		// --------------------------------- PRIMEIRO HOSPEDE ------------------------------------------------------
	   Pais pais = new Pais(1,"EUA");
	   AgenciaViagem agenciaViagem = new AgenciaViagem(1, "AGENCIA DE VIAGEM DISNEY", "10,85");
	   
	   Hospede hospede = new Hospede(1, 
			                 1, 
			                 "SMITH", 
			                 "ADAM",
			                 LocalDate.of(1974, 5, 25),
			                 1,
			                 3,
			                 "1287543980", 
			                 "Detran",
			                 "Brasileira",
			                 3,
			                 "7 de setembro", 
			                 "28A", 
			                 "Conjunto Dom Pedro",
			                 "Dom Pedro I", 
			                 "69075-080",
			                 "Las Vegas", 
			                 "LA", 
			                 "Comerciante", 
			                 1,
			                 LocalDate.of(1974, 5, 25), 
			                 "Apple Inc",
			                 "apple@gmail.com", 
			                 "(92) 3543-8970",
			                 "primeiroEmail@hotmail.com", 
			                 "segundoEmail@gmail.com", 
			                 "(55) 9856-2973", 
			                 "(11) 6799 0978");
	   
	   hospede.setPais(pais);
	   hospede.setAgencia(agenciaViagem);
	   lista.add(hospede);
	   
	// --------------------------------- SEGUNDO HOSPEDE ------------------------------------------------------
	   pais = new Pais(1,"BRASIL");
	   agenciaViagem = new AgenciaViagem(3, "AGENCIA EXCLUSIVA","15,00");
	   hospede = new Hospede(2, 
			                 1, 
			                 "HUBER", 
			                 "MARTINS LIMA",
			                 LocalDate.of(1984, 6, 25),
			                 1,
			                 3,
			                 "1287543980", 
			                 "Detran",
			                 "Americana",
			                 3,
			                 "7 de Novembro", 
			                 "28A", 
			                 "Conjunto Dom Pedro",
			                 "Dom Pedro I", 
			                 "69075-080",
			                 "Las Vegas", 
			                 "LA", 
			                 "Comerciante", 
			                 1,
			                 LocalDate.of(2018, 5, 25), 
			                 "Apple Inc",
			                 "apple@gmail.com", 
			                 "(92) 3543-8970",
			                 "primeiroEmail@hotmail.com", 
			                 "segundoEmail@gmail.com", 
			                 "(55) 9856-2973", 
			                 "(11) 6799 0978");
	   
	   hospede.setPais(pais);
	   hospede.setAgencia(agenciaViagem);
	   lista.add(hospede);
	
	   
	   return lista;
   }
	
	public static ObservableList<Hospede> loadParcial(){ 
		   ObservableList<Hospede> lista = FXCollections.observableArrayList();	 
		   
			// --------------------------------- PRIMEIRO HOSPEDE ------------------------------------------------------
		   AgenciaViagem agenciaViagem = new AgenciaViagem(1, "AGENCIA DE VIAGEM DISNEY", "10,85");
		   Hospede hospede = new Hospede(1, 
				                 1, 
				                 "SMITH", 
				                 "ADAM"
				                 );
		   hospede.setAgencia(agenciaViagem);
		   lista.add(hospede);
		   
		// --------------------------------- SEGUNDO HOSPEDE ------------------------------------------------------
		   agenciaViagem = new AgenciaViagem(3, "AGENCIA EXCLUSIVA","15,00");
		   hospede = new Hospede(2, 
				                 1, 
				                 "MARTINS LIMA", 
				                 "HUBER"
				                 );
		   hospede.setAgencia(agenciaViagem);
		   lista.add(hospede);
		
		   
		   return lista;
	   }
   
   public static String getMaxIdHospede(ObservableList<Hospede> lista) {
		
	   Integer max = 0, id = 0;
       for (Hospede hospede : lista) {
            id = hospede.getId();
    		if (id > max) {
            	max = id;
            }
        }
		return String.valueOf(max+1);
	}


}
