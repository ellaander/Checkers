import java.awt.Color;

public class Theme {
    
//---------------------------------------------------------------------------------------------------------------------
//  PROPERTIES
//---------------------------------------------------------------------------------------------------------------------    
   
    private Color boardcolor1;
    public Color getBoardColor1()
    {
        return boardcolor1;
    }
    public void setBoardColor1(Color color)
    {
        this.boardcolor1 = color;
    }

    private Color boardcolor2;
    public Color getBoardColor2()
    {
        return boardcolor2;
    }
    public void setBoardColor2(Color color)
    {
        this.boardcolor2 = color;
    }

    private Color backgroundColor;
    public Color getBackgroundColor()
    {
        return backgroundColor;
    }
    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    private Color foregroundColor;
    public Color getForegroundColor()
    {
        return foregroundColor;
    }
    public void setForegroundColor(Color color)
    {
        this.foregroundColor = color;
    }


    private Color backgroundButtonColor;
    public Color getBackgroundButtonColor()
    {
        return backgroundButtonColor;
    }
    public void setBackgroundButtonColor(Color backgroundColor)
    {
        this.backgroundButtonColor = backgroundColor;
    }

    private Color foregroundButtonColor;
    public Color getForegroundButtonColor()
    {
        return foregroundButtonColor;
    }
    public void setForegroundButtonColor(Color color)
    {
        this.foregroundButtonColor = color;
    }

    public String player1Image = "";
    //to set an image for a piece......just change player1Image hopefully......?
    public void setPlayer1Image(String image)
    {
        player1Image = image;
    }
    public String getPlayer1Image()
    {
        return player1Image;
    }

    public String player2Image = "";
    //to set an image for a piece......just change player1Image hopefully......?
    public void setPlayer2Image(String image)
    {
        player2Image = image;
    }
    public String getPlayer2Image()
    {
        return player2Image;
    }


//---------------------------------------------------------------------------------------------------------------------
//  CONSTRUCTORS
//--------------------------------------------------------------------------------------------------------------------- 
        
    public Theme() {}


    public Theme(Color foregroundcolor, Color backgroundcolor, Color foregroundbuttoncolor, Color backgroundbuttoncolor, String piece1, String piece2) 
    {
        setForegroundColor(foregroundcolor);
        setBackgroundColor(backgroundcolor);
        setForegroundButtonColor(foregroundbuttoncolor);
        setBackgroundButtonColor(backgroundbuttoncolor);
        setPlayer1Image(piece1);
        setPlayer2Image(piece2);
    }

    /**
     * partial constructor 
     * @param boardColor1
     * @param boardColor2
     * @param backgroundColor
     * @param foregroundColor
     */
    public Theme(Color boardColor1, Color boardColor2, Color backgroundColor, Color foregroundColor, String image)
    {
        setBoardColor1(boardColor1);
        setBoardColor2(boardColor2);
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        setPlayer1Image(image);
    }
        
//---------------------------------------------------------------------------------------------------------------------
//  METHODS
//---------------------------------------------------------------------------------------------------------------------
    
}
