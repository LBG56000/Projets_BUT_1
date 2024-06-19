package modele.Tarification;

import java.util.Objects;

public class Senior extends Tarification{
	
	private static double reduction; 
	
	public Senior(double reduction) {
		super();
		this.reduction = reduction;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Senior senior = (Senior) o;
		return Double.compare(getReduction(), senior.getReduction()) == 0;
	}

	public String toString() {
		return "Senior{" +
				"reduction=" + reduction +
				'}';
	}

	public void affiche(){
		System.out.println("Tarif : " + this.getReduction());
	}

	public double getReduction() {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	
	

}
