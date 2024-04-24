
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Board 
{
   // https://www.tabnine.com/code/java/class-index

    //PLAYER 1 AT TOP PLAYER 2 AT BOTTOM
    Theme theme = new Theme();


    private JFrame frame = new JFrame();

    //THEMES 
    private Color COLOR_1 = new Color(250, 250, 250);
    private Color COLOR_2 = new Color(0, 0, 0);
    private Color HIGHLIGHT_COLOR = new Color(255, 255, 0);
    public Color backgroundColor = new Color (34,139,34);
    public Color buttonColor = Color.GRAY;
    public Color borderColor = new Color(0,0,0);
    public Color fontColor = new Color(0,0,0);

    int rowit; //debugging
    int colit;

    ImageIcon backButtonImage = new ImageIcon("homeImage.png");

    String player1Image = theme.getPlayer1Image();
    String player2Image = "frog2.png";
    ImageIcon player1ImageLabel = new ImageIcon(player1Image); 
    ImageIcon player2ImageLabel = new ImageIcon(player2Image); 


    String kingImage1 = theme.getPlayer1Image();
    String kingImage2 = "frog2King.png";
    String winner;

    boolean jumpSide1;
    boolean jumpSide2;
    private boolean computerPlaysRed = false;
    Boolean gameOver = false;

    private JLabel turnLabel;
    private JLabel Player1Score;
    private JLabel Player2Score;

    private int player1ScoreInt = 0; //red score
    private int player2ScoreInt = 0; //black score
    private int player1PiecesRemaining = 12;
    private int player2PiecesRemaining= 12;
    private int jumpedPieceLength;
    private int turnCounter = 1;

    private ArrayList<int[]> possibleMoves = new ArrayList<>(); 
    private Hashtable<String, ArrayList<int[]>> jumpedPiecesHash = new Hashtable<>();
    int[] jumpedLocation1;
    int[] jumpedLocation2;
    ArrayList<int[]> jumpedPieces = new ArrayList<>();


    private JButton[][] buttons = new JButton[8][8];   // Array to hold buttons
    private JButton backButton = new JButton();
    private JButton resetBoardButton = new JButton();


    private JPanel mainPanel = new JPanel(null); // Main panel to hold everything
    private JPanel player1TurnJP = new JPanel( new BorderLayout()); // Main panel to hold everything
    private JPanel player2TurnJP = new JPanel(new BorderLayout()); // Main panel to hold everything
    private JPanel buttonPanel = new JPanel(); //centered on mainPanel, can be easily adjusted for size

    public Board(boolean computerPlaysRed, Theme theme) 
    {
        this.computerPlaysRed = computerPlaysRed;
        System.out.println("Theme them theme : " + theme.getPlayer1Image());
        this.theme = theme;

        initializeGUI();
        initializeButtons();
        addPieces();
        resetBoard();
        frame.setVisible(true);

         // If computerPlaysRed is true, make the computer move at the beginning

        if (computerPlaysRed) 
        {
        computerMove();
        }
    }

    private void initializeGUI()
    {
        player1Image = theme.getPlayer1Image();
        player2Image = theme.getPlayer2Image();
        System.out.println(player1Image);
        System.out.println(player2Image);
        player1ImageLabel = new ImageIcon(player1Image); 
        player2ImageLabel = new ImageIcon(player2Image); 

        kingImage1 = theme.getPlayer1ImageKing();
        System.out.println(kingImage1);
        kingImage2 = theme.getPlayer2ImageKing();
        System.out.println(kingImage2);


        Color backgroundColor = theme.getBackgroundColor();
        Color fontColor = theme.getForegroundColor();
 
        System.out.println("Board image: " + player1Image);
        //mac slay
        try{
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            catch(Exception e) {
                e.printStackTrace(); 
            }

        // JFrame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int screenWidth = 900;
        int screenHeight = 750;
        Dimension screenSize = new Dimension(screenWidth, screenHeight);


        // Set the frame size based on screen size
        frame.setSize(screenSize);

        // Back button setup, gets rid of the JFrame and goes back to the GUI class.

         backButton.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent e) 
             {
                 frame.dispose();
                 GUI.frame.setState(Frame.NORMAL);
             }
         });

         //initialzie the resetBoardButton, the button calls the startGameOver() method
        resetBoardButton.setText("Restart");
        resetBoardButton.setForeground(fontColor);
        resetBoardButton.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent e) 
             {
                startGameOver();
                System.out.println("Restart button pressed"); //debugging
             }
         });

        resetBoardButton.setFont(new Font("SERIF", Font.PLAIN, 25)); // Set font and size

        resetBoardButton.setBounds(20,30,100,50);
        resetBoardButton.setOpaque(true);
        resetBoardButton.setBackground(Color.WHITE);
        resetBoardButton.setForeground(Color.BLACK);
        resetBoardButton.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        resetBoardButton.setFocusPainted(false); 

        backButton.setBounds(790, 10, 100, 90);
        backButton.setIcon(backButtonImage);
        backButton.setOpaque(false); // Set the button to be transparent
        backButton.setContentAreaFilled(false); // Ensure content area is not filled
        backButton.setBorderPainted(false); // Remove border painting
        backButton.setFocusPainted(false); 
    
        mainPanel.setSize(screenSize);
        mainPanel.setBackground(backgroundColor);

        turnLabel = new JLabel();
        turnLabel.setBounds(345, 20, 300, 50); // Set bounds as needed
        turnLabel.setFont(new Font("SERIF", Font.BOLD, 30)); // Set font and size
        turnLabel.setForeground(fontColor); //font color to black
        updateTurnLabel(); // Initial update

        //-------------------------------------------------------------
        Player1Score = new JLabel();
        Player1Score.setFont(new Font("SERIF", Font.BOLD, 25)); // Set font and size
        Player1Score.setForeground(fontColor); //font color to black
        player1TurnJP.add(Player1Score, BorderLayout.EAST);
        //////////////////////////////
        Player2Score = new JLabel();
        Player2Score.setFont(new Font("SERIF", Font.BOLD, 25)); // Set font and size
        Player2Score.setForeground(fontColor); //font color to black
        player2TurnJP.add(Player2Score, BorderLayout.EAST);
        //////////////////////////////
        JLabel Player1ScoreImage = new JLabel(player1ImageLabel);
        player1TurnJP.add(Player1ScoreImage, BorderLayout.WEST);
        JLabel Player2ScoreImage = new JLabel(player2ImageLabel);
        player2TurnJP.add(Player2ScoreImage, BorderLayout.WEST);
        player1TurnJP.setBackground(backgroundColor);
        player2TurnJP.setBackground(backgroundColor);
        //-------------------------------------------------------------
        player1TurnJP.setBounds(750,200,110,100);
        player2TurnJP.setBounds(750,400,110,100);
        

        mainPanel.add(turnLabel);
        updateScoreLabels();

        buttonPanel.setLayout(new GridLayout(8, 8));
        int preferredButtonPanelSize = (int) (Math.min(screenWidth, screenHeight) * 0.75); // 75% of the smaller dimension

        // Set the preferred size of the button panel
        buttonPanel.setPreferredSize(new Dimension(preferredButtonPanelSize, preferredButtonPanelSize));
        buttonPanel.setBounds(175, 90, preferredButtonPanelSize, preferredButtonPanelSize);

        buttonPanel.setBorder(BorderFactory.createLineBorder(borderColor, 5));

        mainPanel.add(buttonPanel);

        mainPanel.add(backButton);
        mainPanel.add(resetBoardButton);
        mainPanel.add(player2TurnJP);
        mainPanel.add(player1TurnJP);
        mainPanel.setBorder(BorderFactory.createLineBorder(borderColor, 4));

        frame.setResizable(false);


        // Add main panel to frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }


    // Initialize buttons and add to panel, makes an 8x8 grid
    private void initializeButtons() 
    {
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBorder(null);
                buttons[i][j].setLayout(null);
                buttons[i][j].setOpaque(true);
                buttonPanel.add(buttons[i][j]);
            }
        }
    }

    //add the physical checkers pieces to the board
    public void addPieces()
    {
        //debugging
        ArrayList<Piece> addPiecesDebugger = new ArrayList<Piece>();
        //add red pieces to top of board
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if ((i + j) % 2 == 0) 
                {
                    Piece piece = new Piece("player1"); //set side
                    piece.setIcon(player1ImageLabel); // Set the icon for the piece
                    // Center the image within the button
                    piece.setHorizontalAlignment(SwingConstants.CENTER);
                    piece.setVerticalAlignment(SwingConstants.CENTER);
                    // Add the piece to the button
                    buttons[i][j].setLayout(new BorderLayout());
                    buttons[i][j].add(piece, BorderLayout.CENTER);
                    buttons[i][j].repaint();
                }
            }
        }

        // Add black pieces to bottom of board
        for (int i = 5; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if ((i + j) % 2 == 0) 
                {
                    Piece piece = new Piece("player2"); //set side
                    piece.setIcon(player2ImageLabel); // Set the icon for the piece
                    // Center the image within the button
                    piece.setHorizontalAlignment(SwingConstants.CENTER);
                    piece.setVerticalAlignment(SwingConstants.CENTER);
                    // Add the piece to the button
                    buttons[i][j].setLayout(new BorderLayout());
                    buttons[i][j].add(piece, BorderLayout.CENTER);
                    buttons[i][j].repaint();
                }
            }
        }
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if(buttons[i][j].getComponentCount() > 0)
                {
                    Piece piece = (Piece) buttons[i][j].getComponent(0);
                    addPiecesDebugger.add(piece);
                }
            }
        } 

       // System.out.println("Pieces are set");  //debugging
        //System.out.println("There are " + addPiecesDebugger.size() + " pieces on the board after reset");

        // Force the button panel to revalidate and repaint because it's dumb and won't do it by itself
        buttonPanel.revalidate();
        buttonPanel.repaint();

    }
    
    // Highlight possible moves buttons
    /**
     * @param row The row of the button clicked
     * @param col The column of the button clicked
     */

    private void highlightPossibleMoves(int row, int col) 
    {
        resetBoard();
        System.out.println("Highlighting possible moves for button at [" + row + "," + col + "]");
    
        //check if computer turn
        if (computerPlaysRed && turnCounter % 2 == 1)
        {
            computerMove();
        }

        //check if clicked button contains piece
        if (buttons[row][col].getComponentCount() > 0) 
        {
            Piece myPiece = (Piece) buttons[row][col].getComponent(0); // Get the source of the clicked button
            String myPieceSide = myPiece.getSide();
            if((turnCounter % 2 == 1 && myPieceSide.equals("player1")) || (turnCounter % 2 == 0 && myPieceSide.equals("player2")))
            {
                // Get possible moves for the piece
                ArrayList<int[]> possibleMoves = getPossibleMovesForPiece(row, col);
    
                for (int[] move : possibleMoves) 
                {
                    int newRow = move[2]; //get the third element of the move  array 
                    int newCol = move[3]; //get the fourth element of the move  array 
    
                     // Highlight the button at the new position
                    buttons[newRow][newCol].setBackground(HIGHLIGHT_COLOR);

                    // Add an ActionListener to the highlighted button to handle the move
                    buttons[newRow][newCol].addActionListener(new ActionListener() 
                    {
                        @Override
                        public void actionPerformed(ActionEvent e) 
                        {
                            Move(row, col, newRow, newCol);
                         }
                    });
                }
            }
            else
            {
                System.out.println("It is not this piece's turn");
            }
        }
        else
        {
            System.out.println("There is no piece in cell [" + row + "," + col + "]");
        }
    }
    
    //https://www.tabnine.com/code/java/methods/javax.swing.JButton/removeActionListener
    private void Move(int i, int j, int newRow, int newCol)
    {
    if (buttons[i][j].getComponentCount() > 0) 
        {

        Piece myPiece = (Piece) buttons[i][j].getComponent(0); // Get the source of the clicked button
        String myPieceSide = myPiece.getSide();
        
        // Debugging
        System.out.println("Moving from cell [" + i + "," + j + "]"); //verify where piece is moved from 

        if (buttons[newRow][newCol].getComponentCount() == 0) //verify there are no pieces where the clicked piece is trying to move
            {
                buttons[newRow][newCol].add(myPiece); //add piece to new place
                buttons[i][j].remove(myPiece); //remove piece from old place

                buttons[newRow][newCol].setLayout(null);
                // Set the location of the piece within the button
               
                {
                    if(myPieceSide.equals("player1"))
                    {
                        if(newRow == 7)
                        {
                            myPiece.setKingedPlayer1(true, kingImage1);
                            System.out.println("player1 piece has been kinged");
                            myPiece.repaint();
                        }

                    }
                    else if(myPieceSide.equals("player2"))
                    {
                        if(newRow == 0)
                        {
                            myPiece.setKingedPlayer2(true, kingImage2);
                            System.out.println("Player2 piece has been kinged");
                            myPiece.repaint();
                        }
                    }

                }
                buttons[i][j].repaint(); // Request a repaint of the button board otherwise it takes forever

                resetBoard();
            
                frame.validate();
                frame.repaint(); 

            /* 
            //----------------------------------------------------------------------------------------------------------
            // This is for debugging all the action listeners  that's happening, should ALWAYS be 64, anything else is very very very very very bad
            
                int totalListeners = countActionListeners();
                System.out.println("Total Action Listeners AFTER moving the piece: " + totalListeners);

                System.out.println("Turn counter is " + turnCounter);
                System.out.println(myPiece.getSide() + " piece is in [" + newRow + "," + newCol + "]");
                if (buttons[newRow][newCol].getComponentCount() == 0)
                {
                    System.out.println("No piece here");
                } else 
                {
                Piece piece = (Piece) buttons[newRow][newCol].getComponent(0);
                System.out.println("There is a " + piece.getSide() + " piece in " + "[" + newRow + "," + newCol + "]");
                }
            //----------------------------------------------------------------------------------------------------------
            */

            // Check if a jump move was made
                if (Math.abs(i - newRow) > 1 || Math.abs(j - newCol) > 1)
                { 

                    //debugging https://www.geeksforgeeks.org/hashtable-in-java/
        
                    int[] move_int_jp = new int[]{i, j, newRow, newCol};

                        //convert key to string--------------------------
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int m : move_int_jp) {
                            stringBuilder.append(m);
                        }

                        String result = stringBuilder.toString();
                        //------------------------------------------------
                

                    System.out.println("Move_int 0:" + move_int_jp[0]);
                    System.out.println("Move_int 1:" + move_int_jp[1]);
                    System.out.println("Move_int 2:" + move_int_jp[2]);
                    System.out.println("Move_int 3:" + move_int_jp[3]);

                    System.out.println("Row: " + i + " Col: " + j + " Jump Row: " + newRow + " Jump Col: " + newCol);

                    ArrayList<int[]> jumpedPiecesLocationfromHash = (jumpedPiecesHash.get(result));
                    jumpedPieceLength = jumpedPiecesLocationfromHash.size();


                    for (int[] move : jumpedPiecesLocationfromHash) {
                        int jumpedRow = move[0];
                        int jumpedCol = move[1];
                        
                        Piece piece = (Piece) buttons[jumpedRow][jumpedCol].getComponent(0);
                        buttons[jumpedRow][jumpedCol].remove(piece);
                        System.out.println("Piece at " + jumpedRow + " : " + jumpedCol + " removed");
                        buttons[jumpedRow][jumpedCol].repaint();
                    }


                    if(myPieceSide.equals("player1"))
                    {
                        player2PiecesRemaining = player2PiecesRemaining - jumpedPieceLength;
                    }
                    else if(myPieceSide.equals("player2"))
                    {
                        player1PiecesRemaining = player1PiecesRemaining - jumpedPieceLength;
                        System.out.println("Player1 pieces remaing " + player1PiecesRemaining);
                    }
                    
                }
            } 
            else 
            {
                //System.out.println("There is a piece there in cell [" + newRow + "," + newCol + "] : Component = 0: Move"); //debugging
            }
        }
        else 
        {
        //System.out.println("There is no piece in cell [" + i + "," + j + "] : getComponentCount() > 0: Move" ); //debugging
        }  
        updateTurnLabel();
        turnCounter++; //increase turnCounter
        System.out.println(turnCounter);
        resetBoard();
        updateScoreLabels();
        checkEndGame();
        System.out.println("TURN OVER--------------------------------------------------------"); //just to organize the terminal a little more
    }

    /* 
    //debugging method, counts the total number of action listeners on the button grid, should be consistent at 64. 
    //should be 1 actionlisteners on each cell, anything other number is BAD BAD BAD BAD BAD BAD BAD BAD VERY VERY BAD NO
    private int countActionListeners() 
    {
        int count = 0;
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                ActionListener[] listeners = buttons[i][j].getActionListeners();
                count += listeners.length;
            }
        }
        return count;
    }
    */

    /*
    this method checks the side of pieces that are juumped. "side" represents
    the side of the current player and return the side of the opponent player. 
    If the "side" parameter is equal to "red", it returns "black" vice versa
    */
    private String getOpponentSide(String side) 
    {
        return side.equals("player1") ? "player2" : "player1";
    }

    /*
     * I couldn't figure out how to track the overlapping actionlistners so I just had a breakdown and decided to delete all of them each time a move is made.
     * After each move, you call this method in the resetBoard() method, it deletes ALL actionlisteners on the board
     * Then it adds an actionlisteners to each button to call the highlightPossibleMoves method when a button is pressed.
     * 
     * Basically this keeps the location of actionlisteners and source cells updated so they don't get super fucked up
     */

    private void initalizeActionListeners()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++) 
            {
                int row = i; 
                int col = j;

                //remove all prior actionlisteneres
                for( ActionListener al : buttons[i][j].getActionListeners() ) 
                {
                    buttons[i][j].removeActionListener(al);
                }
                buttons[i][j].addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                            highlightPossibleMoves(row, col);
                    }
                });

            }
        }
    }
    // Reset the board colors
    private void resetBoard() 
    {
        // Reset the board colors
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                //reset colors
                if ((i + j) % 2 == 0) 
                {
                    buttons[i][j].setBackground(COLOR_1);
                    buttons[i][j].setOpaque(true);
                } else 
                {
                    buttons[i][j].setBackground(COLOR_2);
                    buttons[i][j].setOpaque(true);
                }
            }
        }
        //reinitialize all actionlisteners so they don't overlap
        initalizeActionListeners();
    }


    // Check if cell is within the board boundaries, must be in 8 by 8 grid, otherwise code will crash because its stupid as fuck
    private boolean isValidCell(int row, int col) 
    {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /*
     * I'm not sure what we want to do when the game has ended, so right now I just have it as removing all the actionlisteners from the buttons 
     * so you can't press anything. Basically just a holder method for now
     */
    private void endGame(JFrame parentFrame) 
    {
        winner = getWinner();
        JDialog dialog = new JDialog(parentFrame, true);
        
        // Set background color of the content pane
        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(new Color(230,230,230)); // Set the desired background color
        dialog.setContentPane(contentPane);
    
        dialog.setLayout(null); // Set layout to null for absolute positioning
    
        JLabel messageLabel = new JLabel(winner + " wins!");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setForeground(fontColor); 
        messageLabel.setBounds(160, 20, 200, 30); // Set bounds for position and size
        Font labelFont = messageLabel.getFont();
        messageLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 30)); // Increase font size to 20

        contentPane.add(messageLabel);

        JLabel turnLabelPopup = new JLabel("Total Score" );
        turnLabelPopup.setHorizontalAlignment(JLabel.CENTER);
        turnLabelPopup.setForeground(fontColor); 
        turnLabelPopup.setBounds(10, 80, 200, 30); // Set bounds for position and size
        Font turnLabelFont = turnLabelPopup.getFont();
        turnLabelPopup.setFont(new Font(turnLabelFont.getName(), Font.PLAIN, 25)); // Increase font size to 20
        turnLabelPopup.setFont(new Font("SERIF", Font.PLAIN, 25)); // Set font and size
        contentPane.add(turnLabelPopup);
        ////////////////////

        JPanel player1ScorePopup = new JPanel(new BorderLayout());
        JPanel player2ScorePopup = new JPanel(new BorderLayout());
          //-------------------------------------------------------------
          Player1Score = new JLabel(" - " + (12-player2PiecesRemaining));
          Player1Score.setFont(new Font("Arial", Font.BOLD, 25)); // Set font and size
          Player1Score.setForeground(fontColor); //font color to black
          player1ScorePopup.add(Player1Score, BorderLayout.EAST);
          //////////////////////////////
          Player2Score = new JLabel(" - " + (12-player1PiecesRemaining));
          Player2Score.setFont(new Font("Arial", Font.BOLD, 25)); // Set font and size
          Player2Score.setForeground(fontColor); //font color to black
          player2ScorePopup.add(Player2Score, BorderLayout.EAST);
          //////////////////////////////
          JLabel Player1ScoreImage = new JLabel(player1ImageLabel);
          player1ScorePopup.add(Player1ScoreImage, BorderLayout.WEST);
          JLabel Player2ScoreImage = new JLabel(player2ImageLabel);
          player2ScorePopup.add(Player2ScoreImage, BorderLayout.WEST);
          player1ScorePopup.setBackground(new Color(230,230,230));
          player2ScorePopup.setBackground(new Color(230,230,230));
          //-------------------------------------------------------------
          player1ScorePopup.setBounds(30,115,110,80);
          player2ScorePopup.setBounds(30,200,110,80);
          contentPane.add(player1ScorePopup);
          contentPane.add(player2ScorePopup);
        /////////////////////////
        JButton homeButtonPop = new JButton("Home");
        homeButtonPop.setFocusPainted(false); 
        homeButtonPop.setBackground(buttonColor);
        homeButtonPop.setForeground(fontColor); 
        homeButtonPop.setFont(new Font(labelFont.getName(), Font.BOLD, 20)); // Increase font size to 20
        homeButtonPop.setBounds(70, 300, 150, 40); // Set bounds for position and size
        homeButtonPop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Close the dialog
                frame.dispose();
                GUI.frame.setState(Frame.NORMAL);
            }
        });
        contentPane.add(homeButtonPop);
    
        JButton restartButtonPop = new JButton("Play Again");        
        restartButtonPop.setFocusPainted(false); 
        restartButtonPop.setBackground(buttonColor);
        restartButtonPop.setForeground(fontColor); 
        restartButtonPop.setFont(new Font(labelFont.getName(), Font.BOLD, 20)); // Increase font size to 20
        restartButtonPop.setBounds(290, 300, 150, 40); // Set bounds for position and size

        restartButtonPop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Close the dialog
                removeAllPieces();
                resetBoard();
                player1PiecesRemaining = 12;
                player2PiecesRemaining = 12;
                initalizeActionListeners();
                // Add new pieces
                addPieces();
                // Verify pieces remaining
                System.out.println("There are " + player1PiecesRemaining + " player1 pieces remaining");
                System.out.println("There are " + player2PiecesRemaining + " player2 pieces remaining");
                updateScoreLabels();
                gameOver = false; // Reset game boolean
            }
        });
        contentPane.add(restartButtonPop);

        contentPane.add(restartButtonPop);

        restartButtonPop.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        homeButtonPop.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        contentPane.setBorder(BorderFactory.createLineBorder(borderColor, 3));
    
        dialog.setSize(500, 400); // Set the size of the dialog
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
    
    /*
     * This is called when you want to restart the game. It removes all prior actionlisteners and pieces from the board
     * It then reinitializes new actionlisteners in the resetBoard() method. 
     * Then it adds new pieces and sets the piecesRemaing back to 12, then you can start a new game from the beginning
     */
    public void startGameOver()
    {
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                //remove all prior actionlisteneres
                for( ActionListener al : buttons[i][j].getActionListeners() ) 
                {
                    buttons[i][j].removeActionListener( al );
                }

                //remove all pieces
                if (buttons[i][j].getComponentCount() > 0) 
                {
                    Piece removedPiece = (Piece) buttons[i][j].getComponent(0); 
                    buttons[i][j].remove(removedPiece);
                    buttons[i][j].repaint();

                }
            }
        }
        resetBoard();
        player1PiecesRemaining = 12;
        player2PiecesRemaining = 12;
        initalizeActionListeners();
        //add new pieces
        addPieces();
        turnCounter = 1;
        //verify pieces remaining
        System.out.println("There are " + player1PiecesRemaining + " player1 pieces remaing" );
        System.out.println("There are " + player2PiecesRemaining + " player2 pieces remaing" );
        updateScoreLabels();
        gameOver = false; //reset game boolean
        buttonPanel.repaint();
    }

    private void computerMove() {
        ArrayList<int[]> redPiecesWithMoves = new ArrayList<>();

        // Step 1: Find red pieces with available moves
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j].getComponentCount() > 0) {
                    Piece currentPiece = (Piece) buttons[i][j].getComponent(0);
                    if (currentPiece.getSide().equals("player1")) {
                        ArrayList<int[]> possibleMoves = getPossibleMovesForPiece(i, j);
                        if (!possibleMoves.isEmpty()) {
                            redPiecesWithMoves.add(new int[]{i, j});
                        }
                    }
                }
            }
        }

        // If there are no red pieces with available moves, return
        if (redPiecesWithMoves.isEmpty()) {
            System.out.println("No player 1 pieces with available moves.");
            return;
        }

        // Randomly select a red piece with available moves
        Random rand = new Random();
        int[] selectedPiece = redPiecesWithMoves.get(rand.nextInt(redPiecesWithMoves.size()));
        int selectedPieceRow = selectedPiece[0];
        int selectedPieceCol = selectedPiece[1];

        // Get possible moves for the selected piece
        ArrayList<int[]> possibleMoves = getPossibleMovesForPiece(selectedPieceRow, selectedPieceCol);

        // Randomly select a move from the possible moves
        int[] selectedMove = possibleMoves.get(rand.nextInt(possibleMoves.size()));
        int newRow = selectedMove[2];
        int newCol = selectedMove[3];

        // Introduce a delay before the computer makes its move for visual effect
        Timer timer = new Timer(500, e -> 
        {
            // Move the piece to the randomly chosen move after 1 second
            Move(selectedPieceRow, selectedPieceCol, newRow, newCol);
        });
        timer.setRepeats(false); // Set the timer to only fire once
        timer.start(); // Start the timer to move
    }


    //MOST IMPORTANT METHOD EVER ALL HAIL THE METHOD
    private ArrayList<int[]> getPossibleMovesForPiece(int row, int col) 
    {
        jumpSide1 = false;
        jumpSide2 = false;

        possibleMoves.clear();
        jumpedPiecesHash.clear();

        //int rowit;
        //int colit;
    
        Piece piece = (Piece) buttons[row][col].getComponent(0);  // Get the piece at the specified position
        String pieceColor = piece.getSide();  // Get the color of the piece
        Boolean pieceIsKinged = piece.getKinged(); // Check if the piece is kinged
    
        int[] dy; // Array to store row 
        int[] dx; // Array to store column
        
        // Define movement directions based on piece color
        if (pieceColor.equals("player1")) 
        {
            // Red pieces move downwards
            dy = new int[]{1, 1};
            dx = new int[]{1, -1};
    
            // Movements for kings
            if (pieceIsKinged) 
            {
                dy = new int[]{1, 1, -1, -1};
                dx = new int[]{1, -1, -1, 1};
            }
        } else 
        {
            // Black pieces move upwards
            dy = new int[]{-1, -1};
            dx = new int[]{-1, 1};
    
            // Movements for kings
            if (pieceIsKinged) 
            {
                dy = new int[]{1, 1, -1, -1};
                dx = new int[]{1, -1, -1, 1};
            }
        }
    
        // Iterate through possible movement directions
        for (int i = 0; i < dx.length; i++) 
        {
            // Calculate new position and jump position
            int newRow = row + dy[i]; // New row index
            int newCol = col + dx[i];  // New column index
    
            // Jump position
            int jumpRow = row + 2 * dy[i];
            int jumpCol = col + 2 * dx[i];
    
            // Check if new position is a valid empty cell
            if (isValidCell(newRow, newCol) && buttons[newRow][newCol].getComponentCount() == 0) 
            {
                int[] move_ints = new int[]{row, col, newRow, newCol}; //generate ints
                possibleMoves.add(move_ints); 

            } 
            else if (isValidCell(newRow, newCol) && buttons[newRow][newCol].getComponentCount() != 0 &&
                    isValidCell(jumpRow, jumpCol) && buttons[jumpRow][jumpCol].getComponentCount() == 0 &&
                    ((Piece) buttons[newRow][newCol].getComponent(0)).getSide().equals(getOpponentSide(pieceColor)))
            {
                // Add jump move to possible moves
                int[] move_ints = new int[]{row, col, jumpRow, jumpCol};
                possibleMoves.add(move_ints); 

                ArrayList<int[]> jumpedPiecesLocation = new ArrayList<>();
                int[] jumpedOpponentLocation1 = new int[]{newRow, newCol};

                jumpedPiecesLocation.add(jumpedOpponentLocation1);

                
                System.out.println("Move location:");
                System.out.println("Row: " + row + " Col: " + col + " Jump Row: " + jumpRow + " Jump Col: " + jumpCol);
                System.out.println("Jumped Pieces Locations:");
                for(int[] jumpedOpponentLocation : jumpedPiecesLocation) 
                {
                    rowit = jumpedOpponentLocation[0];
                    colit = jumpedOpponentLocation[1];
                System.out.println("Row: " + rowit + ", Column: " + colit);
                }
                

                StringBuilder stringBuilder = new StringBuilder();

                for (int k : move_ints) {
                    stringBuilder.append(k);
                }

                String result = stringBuilder.toString();

                jumpedPiecesHash.put(result, jumpedPiecesLocation);
                //System.out.println("Hash before total: " + jumpedPiecesHash.get(result));
                

                findDoubleJumpMoves(row, col, jumpRow, jumpCol, dy, dx, pieceColor, newRow, newCol); 

                //jumpedPieces.add(jumpedOpponentLocation1);
      
            }
        }
    return possibleMoves;
    }

    private void findDoubleJumpMoves(int row, int col, int jumpRow, int jumpCol, int[] dy, int[] dx, String pieceColor, int newRow, int newCol) 
    {
        for (int k = 0; k < dx.length; k++) {

        ArrayList<int[]> jumpedPiecesLocation = new ArrayList<>();
        //int rowit;
        //int colit;

        

            // Calculate new position and jump position
            int newRow1 = jumpRow + dy[k]; // New row index
            int newCol1 = jumpCol + dx[k];  // New column index

            int firstNewRow = newRow;
            int firstNewcol = newCol;
    
            // Jump position
            int jumpRow1 = jumpRow + 2 * dy[k];
            int jumpCol1 = jumpCol + 2 * dx[k];
    
            if (isValidCell(newRow1, newCol1) && buttons[newRow1][newCol1].getComponentCount() != 0 &&
                    isValidCell(jumpRow1, jumpCol1) && buttons[jumpRow1][jumpCol1].getComponentCount() == 0 &&
                    ((Piece) buttons[newRow1][newCol1].getComponent(0)).getSide().equals(getOpponentSide(pieceColor))) 
                {
                // Add jump move to possible moves
                int[] move_ints = new int[]{row, col, jumpRow1, jumpCol1};
                possibleMoves.add(move_ints);

                      //convert key to string--------------------------
                      StringBuilder stringBuilder = new StringBuilder();

                      for (int m : move_ints) {
                          stringBuilder.append(m);
                      }
      
                      String result = stringBuilder.toString();
                      //------------------------------------------------
                      

                jumpedLocation1 = new int[]{firstNewRow, firstNewcol};
                jumpedLocation2 = new int[]{newRow1, newCol1};

                jumpedPiecesLocation.add(jumpedLocation1);
                jumpedPiecesLocation.add(jumpedLocation2);

                jumpedPiecesHash.put(result, jumpedPiecesLocation);

                System.out.println("Hash before total: " + jumpedPiecesHash.get(result));
                System.out.println("Move location:");
                System.out.println("Row: " + row + " Col: " + col + " Jump Row: " + jumpRow1 + " Jump Col: " + jumpCol1);
                System.out.println("Jumped Pieces Locations Find Double Jumps:");

                 for(int[] jumpedOpponentLocation : jumpedPiecesLocation) 
                {
                    rowit = jumpedOpponentLocation[0];
                    colit = jumpedOpponentLocation[1];
               System.out.println("Row: " + rowit + ", Column: " + colit);
                }
                
                
            }
        }
    }
     
    private void updateTurnLabel() 
    {
        if (turnCounter % 2 == 1) {
            turnLabel.setText("Player 1's Turn!");
        } else {
            turnLabel.setText("Player 2's Turn!");
        }
    }

    private void updateScoreLabels() 

    {
        player1ScoreInt = 12 - player2PiecesRemaining;
        player2ScoreInt = 12 - player1PiecesRemaining;

        Player1Score.setText(" - " + player1ScoreInt);
        Player2Score.setText(" - " + player2ScoreInt);
    }

    private void checkEndGame()
    {
        if(player2PiecesRemaining == 0) //game over if no black pieces remain
        {
            gameOver = true;
            System.out.println("The game is over");
            endGame(frame);
        }
        if(player1PiecesRemaining == 0) //game over if no black pieces remain
        {
            gameOver = true;
            System.out.println("The game is over");
            endGame(frame);
        }
    }

    private String getWinner()
    {
        if(player1PiecesRemaining == 0)
        {
            winner = "Player 2";
        }
        else if (player2PiecesRemaining == 0)
        {
            winner = "Player 1";
        }

        return winner;
    }

    public void removeAllPieces()
    {
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                //remove all pieces
                if (buttons[i][j].getComponentCount() > 0) 
                {
                    Piece removedPiece = (Piece) buttons[i][j].getComponent(0); 
                    buttons[i][j].remove(removedPiece);
                    buttons[i][j].repaint();

                }
            }
        }
    }
}
    