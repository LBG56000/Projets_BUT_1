package vue;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import modele.Billet;
import modele.Reservation;

public class CtrlBillets {

    @FXML
    private Button bnFermer;

    @FXML
    private Button bnImprimer;

    @FXML
    private Label categorieBillet;

    @FXML
    private Label codeBillet;

    @FXML
    private Label dateBillet;

    @FXML
    private Label emplacementBillet;

    @FXML
    private Label heureBillet;

    @FXML
    private ListView<Billet> listeBillets;

    @FXML
    private Label nomGroupeBillet;

    @FXML
    private Label ouverturePortesBillet;

    @FXML
    private ImageView photoBillet;

    @FXML
    private Label placeBillet;

    @FXML
    private Label prixBillet;

    @FXML
    private Label rangBillet;

    static private ObservableList<Billet> 	lesBillets = FXCollections.observableArrayList();
    
    @FXML
    void clicFermer(ActionEvent event) {
        controleur.Main.fermerBillets();
    }

    @FXML
    void clicImprimer(ActionEvent event) {
        System.out.println("Impression des billets OK...");
    }

	public void afficheBillet(ArrayList<Billet> lesBilletsTemp) {
		for(Billet billet : lesBilletsTemp) {
			lesBillets.add(billet);
		}
		
		listeBillets.setItems(lesBillets);
		
	}
	
	public void initialize() {
		listeBillets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Réagissez à l'événement de sélection
                placeBillet.setText(newValue.getMonFauteuil().getNumero());
                rangBillet.setText(newValue.getMonFauteuil().getRangee());
            }
            
            photoBillet.setId("/photo_RS.png");
        });
		
	}

}
