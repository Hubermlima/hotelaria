package model;
public enum MeioTransporte {
	AVIAO("Avião", 1), NAVIO("Navio", 2), AUTOMOVEL("Automóvel", 3), ONIBUSTREM("Ônibus/Trem", 4), OUTRO("Outro", 5);
	private int codigo;
    private String descricao;

    // construtor sempre private
    MeioTransporte(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (MeioTransporte e : MeioTransporte.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (MeioTransporte e : MeioTransporte.values()) {
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