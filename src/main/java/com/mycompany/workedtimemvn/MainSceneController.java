package com.mycompany.workedtimemvn;

import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MainSceneController implements Initializable {
    //Instance variables
    
    //Class variables
    static ObservableList<String> list = FXCollections.observableArrayList();
    
    @FXML
    private ChoiceBox<String> cityChoiseBox;

    @FXML
    private TextField newCityTextField;

    @FXML
    private Button addCityButton;

    @FXML
    void handleAddCityButton(ActionEvent event) {
        
        if(cityChoiseBox.getValue().equals("...New City...")){
            newCityTextField.setVisible(true);
            addCityButton.setVisible(true);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Read file cities.txt from resourses and the to list
        String s = "";
        StringBuilder sb = new StringBuilder();
        ClassLoader loader = this.getClass().getClassLoader();
        try {
            File file = new File(loader.getResource("data/cities.txt").toURI());
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                list.add(line);
            }
            scanner.close();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        s = sb.toString();
        //End
        
        //Assign list to choise box
        cityChoiseBox.setItems(list);
        //End
    }    
}
