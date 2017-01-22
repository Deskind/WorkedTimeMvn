package com.mycompany.workedtimemvn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class MainSceneController implements Initializable {
    //Instance variables
    ClassLoader loader = this.getClass().getClassLoader();
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
        
        String newCity = newCityTextField.getText();
        
        try {
            FileWriter out = new FileWriter("cities.txt");
            out.write("Hello!!!");
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        cityChoiseBox.getItems().add(newCity);
        
        newCityTextField.setVisible(false);
        addCityButton.setVisible(false);
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //file:/C:/Users/Desk1nd/Documents/NetBeansProjects/WorkedTimeMvn/target/classes/data/cities.txt
        
        

        //Read file cities.txt from resourses and the to list
        String s = null;
        InputStream is = loader.getResourceAsStream("data/cities.txt");
        try {
            s = IOUtils.toString(is, "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String [] cities = s.split(" ");
        for(int i = 0; i < cities.length; i++){
            list.add(cities[i]);
        }
        //End
        
        //Assign list to choise box
        cityChoiseBox.setItems(list);
        //End
        
        //Add event handler for getting ...NewCity... item
        cityChoiseBox.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(cityChoiseBox.getValue().equals("...NewCity...")){
                newCityTextField.setVisible(true);
                addCityButton.setVisible(true);
                }else{
                    newCityTextField.setVisible(false);
                    addCityButton.setVisible(false);
                }
            }
        });
        //End
    }    
}
