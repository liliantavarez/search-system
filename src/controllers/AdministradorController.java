package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.utils.Load;

public class AdministradorController {

    @FXML
    private Button btBuscMeliante;

    @FXML
    private Button btBuscUsuario;

    @FXML
    private Button btCadMeliante;

    @FXML
    private Button btCadUsuario;

    @FXML
    private Button btSair;
    
	Load lv = new Load();


    @FXML
    void onBtBuscMelianteAction(ActionEvent event) {
    	lv.loadview("/views/BuscaAdm.fxml");
    }

    @FXML
    void onBtBuscUsuarioAction(ActionEvent event) {
    	lv.loadview("/views/BuscaUsuario.fxml");
    }

    @FXML
    void onBtCadMelianteAction(ActionEvent event) {
    	lv.loadview("/views/CadMeliante.fxml");
    }

    @FXML
    void onBtCadUsuarioAction(ActionEvent event) {
    	lv.loadview("/views/CadUsuario.fxml");
    }

    @FXML
    void onBtSairAction(ActionEvent event) {
    	lv.loadview("/views/Login.fxml");
    }

}
