package basedados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Categoria;
import model.Torre;
import model.UnidadeHabitacional;

public abstract class TableUnidadeHabitacionalLoad {
	
   public static ObservableList<UnidadeHabitacional> load(){ 

	   ObservableList<UnidadeHabitacional> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new UnidadeHabitacional(1, "SUPERIOR ROOM", 3, "500", 1, 
			                       new Torre (1, "TORRE NANTES"), 
			                      "Uma Cama king size. Vista da cidade. Este espaçoso quarto de 100 m² apresenta janelas do chão ao teto de 3 metros de altura e está decorado com superfícies de madeira e granito e"+
			                      "luxuosos electrodomésticos da marca Wolf, Bosch e Sub Zero. ",
			                      new Categoria (1, "QUARTO KING SUPERIOR", "108,75", 3, 1)));
	  
	   lista.add(new UnidadeHabitacional(2, "SUPERIOR ROOM", 2, "550", 6, new Torre(2, "TORRE BOURDEAUX"), 
               "Duas Cama queen size. Vista da cidade. Este espaçoso quarto de 100 m² apresenta janelas do chão ao teto de 3 metros de altura e está decorado com superfícies de madeira e granito e"+
               "luxuosos electrodomésticos da marca Wolf, Bosch e Sub Zero. "+
               
               "CARACTERÍSTICAS DO QUARTO: Lençóis de 300 fios Carilo Cale. Espaçosa área de estar com sofá secional. Cofre no quarto compatível com laptop." + 
               "Banheiro de mármore italiano. Banheira de hidromassagem de imersão. Chuveiro separado" + 
               
               "TECNOLOGIA: Largura de banda de alta qualidade, tanto com fio quanto sem fio, incluída na taxa de resort. TV de plasma de 42 polegadas." + 
               "Sistema de som mp3 player. Jornais eletrônicos gratuitos" + 
               
               "COZINHA: Kitchenette de estilo europeu com eletrodomésticos da Wolf, Bosch e Sub-Zero. Geladeira compacta. Cafeteira no quarto",
               new Categoria (3, "QUARTO KING DELUXE", "205,01", 5, 2)));
	   
	   lista.add(new UnidadeHabitacional(3, "DELUXE ROOM", 4, "740", 3, new Torre(3, "TORRE MARSEILE"), 
               "Uma Cama king size. Vista da cidade. Este espaçoso quarto de 100 m² apresenta janelas do chão ao teto de 3 metros de altura e está decorado com superfícies de madeira e granito e"+
               "luxuosos electrodomésticos da marca Wolf, Bosch e Sub Zero. "+
               
               "CARACTERÍSTICAS DO QUARTO: Lençóis de 300 fios Carilo Cale. Espaçosa área de estar com sofá secional. Cofre no quarto compatível com laptop." + 
               "Banheiro de mármore italiano. Banheira de hidromassagem de imersão. Chuveiro separado" + 
               
               "TECNOLOGIA: Largura de banda de alta qualidade, tanto com fio quanto sem fio, incluída na taxa de resort. TV de plasma de 42 polegadas." + 
               "Sistema de som mp3 player. Jornais eletrônicos gratuitos" + 
               
               "COZINHA: Kitchenette de estilo europeu com eletrodomésticos da Wolf, Bosch e Sub-Zero. Geladeira compacta. Cafeteira no quarto",
               new Categoria (5,"SUITE DELUXE COM 1 QUARTO", "176,25", 2, 1)));

	   
	   lista.add(new UnidadeHabitacional(4, "SUPERIOR ONE BEDROOM SUITE", 5, "798", 6, new Torre(3, "TORRE MARSEILE"), 
               "Um quarto com cama king size. Vista da cidade. Este espaçoso quarto de 100 m² apresenta janelas do chão ao teto de 3 metros de altura e está decorado com superfícies de madeira e granito e"+
               "luxuosos electrodomésticos da marca Wolf, Bosch e Sub Zero. "+
               
               "CARACTERÍSTICAS DO QUARTO: Lençóis de 300 fios Carilo Cale. Espaçosa área de estar com sofá secional. Cofre no quarto compatível com laptop." + 
               "Banheiro de mármore italiano. Banheira de hidromassagem de imersão. Chuveiro separado" + 
               
               "TECNOLOGIA: Largura de banda de alta qualidade, tanto com fio quanto sem fio, incluída na taxa de resort. TV de plasma de 42 polegadas." + 
               "Sistema de som mp3 player. Jornais eletrônicos gratuitos" + 
               
               "COZINHA: Kitchenette de estilo europeu com eletrodomésticos da Wolf, Bosch e Sub-Zero. Geladeira compacta. Cafeteira no quarto", 
               new Categoria (4,"QUARTO QUEEN SUPERIOR", "123,75", 4, 2)));


	    lista.add(new UnidadeHabitacional(5, "PENTHOUSE ONE BEDROOM SUITE", 2, "1098", 5, new Torre(5, "TORRE LYON"), 
               "Um quarto com cama king size. Vista da cidade. Este espaçoso quarto de 100 m² apresenta janelas do chão ao teto de 3 metros de altura e está decorado com superfícies de madeira e granito e"+
               "luxuosos electrodomésticos da marca Wolf, Bosch e Sub Zero. "+
               
               "CARACTERÍSTICAS DO QUARTO: Lençóis de 300 fios Carilo Cale. Espaçosa área de estar com sofá secional. Cofre no quarto compatível com laptop." + 
               "Banheiro de mármore italiano. Banheira de hidromassagem de imersão. Chuveiro separado" + 
               
               "TECNOLOGIA: Largura de banda de alta qualidade, tanto com fio quanto sem fio, incluída na taxa de resort. TV de plasma de 42 polegadas." + 
               "Sistema de som mp3 player. Jornais eletrônicos gratuitos" + 
               
               "COZINHA: Kitchenette de estilo europeu com eletrodomésticos da Wolf, Bosch e Sub-Zero. Geladeira compacta. Cafeteira no quarto.",
               new Categoria (2, "QUARTO QUEEN DELUXE", "135,00", 4, 1)));

     	   return lista;
   }
   
   public static ObservableList<UnidadeHabitacional> filtrarFaixaPreco(ObservableList<UnidadeHabitacional> lista){
	   List<UnidadeHabitacional> listaFiltrada = new ArrayList<>();
	   listaFiltrada = lista.stream()
			    .filter(p -> Double.valueOf(p.getCategoria().getPrecoCategoria())  > 16.00).collect(Collectors.toList());
	   return FXCollections.observableArrayList(listaFiltrada);
	   
   }
   
   public static String getMaxIdUH(ObservableList<UnidadeHabitacional> lista) {
		
	   Integer max = 0, id = 0;
       for (UnidadeHabitacional uH : lista) {
            id = uH.getId();
    		if (id > max) {
            	max = id;
            }
        }
		return String.valueOf(max+1);
	}

}
