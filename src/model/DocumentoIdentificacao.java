package model;
public enum DocumentoIdentificacao {
	CARTEIRAIDENTIDADE("Carteira identidade", 1), CPF("Cpf", 2), CARTEIRAMOTORISTA("Carteira motorista", 3);
    private int codigo;
	private String descricao;

    // construtor sempre private
    DocumentoIdentificacao(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (DocumentoIdentificacao e : DocumentoIdentificacao.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (DocumentoIdentificacao e : DocumentoIdentificacao.values()) {
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