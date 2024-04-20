
import javax.swing.*;


public class Piece extends JLabel {


    String side = "";

    boolean kinged;

    public boolean getKinged() 
    {
        return this.kinged;
    }

    public void setKinged(boolean kinged) 
    {
        this.kinged = kinged;
    }

    public String getSide() {
		return this.side;
	}
    public void setSide(String side)
    {
		this.side = side;
	}

    public void setPlayer1(String image)
    {

        String imageFile = image;
        ImageIcon player1Image = new ImageIcon(imageFile);
        setIcon(player1Image);
        repaint();
    }

    public void setPLayer2(String image)
    {
        String imageFile = image;
        ImageIcon player2Image = new ImageIcon(imageFile);
        setIcon(player2Image);
        repaint();
    }

    public Piece(String side) 
    {
       
        this.side = side;
        kinged = false;
    }
    
    public Piece() {
       
    }
}