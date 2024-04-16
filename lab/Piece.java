package javaapplication11;

import javax.swing.*;
import java.awt.*;
import java.io.File;




public class Piece extends JLabel {

    File asdf = new File("img/kernal2.0.png");
    
    
    String side = "";
    ImageIcon pieceRed = new ImageIcon(asdf.getPath());
    ImageIcon pieceBlack = new ImageIcon(asdf.getPath());
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
        
         // Set the size of the JLabel to match the size of the image
        setPreferredSize(new Dimension(pieceBlack.getIconWidth(), pieceBlack.getIconHeight()));
        
        repaint();

       

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