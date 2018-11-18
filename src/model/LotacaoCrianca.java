package model;

public class LotacaoCrianca extends Lotacao {
	private Integer lotacaoMaxCrianca;
    private Integer lotacaoAtualCrianca;
    private Integer crianca;
    
    public LotacaoCrianca (Integer lotacaoMaxCrianca, Integer lotacaoAtualCrianca) {
		this.lotacaoMaxCrianca = lotacaoMaxCrianca;
		this.lotacaoAtualCrianca = lotacaoAtualCrianca;
	}
    
    @Override
	public boolean regra() {
    	if ((crianca + this.lotacaoAtualCrianca) <= this.lotacaoMaxCrianca) {
    		super.status = true;
			return true;
		}
    	super.status = false; // Regra não satisfeita (lotacao acima do limite) logo, retorna false
		return false;
		
	}
    
	@Override
	public void add(Integer crianca) {
		this.lotacaoAtualCrianca += crianca;
	}

	@Override
	public void remove(Integer crianca) {
		this.lotacaoAtualCrianca -= crianca;
	}
	@Override
	public Integer faltaAlocar() {
		return this.lotacaoMaxCrianca - this.lotacaoAtualCrianca;
	}

	public Integer getLotacaoAtualCrianca() {
		return lotacaoAtualCrianca;
	}

	public void setLotacaoAtualCrianca(Integer lotacaoAtualCrianca) {
		this.lotacaoAtualCrianca = lotacaoAtualCrianca;
	}

	public Integer getCrianca() {
		return crianca;
	}

	public void setCrianca(Integer crianca) {
		this.crianca = crianca;
	}

	public Integer getLotacaoMaxCrianca() {
		return lotacaoMaxCrianca;
	}

	public void setLotacaoMaxCrianca(Integer lotacaoMaxCrianca) {
		this.lotacaoMaxCrianca = lotacaoMaxCrianca;
	}
	

	
}
