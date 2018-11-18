package model;

public abstract class Lotacao implements RegraNegocio {
    
    protected boolean status;

	public abstract void add(Integer numero);
	public abstract void remove(Integer numero);
	public abstract Integer faltaAlocar();
    
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
