package checkersnetbeams;

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
    public void setBackgroundColor(Color color)
    {
        this.backgroundColor = color;
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

    private Piece pieceSide1;
    public Piece getPieceSide1()
    {
        return pieceSide1;
    }
    public void setPieceSide1(Piece piece)
    {
        this.pieceSide1 = piece;
    }
    
    private Piece pieceSide2;
    public Piece getPieceSide2()
    {
        return pieceSide2;
    }
    public void setPieceSide2(Piece piece)
    {
        this.pieceSide2 = piece;
    }
    
//---------------------------------------------------------------------------------------------------------------------
//  CONSTRUCTORS
//--------------------------------------------------------------------------------------------------------------------- 
        
    public Theme() {}

    /**
     * partial constructor 
     * @param boardColor1
     * @param boardColor2
     * @param backgroundColor
     * @param foregroundColor
     */
    public Theme(Color boardColor1, Color boardColor2, Color backgroundColor, Color foregroundColor)
    {
        setBoardColor1(boardColor1);
        setBoardColor2(boardColor2);
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
    }


    public Theme(Color boardColor1, Color boardColor2, Color backgroundColor, Color foregroundColor, Piece pieceSide1, Piece pieceSide2)
    {
        setBoardColor1(boardColor1);
        setBoardColor2(boardColor2);
        setBackgroundColor(backgroundColor);
        setForegroundColor(foregroundColor);
        setPieceSide1(pieceSide1);
        setPieceSide2(pieceSide2);
    }
        
//---------------------------------------------------------------------------------------------------------------------
//  METHODS
//---------------------------------------------------------------------------------------------------------------------
    
    public void changeTheme(Theme theme)
    {
        theme.getBoardColor1();
        theme.getBoardColor2();
        theme.getBackgroundColor();
        theme.getForegroundColor();
        theme.getPieceSide1();
        theme.getPieceSide2();
    }



}
