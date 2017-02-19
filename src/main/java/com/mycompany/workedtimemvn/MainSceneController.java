package com.mycompany.workedtimemvn;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.mycompany.workedtimemvn.entities.City;
import com.mycompany.workedtimemvn.entities.TimeUnit;
import com.mycompany.workedtimemvn.entities.UnitType;
import com.mycompany.workedtimemvn.utilities.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    private DatePicker date;

    @FXML
    private JFXDatePicker beginTime;

    @FXML
    private JFXDatePicker finishTime;
    
    @FXML
    private Button doneBtn;
    
    @FXML
    private Button tableBtn;
    
    @FXML
    private JFXCheckBox businessTripCheckBox;

    @FXML
    private JFXCheckBox onPlaceCheckBox;
    
    //Handlers
    @FXML
    void handleTableBtn(ActionEvent event) {
//        URL url = getClass().getClassLoader().getResource("AllEntries.fxml");
//        System.out.println("The url is : " + url.toString());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/AllEntries.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("All entries");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleDoneBtn(ActionEvent event) {
        //Get date
        LocalDate date_local = date.getValue();
        
        //Get begin time
        LocalTime bTime = beginTime.getTime();
        
        //Get finish time
        LocalTime fTime = finishTime.getTime();
        
        //Get result time
        int rTime = (fTime.toSecondOfDay()-bTime.toSecondOfDay());
        Double dResult = ((double)rTime)/60/60;
        
        //Make
        //Hibernate
        //connection
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        
        //Create an object 
        TimeUnit timeUnit = new TimeUnit();
        timeUnit.setDate(date_local.toString());
        timeUnit.setCity(cityChoiseBox.getValue());
        timeUnit.setBeginTime(bTime.toString());
        timeUnit.setFinishTime(fTime.toString());
        timeUnit.setResult(dResult.toString());
        
            //Get checkbox value
            if(businessTripCheckBox.isSelected()){
                timeUnit.setUnitType(UnitType.BUSINESS_TRIP);
            }else if(onPlaceCheckBox.isSelected()){
                timeUnit.setUnitType(UnitType.ON_PLACE);
            }
        
        //Save
        session.save(timeUnit);
        
        System.out.println("HUMAN SAVED IN DATABASE!!!");
        
        session.getTransaction().commit();
        
        sessionFactory.close();
    }

    @FXML
    void handleAddCityButton(ActionEvent event) {
        //Get value from text field
        String newCity = newCityTextField.getText();
        //End
        
        //Trying to write new city to database
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        
            //Make city object 
            City city = new City();
            city.setCityName(newCity);
            //End
            
            //Persist city
            session.save(city);
            session.getTransaction().commit();
            sessionFactory.close();
            //End
            
        //end
        
        //Add new city to choise box
        cityChoiseBox.getItems().add(newCity);
        //end
        
        //Make field and button unvisible
        newCityTextField.setVisible(false);
        addCityButton.setVisible(false);
        //end
        
        //Set new value to choicebox
        cityChoiseBox.setValue(newCity);
        //End

    }
    
    //Initialize method
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Add initial entry to list
        list.add("...NewCity...");
        //end

        //Read cities from database worktime/city
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<City> l = session.createCriteria(City.class).list();
        if(l.size()>0){
            for(City c : l){
                list.add(c.getCityName());
            }
        }
        session.close();
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
