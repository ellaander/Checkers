package checkersnetbeams;

import javax.swing.*;
import java.awt.*;

public class Piece extends JLabel {
    String side = "";

    public String getSide() {
		return this.side;
	}

    public void setSide(String side)
    {
		this.side = side;
	}

    private ImageIcon pieceIcon;

    public Piece(String side) {
       
        pieceIcon = new ImageIcon("Checkers.png");

        // Set the icon for the JLabel
        setIcon(pieceIcon);


        // Set the size of the JLabel to match the size of the image
        setPreferredSize(new Dimension(pieceIcon.getIconWidth(), pieceIcon.getIconHeight()));
    }
}