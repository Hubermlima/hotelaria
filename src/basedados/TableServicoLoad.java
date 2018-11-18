package basedados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Servico;

public abstract class TableServicoLoad {
	
   public static ObservableList<Servico> load(){ 

	   ObservableList<Servico> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Servico(1, "PISCINA", 
			                      "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Las Vegas."+ 
	                              "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto King Superior impossível de ignorar.", "108,75"));
	   lista.add(new Servico(2, "CAFÉ DA MANHÃ", "A partir dessas acomodações no Las Vegas Deluxe Room, você pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
			                      "combinadas apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
			                      "banheira em mármore italiano. No Trump, o luxo está incluído.","135,00"));
	   lista.add(new Servico(3, "ESTACIONAMENTO", "A partir destas acomodações Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
			                      "apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em mármore italiano."+
			                      "No Trump, o luxo está incluído.","123,75"));
	   lista.add(new Servico(4,"CAMPO DE GOLFE", "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Manaus."+ 
			                      "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do chão ao teto."+
			                      "Todos tornam o Quarto Queen Superior impossível de ignorar.","231,67"));
	   lista.add(new Servico(5,"LAGO DE PESCA", "Entre em sua luxuosa suíte de um quarto em Manaus e sinta o mundo derreter. Com vistas panorâmicas impressionantes da Manaus Strip, a sua espaçosa suíte"+ 
			                      "de luxo possui áreas de estar luxuosas, uma cozinha de alto padrão em estilo europeu e banheira de mármore italiano.","176,25"));
	    
	   lista.add(new Servico(6,"SERVIÇO DE QUARTO", "Entre em sua luxuosa suíte de um quarto em Manaus e sinta o mundo derreter. Com vistas panorâmicas impressionantes da Manaus Strip, a sua espaçosa suíte"+ 
               "de luxo possui áreas de estar luxuosas, uma cozinha de alto padrão em estilo europeu e banheira de mármore italiano.","199,96"));

	   return lista;
   }
   
   public static ObservableList<Servico> reLoadChecked(){ 

	   ObservableList<Servico> lista = FXCollections.observableArrayList();	   
	  
	   lista.add(new Servico(1, "PISCINA", 
               "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Las Vegas."+ 
               "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da cidade fora das janelas do chão ao teto."+
               "Todos tornam o Quarto King Superior impossível de ignorar.", "108,75", false));
lista.add(new Servico(2, "CAFÉ DA MANHÃ", "A partir dessas acomodações no Las Vegas Deluxe Room, você pode desfrutar de vistas deslumbrantes de Manaus Strip e das montanhas ao redor - "+
               "combinadas apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa"+
               "banheira em mármore italiano. No Trump, o luxo está incluído.","135,00", false));
lista.add(new Servico(3, "ESTACIONAMENTO", "A partir destas acomodações Manaus Deluxe King Room, desfrute de vistas deslumbrantes sobre a Manaus Strip e as montanhas circundantes - combinadas"+
               "apenas com a beleza da própria sala. Relaxe na sua área de estar confortável, com uma televisão HD de tela plana, ou relaxe verdadeiramente na sua luxuosa banheira em mármore italiano."+
               "No Trump, o luxo está incluído.","123,75", false));
lista.add(new Servico(4,"CAMPO DE GOLFE", "Os hóspedes do Trump International Hotel Manaus são imediatamente cativados pelas requintadas entradas em mármore destes quartos de hotel em Manaus."+ 
               "Em seguida, vem a decoração elegante e moderna e mobiliário de luxo. Em seguida, vistas deslumbrantes da City fora das janelas do chão ao teto."+
               "Todos tornam o Quarto Queen Superior impossível de ignorar.","231,67", false));
lista.add(new Servico(5,"LAGO DE PESCA", "Entre em sua luxuosa suíte de um quarto em Manaus e sinta o mundo derreter. Com vistas panorâmicas impressionantes da Manaus Strip, a sua espaçosa suíte"+ 
               "de luxo possui áreas de estar luxuosas, uma cozinha de alto padrão em estilo europeu e banheira de mármore italiano.","176,25", false));

lista.add(new Servico(6,"SERVIÇO DE QUARTO", "Entre em sua luxuosa suíte de um quarto em Manaus e sinta o mundo derreter. Com vistas panorâmicas impressionantes da Manaus Strip, a sua espaçosa suíte"+ 
"de luxo possui áreas de estar luxuosas, uma cozinha de alto padrão em estilo europeu e banheira de mármore italiano.","199,96", false));

	   
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
