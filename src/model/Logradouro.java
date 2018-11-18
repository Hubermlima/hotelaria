package model;
public enum Logradouro {
	
	ALAMEDA("Alameda", 1), AREA("Área", 2), AVENIDA("Av", 3), CONDOMINIO("Cond.", 4), CONJUNTO("Conj.", 5), DISTRITO("Dist.", 6),
	LADEIRA("Lad.", 7), PRAÇA("Pça", 8), RUA("Rua", 9), VIA("Via", 10);
	private int codigo;
    private String descricao;

    // construtor sempre private
    Logradouro(String descricao, int codigo) {
    	this.codigo = codigo;
        this.descricao = descricao;
    }      
    
    public static String getNome(int codigo){
    	for (Logradouro e : Logradouro.values()) {
    		if(e.getCodigo() == codigo){
    			return e.getDescricao();
    		}
    	}
    	return null;
    }
    
    public static int getCodigo(String descricao){
    	for (Logradouro e : Logradouro.values()) {
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