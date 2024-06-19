package modele.Paiement;

public abstract class Paiement {
	
	private boolean realise;
	
	public Paiement(boolean realise) {
		super();
		this.realise = realise;
	}

	public boolean isRealise() {
		return realise;
	}

	public void setRealise(boolean realise) {
		this.realise = realise;
	} 
	
	

}
