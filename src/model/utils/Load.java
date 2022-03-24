package model.utils;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Load {
	
	public synchronized void loadview(String absoluteName) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) mainScene.getRoot();

		
			mainVBox.getChildren().clear();
			mainVBox.getChildren().addAll(newVbox.getChildren());

		} catch (IOException e) {

		}
	}
}
