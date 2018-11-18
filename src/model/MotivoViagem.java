package model;
public enum MotivoViagem {
	TURISMO("Turismo", 1), NEGOCIO("Negócio", 2), CONVENCAO("Convenção", 3), OUTRO("Outro", 4);
	private int codigo;
    private String descricao;

    // construtor sempre private
    MotivoViagem(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (MotivoViagem e : MotivoViagem.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (MotivoViagem e : MotivoViagem.values()) {
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