package modele;

import modele.Client.Client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class Reservation {
//    private String numero;
//    private Date date;
//    private Date dateEnvoieConf;
//    private Client client;
//    private ArrayList<Billet> mesBillets = new ArrayList<>();
//    private ArrayList<Representation> mesRepresentations = new ArrayList<>();
    
    private final StringProperty num;
    private final StringProperty date;
    private final StringProperty dateEnvoieConf; 
    private final Client client; 
    private final ObservableList<Billet> mesBillets = FXCollections.observableArrayList();
	private final ObservableList<Representation>mesRepresentations = FXCollections.observableArrayList();
    

    public Reservation(String numero, String date, String dateEnvoieConf, Client cli, Representation r1) {
        this.num = new SimpleStringProperty(numero);
        this.date = new SimpleStringProperty(date);
        this.dateEnvoieConf = new SimpleStringProperty(dateEnvoieConf);
        this.client = cli;
        this.mesRepresentations.add(r1);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "numero='" + num + '\'' +
                ", date=" + date +
                ", dateEnvoieConf=" + dateEnvoieConf +
                ", client=" + client +
                ", mesBillets=" + mesBillets +
                ", mesRepresentations=" + mesRepresentations +
                '}';
    }



    @Override
	public int hashCode() {
		return Objects.hash(num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(num, other.num);
	}

	public void affiche(){
        System.out.println("Numero : " + this.num + " Date : " + this.date + " Date Confirmation : " + this.dateEnvoieConf + " Date Confirmation : " + this.client);

        if (!this.mesRepresentations.isEmpty()){
            for (Representation r : mesRepresentations) {
                r.affiche();
            }
        }

        if (!this.mesBillets.isEmpty()){
            int i = 1;
            for (Billet billet : mesBillets) {
                System.out.println("Billet n°" + i);
                billet.affiche();
                i++;
            }
        }
    }

    public void ajouterRepresentation(Representation representation){
        if (!representation.equals(null) && !this.mesRepresentations.contains(representation)){
            affecterRepresentation(representation);
        }
    }

    public void supprimerRepresentation(Representation representation){
        if (!representation.equals(null) && this.mesRepresentations.contains(representation)){
            enleverRepresentation(representation);
        }
    }

    public void modifierRepresentation(Representation representation1, Representation representation2){
        if (!representation2.equals(null) && mesBillets.contains(representation1)){
            for (Representation r : this.mesRepresentations) {
                if (r == representation1){
                    changerRepresentation(r, representation2);
                }
            }
        }
    }

    protected void affecterRepresentation(Representation representation){
        this.mesRepresentations.add(representation);
    }

    protected void enleverRepresentation(Representation representation){
        this.mesRepresentations.remove(representation);
    }

    protected void changerRepresentation(Representation representation1, Representation representation2){
        representation1 = representation2;
    }

    public void ajouterPlacement(Billet billet){
        if (!billet.equals(null) && !mesBillets.contains(billet)){
            affecterBillet(billet);
        }
    }

    public void retirerPlacement(Billet billet){
        if (!billet.equals(null) && mesBillets.contains(billet)){
            retirerBillet(billet);
        }
    }

    public void modifierPlacement(Billet billet1, Billet billet2){
        if (!billet2.equals(null) && mesBillets.contains(billet1)){
            for (Billet b : mesBillets) {
                if (b == billet1){
                    changerBillet(b, billet2);
                }
            }
        }
    }

    protected void affecterBillet(Billet billet){
        this.mesBillets.add(billet);
    }

    protected void retirerBillet(Billet billet){
        this.mesBillets.remove(billet);
    }

    protected void changerBillet(Billet billet1, Billet billet2){
        billet1 = billet2;
    }
    
    public String getNumero() {
        return num.get();
    }

    public StringProperty numeroProperty() {
        return num;
    }

    public Client getClient() {
        return client;
    }

    public String getDate() {
        return date.get();
    }


    public StringProperty dateProperty() {
        return date;
    }

    public String getConfDate() {
        return dateEnvoieConf.get();
    }


    public StringProperty confDateProperty() {
        return dateEnvoieConf;
    }

    public String getBillet() {
        return mesBillets.toString();
    }
    
    public StringProperty getNum() {
		return num;
	}

	public StringProperty getDateEnvoieConf() {
		return dateEnvoieConf;
	}

	public ObservableList<Billet> getMesBillets() {
		return mesBillets;
	}

	public ObservableList<Representation> getMesRepresentations() {
		return mesRepresentations;
	}


//
//    public String getRepresentation() {
//        return representation.;
//    }
//
//    public void setRepresentation(String representation) {
//        this.representation.set(representation);
//    }
//
//    public StringProperty representationProperty() {
//        return representation;
//    }

	


}
