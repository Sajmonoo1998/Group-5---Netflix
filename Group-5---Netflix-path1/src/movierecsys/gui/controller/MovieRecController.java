/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import movierecsys.be.Movie;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.gui.model.MovieModel;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable
{


    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;

    private MovieModel movieModel;
    @FXML
    private TextField query;
    private ObservableList<Movie> searchedAsObservable;
   
    @FXML
    private ComboBox<String> year;
    
    
   
    public MovieRecController() throws IOException
    {
        
        try
        {
            movieModel = new MovieModel();
        } catch (MovieRecSysException ex)
        {
            displayError(ex);
            System.exit(0);
        } 
        
       
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        year.getItems().add("Year");
        lstMovies.setItems(movieModel.getMovies());
        for (int i = 2018; i > 1900; i--) {
            year.getItems().add(""+ i);
       }
    }

    /**
     * Displays errormessages to the user.
     * @param ex The Exception
     */
    private void displayError(Exception ex)
    {
        //TODO Display error properly
        System.out.println(ex.getMessage());
        ex.printStackTrace();
    }

    @FXML
    private void searchButton(ActionEvent event) {
        
      if(!(query.getText().equals(null)) && year.getValue()==null || year.getValue().equals("Year")){
        searchedAsObservable = FXCollections.observableArrayList(movieModel.searchedMovies(query.getText()));
        lstMovies.setItems(searchedAsObservable);}
        else if ( query.getText().equals("") && year.getValue()!=null){
        searchedAsObservable = FXCollections.observableArrayList(movieModel.searchedMoviesByYear(Integer.parseInt(year.getValue())));
        lstMovies.setItems(searchedAsObservable);
        }
      
        else if((!query.getText().equals(null)) && year.getValue()!=null){
        searchedAsObservable = FXCollections.observableArrayList(movieModel.searchedWhole(Integer.parseInt(year.getValue()), query.getText()));
        lstMovies.setItems(searchedAsObservable);
        }
      
    }

    @FXML
    private void add(ActionEvent event) {
    if(query.getText()!=null && year!=null){
   movieModel.createMovie(Integer.parseInt(year.getValue()), query.getText());
        
    }
   
    }
    @FXML
    private void delete(ActionEvent event) {
        Movie id = lstMovies.getSelectionModel().getSelectedItem();
        movieModel.deleteMovie(id);
        
        }

   

    
    

}
