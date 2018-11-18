package model;
public enum LocalizacaoTorre {
    NORTE("Norte", 1), SUL("Sul", 2), LESTE("Leste", 3), OESTE("Oeste", 4);
	private int codigo;
    private String descricao;

    // construtor sempre private
    LocalizacaoTorre(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (LocalizacaoTorre e : LocalizacaoTorre.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (LocalizacaoTorre e : LocalizacaoTorre.values()) {
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