package vue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import controleur.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modele.Billet;

public class CtrlInfosResa {

    @FXML
    private Button bnBillets;

    @FXML
    private Button bnFermer;

    @FXML
    private Button bnImprimer;

    @FXML
    private Label dateConfirmation;

    @FXML
    private Label dateReservation;

    @FXML
    private Label gammePrix;

    @FXML
    private Label groupe;

    @FXML
    private Label heure;

    @FXML
    private Label idClient;

    @FXML
    private Label modePaiement;

    @FXML
    private Label nbPlaces;

    @FXML
    private Label nomClient;

    @FXML
    private Label numFacture;

    @FXML
    private Label numFauteils;

    @FXML
    private Label numResa;

    @FXML
    private Label prenomClient;

    @FXML
    private Label prix;

    @FXML
    private Label zoneResa;
    
    private ArrayList<Billet>lesBilletsTemp = new ArrayList<Billet>();

    @FXML
    void clicBillets(ActionEvent event) {
    	Main.clicBillet(lesBilletsTemp);
    }

    @FXML
    void clicFermer(ActionEvent event) {
        controleur.Main.fermerInfos();
    }

    @FXML
    void clicImprimer(ActionEvent event) {
        System.out.println("Impression OK...");
    }

    
    public void afficherBillet(String numCli,String prenom,String nom,String numReser,ArrayList<Billet>lesBillets,String date,String dateConf, String heureSpec) {
    	idClient.setText(numCli);
    	prenomClient.setText(prenom);
    	numResa.setText(numReser);
    	Set<String>lesFauteuil = new HashSet<String>(); 
    	for(Billet billet : lesBillets) {
    		String fauteuil = ""; 
    		fauteuil = String.valueOf(billet.getMonFauteuil().getNumero()+billet.getMonFauteuil().getRangee());
    		lesFauteuil.add(fauteuil);
    	}
    	
    	String fauteuilsText = lesFauteuil.stream().collect(Collectors.joining(", "));
    	numFauteils.setText(fauteuilsText);
    	dateReservation.setText(date);
    	dateConfirmation.setText(dateConf);
    	nomClient.setText(nom);
    	nbPlaces.setText(lesBillets.size()+"");
    	String lesZones = ""; 
    	lesZones = lesBillets.get(0).getMonFauteuil().getMaZone().getNom();
    	
    	zoneResa.setText(lesZones);
    	
    	heure.setText(heureSpec);
    	
    	lesBilletsTemp = lesBillets;
    	
    	
    	
    }
    
    

}
