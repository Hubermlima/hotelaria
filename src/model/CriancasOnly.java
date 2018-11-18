package model;

public class CriancasOnly implements RegraNegocio {

	Integer adultos;
	
	@Override
	public boolean regra() {
		if (adultos > 0) {
			return true; // Regra de pelo menos 1 adulto no quarto satisfeito
		}
		return false; // Regra de pelo 1 adulto no quarto violada
	}
	

}
