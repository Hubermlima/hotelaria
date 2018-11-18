package model;

public class LotacaoAdulto extends Lotacao {
	private Integer lotacaoMaxAdulto;
    private Integer lotacaoAtualAdulto;
	private Integer adulto;
	
	public LotacaoAdulto (Integer lotacaoMaxAdulto, Integer lotacaoAtualAdulto) {
		this.lotacaoMaxAdulto = lotacaoMaxAdulto;
		this.lotacaoAtualAdulto = lotacaoAtualAdulto;
	}
	
    @Override
	public boolean regra() {
    	if ((adulto + this.lotacaoAtualAdulto) <= this.lotacaoMaxAdulto) {
    		super.status = true;
			return true; // Regra satisfeita (lotacao abaixo do limite) logo, retorna true
		}
    	super.status = false; 
		return false;    // Regra não satisfeita (lotacao acima do limite) logo, retorna false
		
	}

	@Override
	public void add(Integer adulto) {
		this.lotacaoAtualAdulto += adulto;
	}
	@Override
	public void remove(Integer adulto) {
		this.lotacaoAtualAdulto -= adulto;
	}
	
	public Integer getLotacaoAtualAdulto() {
		return this.lotacaoAtualAdulto;
	}
	
	@Override
	public Integer faltaAlocar() {
		return this.lotacaoMaxAdulto - this.lotacaoAtualAdulto;
	}


	public void setLotacaoAtualAdulto(Integer lotacaoAtualAdulto) {
		this.lotacaoAtualAdulto = lotacaoAtualAdulto;
	}

	public Integer getAdulto() {
		return adulto;
	}

	public void setAdulto(Integer adulto) {
		this.adulto = adulto;
	}

	public Integer getLotacaoMaxAdulto() {
		return lotacaoMaxAdulto;
	}

	public void setLotacaoMaxAdulto(Integer lotacaoMaxAdulto) {
		this.lotacaoMaxAdulto = lotacaoMaxAdulto;
	}


	

}
