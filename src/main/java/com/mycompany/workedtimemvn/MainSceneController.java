package com.mycompany.workedtimemvn;

import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        String newCityRc = "\n"+newCity;
        String path = loader.getResource("data/cities.txt").getPath().substring(1);
        
        //Add city to cities.txt file
        try {
            Files.write(Paths.get(path), newCityRc.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("No such file!!!!!!!!!");
        }
        //End
        
        cityChoiseBox.getItems().add(newCity);
        
        newCityTextField.setVisible(false);
        addCityButton.setVisible(false);
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //file:/C:/Users/Desk1nd/Documents/NetBeansProjects/WorkedTimeMvn/target/classes/data/cities.txt
        
        

        //Read file cities.txt from resourses and the to list
        String s = "";
        StringBuilder sb = new StringBuilder();
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
        
        //Add event handler for getting ...NewCity... item
        cityChoiseBox.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(cityChoiseBox.getValue().equals("...New City...")){
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
