package modele.Annulation;

import java.util.Date;

public abstract class Annulation {
	
	private Date date; 
	private boolean remboursementEffectue;
	public Annulation(Date date, boolean remboursementEffectue) {
		super();
		this.date = date;
		this.remboursementEffectue = remboursementEffectue;
	}
	public Date getDate() {
		return date;
	}
	public boolean isRemboursementEffectue() {
		return remboursementEffectue;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setRemboursementEffectue(boolean remboursementEffectue) {
		this.remboursementEffectue = remboursementEffectue;
	} 
	
	

}
