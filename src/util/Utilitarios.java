package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import model.ItemReserva;

public abstract class Utilitarios {

public static void maskTudoMaiuculoTextField(TextField textField) {

    textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {   
    	String letrasMaiusculas = newValue.toUpperCase();
		textField.setText(letrasMaiusculas);
    });
}

public static void maxField(final TextField textField, final Integer length) {

    textField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            if (newValue.length() > length)
                textField.setText(oldValue);
            
            if (textField.getText() == null || textField.getText().replaceAll(" ","").isEmpty()) { 
            	textField.setPromptText("Não é permitido em branco!");
            	textField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
              }
            else
            	textField.setStyle(null);
            	
            
        }
    });
  }
public static void maxTextArea(final TextArea textArea, final Integer length) {
    textArea.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            if (newValue.length() > length)
                textArea.setText(oldValue);
            if (newValue.length() == 0) { 
            	textArea.setPromptText("Não é permitido em branco!");
            	textArea.setStyle("-fx-border-color: red ; -fx-border-width: 1px ; -fx-border-radius: 1px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
              }
            else
            	textArea.setStyle(null);
            
        }
    });
  }

    public static UnaryOperator<Change> integerFilter = change -> {
	    String input = change.getText();
	    if (input.matches("[0-9]*")) { 
	        return change;
	    }
	    return null;
	};

	//monetary
	public static UnaryOperator<TextFormatter.Change> filterMonetary = new UnaryOperator<TextFormatter.Change>() {

        @Override
        public TextFormatter.Change apply(TextFormatter.Change t) {

            if (t.isReplaced()) 
                if(t.getText().matches("[^0-9]"))
                    t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
            

            if (t.isAdded()) {
                if (t.getControlText().contains(",")) {
                    if (t.getText().matches("[^0-9]")) {
                        t.setText("");
                    }
                } else if (t.getText().matches("[^0-9\\,]")) {
                    t.setText("");
                }
            }

            return t;
        }
    };

    // max length
    public static UnaryOperator<TextFormatter.Change> filterMaxLength = (TextFormatter.Change change) -> {
             if (change.getControlNewText().length() > 35) {
                   return null;                         
              }
              return change;
      };

    public static NumberFormat meuFormato() {
          DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ROOT);
          symbols.setDecimalSeparator(',');
         // symbols.setGroupingSeparator('.');
          return new DecimalFormat("#0.00", symbols);
      }
    
    public static void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    
    public static boolean isRepetido(ObservableList<ItemReserva> listaReservados, String numero) {
		Optional<ItemReserva> result = listaReservados.stream()
				.filter(o -> o.getNumero().equals(numero))
				.findAny();

        if (!result.isPresent()) {
	        return false;
        }
	    return true;	
	}
    
}
