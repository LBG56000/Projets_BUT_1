package vue;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Billet;
import javafx.fxml.FXMLLoader;

public class FenBillets extends Stage {
	CtrlBillets ctrl;
	public FenBillets() throws IOException {
		this.setTitle("Patio'Planner : Billets");
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
		this.setResizable(false);
	}

	private SplitPane creerSceneGraph() throws IOException {
     	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FenBillets.fxml"));
        SplitPane root = loader.load();
        ctrl = loader.getController();
     	return root;
	}

	public void clicBillet(ArrayList<Billet> lesBilletsTemp) {
		ctrl.afficheBillet(lesBilletsTemp);
		
	}
}
