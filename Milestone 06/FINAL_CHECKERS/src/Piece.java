
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;



public class Piece extends JLabel {


    String side = "";
    String imageFile;
    private ImageIcon icon;
    private static final String DING_SOUND_PATH = "ding.mp3"; 
    private static final File dingFile = new File(DING_SOUND_PATH);

    boolean kinged;

    public boolean getKinged() 
    {
        return this.kinged;
    }

    public void setKingedPlayer1(boolean kinged, String image) 
    {
        this.kinged = kinged;
        ImageIcon player1KingImage = new ImageIcon(image);
        setIcon(player1KingImage);
        
    }
    public void setKingedPlayer2(boolean kinged, String image)
    {
        this.kinged = kinged;
        ImageIcon player2KingImage = new ImageIcon(image);
        setIcon(player2KingImage);
    }

    public String getSide() 
    {
		return this.side;
	}

    public void setSide(String side)
    {
		this.side = side;
	}


    public String returnImageFile()
    {
        return imageFile;
    }

    public Piece(String side) 
    {
       
        this.side = side;
        kinged = false;
    }
    
    public Piece() {
       
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void playDingSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(dingFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to handle when the piece gets jumped over
    public void onJumpedOver() {
        playDingSound();
    }

}