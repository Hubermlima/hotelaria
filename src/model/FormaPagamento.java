package model;
public enum FormaPagamento {
	 DINHEIRO("Dinheiro", 4), CARTAOCREDITO("Cartão de crédito", 1), BOLETO("Boleto", 2), INTERNETBANK("Internet bank", 3), CHEQUE("Cheque", 5);
    private int codigo;
    private String descricao;

    // construtor sempre private
    FormaPagamento(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (FormaPagamento e : FormaPagamento.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (FormaPagamento e : FormaPagamento.values()) {
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