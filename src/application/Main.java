package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene mainScene;
	@Override
	public void start(Stage stage) throws IllegalArgumentException, IllegalAccessException {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/views/BuscaAdm.fxml"));
			mainScene = new Scene(parent);
			
			stage.setTitle("Search System");
			stage.setScene(mainScene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
