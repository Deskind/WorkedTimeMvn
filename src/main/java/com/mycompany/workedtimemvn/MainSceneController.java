package com.mycompany.workedtimemvn;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainSceneController implements Initializable {
    //Define fxml varialbles
    @FXML
    private Label label;
    @FXML
    private Button citiesButton;
    //Define handlers
    @FXML
    void handleCitiesButton(ActionEvent event) {
        InputStream is  = this.getClass().getResourceAsStream("cities.txt");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
