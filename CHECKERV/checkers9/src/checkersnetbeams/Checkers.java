package checkersnetbeams;



//main class, calls GUI to start
import java.io.File;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class Checkers 
{
   
   
    public static void main(String[] args) throws Exception 
    {
        Platform.startup(() ->
        {
          
       });
        String bip = "Sound/CoolRevenge.mp3";
        Media hit = new Media(new File(bip).toURI().toString()); 
        MediaPlayer mediaPlayer = new MediaPlayer(hit); 
        mediaPlayer.play(); 
    }
}
        
        
 

