package codigosuteis;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CodigosUteis {
    public void codigosUteis() {
    	
    	//DecimalFormatSymbols decimal = new DecimalFormatSymbols (Locale.getDefault ()); 
        // String sep = String.valueOf (decimal.getDecimalSeparator ());
         //textFieldAndar.setText(String.valueOf (decimal.getDecimalSeparator())); 
         
         double amount =200.0;
         Locale locale = new Locale("en", "US");      
         NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
         System.out.println(currencyFormatter.format(amount));
         
         // sem cifrao
         amount = 300.0;
         DecimalFormat twoPlaces = new DecimalFormat("0.00");
         System.out.println(twoPlaces.format(amount));
         
         // com separador de milhar
         amount = 2000000;    
         System.out.println(String.format("%,.2f", amount));
     	
    }
}
