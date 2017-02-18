/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.workedtimemvn;

import com.mycompany.workedtimemvn.entities.TimeUnit;
import com.mycompany.workedtimemvn.utilities.HibernateUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author Desk1nd
 */
public class AllEntriesController implements Initializable {

    //Fields
    private ObservableList<TimeUnit> timeUnitsList = FXCollections.observableArrayList();
 
    @FXML
    private TableView<TimeUnit> timeUnitTable;
    
    @FXML
    private TableColumn<TimeUnit, Long> id;
 
    @FXML
    private TableColumn<TimeUnit, String> date;
 
    @FXML
    private TableColumn<TimeUnit, String> city;
 
    @FXML
    private TableColumn<TimeUnit, String> beginTime;
 
    @FXML
    private TableColumn<TimeUnit, String> finishTime;
    
    @FXML
    private TableColumn<TimeUnit, String> result;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Cell properties
        id.setCellValueFactory(new PropertyValueFactory<TimeUnit, Long>("id"));
        date.setCellValueFactory(new PropertyValueFactory<TimeUnit, String>("date"));
        city.setCellValueFactory(new PropertyValueFactory<TimeUnit, String>("city"));
        beginTime.setCellValueFactory(new PropertyValueFactory<TimeUnit, String>("beginTime"));
        finishTime.setCellValueFactory(new PropertyValueFactory<TimeUnit, String>("finishTime"));
        result.setCellValueFactory(new PropertyValueFactory<TimeUnit, String>("result"));
        
        //Connect to database with hibernate and get data as collestion of TimeUnits
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<TimeUnit> list = session.createCriteria(TimeUnit.class).list();
        timeUnitsList.addAll(list);
        session.close();
        
        //Set table items
        timeUnitTable.setItems(timeUnitsList);
    }    
    
}
