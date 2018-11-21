/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.model;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;

import movierecsys.bll.MRSLogicFacade;
import movierecsys.bll.MRSManager;
import movierecsys.bll.MRSdbLogicFacade;
import movierecsys.bll.MRSdbManager;

import movierecsys.bll.exception.MovieRecSysException;

/**
 *
 * @author pgn
 */
public class MovieModel
{

    private ObservableList<Movie> movies;

    private MRSLogicFacade logiclayer;
    private MRSdbLogicFacade logicdblayer;
    
    public MovieModel() throws MovieRecSysException, IOException
    {
        movies = FXCollections.observableArrayList();
        logiclayer = new MRSManager();
        logicdblayer=new MRSdbManager();
        movies.addAll(logiclayer.getAllMovies());
    }
    

    /**
     * Gets a reference to the observable list of Movies.
     * @return List of movies.
     */
    public ObservableList<Movie> getMovies()
    {
        return movies;
    }

    public void createMovie(int year, String title)
    {
        Movie movie = logiclayer.createMovie(year, title);
        Movie movie2 = logicdblayer.createMovie(year, title);
        movies.add(movie);
        
        
        
    }
    
    public void deleteMovie(Movie movie)
    {
        logiclayer.deleteMovie(movie);
        logicdblayer.deleteMovie(movie);
        movies.remove(movie);
    }
    public List<Movie> searchedMovies(String query){
        return logiclayer.searchMovies(query);
    }
    
    public List<Movie> searchedMoviesByYear(int theYear){
        return logiclayer.searchMoviesByYear(theYear);
    }
    
    public List<Movie> searchedWhole(int theYear,String query){
        return logiclayer.searchWhole(theYear, query);
    }
    
}
