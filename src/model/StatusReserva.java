package model;
public enum StatusReserva {
	PRERESERVA("Pré-reserva", 1), CONFIRMADO("Confirmado", 2), CHECKIN ("Check-In", 3), CHECKOUT ("Check-Out", 4), CANCELADO ("Cancelado", 5) ;
	private int codigo;
    private String descricao;

    // construtor sempre private
    StatusReserva(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (StatusReserva e : StatusReserva.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (StatusReserva e : StatusReserva.values()) {
    		if (e.getDescricao().equals(descricao)) {
    			return e.getCodigo();
    		}
    	}
    	return 0;
    }

    public String getDescricao() {
		return this.descricao;
	}

    public int getCodigo() {
		return this.codigo;
	}
}   