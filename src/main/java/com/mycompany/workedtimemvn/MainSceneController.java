package com.mycompany.workedtimemvn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    File cities = new File("cities.txt");
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
        //Get value from text field
        String newCity = newCityTextField.getText();
        //Trying to write new city to cities.txt
        try {
            FileUtils.writeStringToFile(cities, newCity+" ", true);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //end
        
        //Add new city to choise box
        cityChoiseBox.getItems().add(newCity);
        //end
        
        //Make field and button unvisible
        newCityTextField.setVisible(false);
        addCityButton.setVisible(false);
        //end
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Add initial entry to list
        list.add("...NewCity...");
        //end
        
        //Read file cities.txt from resourses and the to list if file exists
        try {
            String s = IOUtils.toString(new FileInputStream(cities));
            String[] arr = s.split(" ");
            for(int i = 0 ; i < arr.length; i++){
                list.add(arr[i]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //End
        
        //Assign list to choise box
        cityChoiseBox.setItems(list);
        //End
        
        //Add event handler for getting ...NewCity... item action
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
