package model;
public enum TituloTratamento {
	SR("Sr.", 1), SRA("Sra.", 2), EXMO("Exmo.", 3), DR("Dr.", 4), EXMA("Exma.", 5), DRA("Dra.", 6);
	private int codigo;
    private String descricao;

    // construtor sempre private
    TituloTratamento(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (TituloTratamento e : TituloTratamento.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (TituloTratamento e : TituloTratamento.values()) {
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