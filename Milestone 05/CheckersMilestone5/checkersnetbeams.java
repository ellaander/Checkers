package checkersnetbeams;

import java.io.File;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.SwingUtilities;

//main class, calls GUI to start

public class checkersnetbeams
{
   
   
    public static void main(String[] args) throws Exception 
    {
        new GUI(); 
        
       
        // Initialize JavaFX Toolkit
        Platform.startup(() -> {
            // You can put initialization code here if needed
        });

        // Launch the Swing application
        SwingUtilities.invokeLater(() -> {
            // Create and show your Swing GUI here
            new GUI();
        });
    }
        
        
    }

