import java.awt.Color;

public class Theme {
    
//---------------------------------------------------------------------------------------------------------------------
//  PROPERTIES
//---------------------------------------------------------------------------------------------------------------------    
   
    
    public Color boardcolor1;


    public Theme theme;
    public void setTheme(Theme theme)
    {
        this.theme = theme;
    }

    public Theme getTheme()
    {
        return theme;
    }

    public Color getBoardColor1()
    {
        return boardcolor1;
    }
    public void setBoardColor1(Color color)
    {
        this.boardcolor1 = color;
    }

    public Color boardcolor2;
    public Color getBoardColor2()
    {
        return boardcolor2;
    }
    public void setBoardColor2(Color color)
    {
        this.boardcolor2 = color;
    }

    public Color backgroundColor;
    public Color getBackgroundColor()
    {
        return backgroundColor;
    }
    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public Color foregroundColor;
    public Color getForegroundColor()
    {
        return foregroundColor;
    }
    public void setForegroundColor(Color color)
    {
        this.foregroundColor = color;
    }


    public Color backgroundButtonColor;
    public Color getBackgroundButtonColor()
    {
        return backgroundButtonColor;
    }
    public void setBackgroundButtonColor(Color backgroundColor)
    {
        this.backgroundButtonColor = backgroundColor;
    }

    public Color foregroundButtonColor;
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
         
        System.out.println("Theme image1: " + player1Image);
    }
    public String getPlayer1Image()
    {
        return player1Image;
    }

    public String player2Image = "";
    //to set an image for a piece......just change player1Image hopefully......?
    public void setPlayer2Image(String image)
    {
        player2Image = image;;

        System.out.println("Theme image2: " + player2Image);
    }
    public String getPlayer2Image()
    {
        return player2Image;
    }



    ///////

    public String player1ImageKing = "";
    public void setPlayer1ImageKing(String image)
    {
        player1ImageKing = image;
         
        System.out.println("Theme image1King: " + player1ImageKing);
    }
    public String getPlayer1ImageKing()
    {
        return player1ImageKing;
    }

    public String player2ImageKing = "";
    //to set an image for a piece......just change player1Image hopefully......?
    public void setPlayer2ImageKing(String image)
    {
        player2ImageKing = image;;
        System.out.println("Theme image2King: " + player2ImageKing);
    }
    public String getPlayer2ImageKing()
    {
        return player2ImageKing;
    }


//---------------------------------------------------------------------------------------------------------------------
//  CONSTRUCTORS
//--------------------------------------------------------------------------------------------------------------------- 
        
    public Theme() {} 



    public Theme(Color foregroundcolor, Color backgroundcolor, Color foregroundbuttoncolor, Color backgroundbuttoncolor, String piece1, String piece2, String king1, String king2 ) 
    {
        setForegroundColor(foregroundcolor);
        setBackgroundColor(backgroundcolor);
        setForegroundButtonColor(foregroundbuttoncolor);
        setBackgroundButtonColor(backgroundbuttoncolor);
        setPlayer1Image(piece1);
        setPlayer2Image(piece2);
        setPlayer1ImageKing(king1);
        setPlayer2ImageKing(king2);
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