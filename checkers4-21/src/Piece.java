
import javax.swing.*;
import javax.swing.ImageIcon;



public class Piece extends JLabel {


    String side = "";
    String imageFile;
    private ImageIcon icon;

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

}