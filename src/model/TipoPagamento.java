package model;
public enum TipoPagamento {
	AVISTA ("A vista", 1), ENTRADA("Entrada", 2), PARCELAFINAL ("Parcela final", 3) ;
	private int codigo;
    private String descricao;

    // construtor sempre private
    TipoPagamento(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (TipoPagamento e : TipoPagamento.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (TipoPagamento e : TipoPagamento.values()) {
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