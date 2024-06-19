package vue;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Billet;
import javafx.fxml.FXMLLoader;

public class FenInfosResa extends Stage {
	CtrlInfosResa ctrl;
	public FenInfosResa() throws IOException {
		this.setTitle("Patio'Planner : Réservation");
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
		this.setResizable(false);
	}

	private AnchorPane creerSceneGraph() throws IOException {
     	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FenAffichageResa.fxml"));
        AnchorPane root = loader.load();
        ctrl = loader.getController();
//        ctrl.afficherBillet(getFullScreenExitHint(), getTitle(), getFullScreenExitHint())
     	return root;
	}
	
	public void clicInfo(String numCli,String prenom,String nom,String numReser,ArrayList<Billet>lesBillets,String date,String dateConf, String heure) {
		ctrl.afficherBillet(numCli, prenom,nom, numReser,lesBillets,date,dateConf,heure);
	}
}
