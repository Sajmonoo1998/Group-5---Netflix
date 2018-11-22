/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import movierecsys.dal.db.MovieDbDao;
import movierecsys.gui.controller.MovieRecController;

/**
 *
 * @author pgn
 */
public final class OwsMain extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/movierecsys/gui/view/MovieRecView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        Stage second = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/movierecsys/gui/view/MainMenuView.fxml"));
        Scene scene2 = new Scene(root2);
        second.setScene(scene2);
        second.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
       
        
         launch(args);
    }

}
