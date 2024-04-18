package checkersnetbeams;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Piece extends JLabel {

    File Kernal = new File("Image/kernal2.0.png"); 
    File Niblet = new File("Image/niblet2.0.png");
    String side = "";
    
    
    
    ImageIcon pieceRed = new ImageIcon(Kernal.getPath());
    ImageIcon pieceBlack = new ImageIcon(Niblet.getPath());
    boolean kinged;

    public boolean getKinged() 
    {
        return this.kinged;
    }

    public void setKinged(boolean kinged) {
        this.kinged = kinged;
    }

    public String getSide() {
		return this.side;
	}
    public void setSide(String side)
    {
		this.side = side;
	}

    public void setBLACK(){
        setIcon(pieceBlack);
        repaint();

        // Set the size of the JLabel to match the size of the image
        setPreferredSize(new Dimension(pieceBlack.getIconWidth(), pieceBlack.getIconHeight()));

    }

    public void setRED(){
        setIcon(pieceRed);
        repaint();
        // Set the size of the JLabel to match the size of the image
    }

    public Piece(String side) {
       

        this.side = side;
        kinged = false;

    }
}