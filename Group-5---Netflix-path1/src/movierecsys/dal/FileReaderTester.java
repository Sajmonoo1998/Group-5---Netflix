/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.db.MovieDbDao;

/**
 *
 * @author pgn
 */
public class FileReaderTester
{

    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
       MovieDbDao mdb = new MovieDbDao();
       List<Movie> m = mdb.searchedMovies("red");
        for (Movie movie : m) {
            System.out.println(movie);
        }
    }
    
    public static void createRafFriendlyRatingsFile() throws IOException
    {
//        String target = "data/user_ratings";
//        RatingDAO ratingDao = new RatingDAO();
//        List<Rating> all = ratingDao.getAllRatings();
//        
//        try (RandomAccessFile raf = new RandomAccessFile(target, "rw"))
//        {
//            for (Rating rating : all)
//            {
//                raf.writeInt(rating.getMovie());
//                raf.writeInt(rating.getUser());
//                raf.writeInt(rating.getRating());
//            }
//        } catch (IOException ex)
//        {
//            ex.printStackTrace();
//        }
    }
    
}
