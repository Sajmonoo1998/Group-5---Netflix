/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.DalController;
import movierecsys.dal.exception.MrsDalException;

/**
 *
 * @author Szymon
 */
public class MRSdbManager implements MRSdbLogicFacade{
    DalController dalcontroller;
    public MRSdbManager() throws IOException{
    dalcontroller =new DalController();
    }

    @Override
    public Movie createMovie(int releaseYear, String title) {
        Movie m=null;
        try {
            m=dalcontroller.createMovie(releaseYear, title);
        } catch (MrsDalException ex) {
            Logger.getLogger(MRSdbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    @Override
    public void deleteMovie(Movie movie) {
        try {
            dalcontroller.deleteMovie(movie);
        } catch (MrsDalException ex) {
            Logger.getLogger(MRSdbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> m=null;
        try {
            m=dalcontroller.getAllMovies();
        } catch (MrsDalException ex) {
            Logger.getLogger(MRSdbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    @Override
    public Movie getMovie(int id) {
        try {
            return dalcontroller.getMovie(id);
        } catch (MrsDalException ex) {
            Logger.getLogger(MRSdbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void updateMovie(Movie movie) {
        try {
            dalcontroller.updateMovie(movie);
        } catch (MrsDalException ex) {
            Logger.getLogger(MRSdbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createRating(Rating rating) {
        dalcontroller.createRating(rating);
    }

    @Override
    public void deleteRating(Rating rating) {
        dalcontroller.deleteRating(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        List<Rating> r=null;
        try {
            r=dalcontroller.getAllRatings();
        } catch (MrsDalException ex) {
            Logger.getLogger(MRSdbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public List<Rating> getRatings(User user) {
        List<Rating> r;
        r=dalcontroller.getRatings(user);
        return r;
    }

    @Override
    public void updateRating(Rating rating) {
        try {
            dalcontroller.updateRating(rating);
        } catch (MrsDalException ex) {
            Logger.getLogger(MRSdbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allusers;
        allusers=dalcontroller.getAllUsers();
        return allusers;
    }

    @Override
    public User getUser(int id) {
     return   dalcontroller.getUser(id);
        
    }

    @Override
    public void updateUser(User user) {
        dalcontroller.updateUser(user);
    }

    @Override
    public void createUser(String name) {
dalcontroller.createUser(name);
    }

    @Override
    public List<Movie> searchedMovies(String query) {
        List<Movie> allmovies;
        allmovies=dalcontroller.searchedMovies(query);
        return allmovies;
    }
    @Override
    public List<Movie> searchByYear(int year) {
        List<Movie> allmovies;
        allmovies=dalcontroller.searchByYear(year);
        return allmovies;
    }
    @Override
    public List<Movie> searchWhole(int year,String query) {
        List<Movie> allmovies;
        allmovies=dalcontroller.searchWhole(year, query);
        return allmovies;
    }
    
}
