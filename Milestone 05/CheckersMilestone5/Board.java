package checkersnetbeams;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Board 
{
   // https://www.tabnine.com/code/java/class-index

    // Colors for the board
    private Color COLOR_1 = new Color(250, 250, 250);
    private Color COLOR_2 = new Color(0, 0, 0);
    private Color HIGHLIGHT_COLOR = new Color(255, 255, 0);

    private boolean computerPlaysRed = false;

    //computerPiece arrayList for computer moves, not currently used in updated version
    ArrayList<Piece> computerPieceArray = new ArrayList<Piece>();
    ArrayList<Integer> computerPieceI = new ArrayList<Integer>();
    ArrayList<Integer> computerPieceJ = new ArrayList<Integer>();


    boolean computerMoved;

    // Array to hold buttons
    private JButton[][] buttons = new JButton[8][8];
  
    // Frame and panels
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(); // Main panel to hold everything
    private JPanel buttonPanel = new JPanel(); //centered on mainPanel, can be easily adjusted for size
    private JPanel textPanel = new JPanel();

    //reset button and back button
    private JPanel leftButtonPanel = new JPanel();

    //Monitor game status
    private int redPiecesRemaining = 12;
    private int blackPiecesRemaining= 12;
    Boolean gameOver = false;

    // Text field and button
    private JTextField text = new JTextField();
    private JButton backButton = new JButton();
    private JButton resetBoardButton = new JButton();

    //turn counter might need to be 3 for remainders idk really
    int turnCounter = 1;
    Piece jumpedOpponent;

    public Board(boolean computerPlaysRed) 
    {
        this.computerPlaysRed = computerPlaysRed;
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

        // Text setup
        text.setBackground(Color.BLACK);
        text.setForeground(Color.PINK);
        text.setFont(new Font("SERIF", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("CHECKERS");
        text.setOpaque(true);
        text.setEditable(false);

        // Text panel setup
        textPanel.setLayout(new BorderLayout());
        textPanel.add(text);

        // Back button setup, gets rid of the JFrame and goes back to the GUI class.
         backButton.setText("Back");
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
         resetBoardButton.addActionListener(new ActionListener() 
         {
             @Override
             public void actionPerformed(ActionEvent e) 
             {
                startGameOver();
                System.out.println("Restart button pressed"); //debugging
             }
         });

        // Add components to frame
        mainPanel.setLayout(new BorderLayout());


        mainPanel.setSize(screenSize);
        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(leftButtonPanel, BorderLayout.WEST);

        // Button panel setup
        buttonPanel.setLayout(new GridLayout(8, 8));
        // Calculate preferred size as a percentage of the screen size
        int preferredButtonPanelSize = (int) (Math.min(screenWidth, screenHeight) * 0.8); // 80% of the smaller dimension

        // Set the preferred size of the button panel
        buttonPanel.setPreferredSize(new Dimension(preferredButtonPanelSize, preferredButtonPanelSize));

        JPanel centerPanel = new JPanel(new GridBagLayout()); // Put the button panel on another JPanel, and then put THAT Jpanel on the main panel.
        centerPanel.add(buttonPanel);

        // Add centerPanel with buttonPanel to main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //add back button and reset button to main panel
        leftButtonPanel.setPreferredSize(new Dimension(80, 50));

        leftButtonPanel.add(backButton);
        leftButtonPanel.add(resetBoardButton);

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
                buttons[i][j].setOpaque(true);
                buttonPanel.add(buttons[i][j]);
            }
        }
    }

    //add the physical checkers pieces to the board
    public void addPieces()
    {
        //add red pieces to top of board
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if ((i + j) % 2 == 0) 
                {
                    Piece piece = new Piece("red"); //set side
                    piece.setRED(); //changes image to red checkers piece
                    // Center the image within the button
                    piece.setHorizontalAlignment(SwingConstants.CENTER);
                    piece.setVerticalAlignment(SwingConstants.CENTER);
                    buttons[i][j].add(piece);
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
                    Piece piece = new Piece("black"); //set side
                    piece.setBLACK(); //changes image to black checkers piece
                // Center the image within the button
                piece.setHorizontalAlignment(SwingConstants.CENTER);
                piece.setVerticalAlignment(SwingConstants.CENTER);
                buttons[i][j].add(piece);
                buttons[i][j].repaint();
                }
            }
        }

        System.out.println("Pieces are set");  //debugging

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
            if((turnCounter % 2 == 1 && myPieceSide.equals("red")) || (turnCounter % 2 == 0 && myPieceSide.equals("black")))
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
                myPiece.setLocation(buttons[newRow][newCol].getWidth() / 2 - myPiece.getWidth() / 2, 
                buttons[newRow][newCol].getHeight() / 2 - myPiece.getHeight() / 2);


                //check if should be kinged CHECKED BUT MIGHT BE IFFY IDK
                {
                    if(myPieceSide.equals("red"))
                    {
                        if(newRow == 7)
                        {
                            myPiece.setKinged(true);
                            System.out.println("Red piece has been kinged");
                        }

                    }
                    else if(myPieceSide.equals("black"))
                    {
                        if(newRow == 0)
                        {
                            myPiece.setKinged(true);
                            System.out.println("Black piece has been kinged");
                        }
                    }

                }
                buttons[i][j].repaint(); // Request a repaint of the button board otherwise it takes forever
                turnCounter++; //increase turnCounter
                resetBoard();
            
                frame.validate();
                frame.repaint(); 

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

            // Check if a jump move was made
            
                int jumpedRow = (i + newRow) / 2; // Calculate the row of the jumped opponent, there should be an  cell in the row between the old and new row which holds the opponent
                int jumpedCol = (j + newCol) / 2; // Calculate the column of the jumped opponent, same logic as the row

                if (Math.abs(i - newRow) == 2 && Math.abs(j - newCol) == 2)
                { //checks if the absolute difference in row and column indices between the original and destination positions is equal to 2, indicating a jump move.
                    // Remove the jumped opponent from the board

                    Piece jumpedOpponent =  (Piece) buttons[jumpedRow][jumpedCol].getComponent(0);//get opponent piece
                    System.out.println("have gotten opponent");
                    String jumpedOpponentSide = jumpedOpponent.getSide(); //get opponenet side

                    buttons[jumpedRow][jumpedCol].remove(jumpedOpponent); // Remove all components from the jumped cell
                    System.out.println("have removed opponent");
                    buttons[jumpedRow][jumpedCol].repaint(); //call immediate repaint of board otherwise it takes forever for java to catch up

                    //update the number of remaining pieces on the board
                    if (jumpedOpponentSide.equals("red"))
                    {
                        redPiecesRemaining--;
                        System.out.println("There are " + redPiecesRemaining + " red pieces remaing" );
                        if(redPiecesRemaining == 0) //game over if no red pieces remain
                        {
                            gameOver = true;
                            System.out.println("The game is over");
                            endGame();
                        }
                    }
                    else if (jumpedOpponentSide.equals("black"))
                    {
                        blackPiecesRemaining--;
                        System.out.println("There are " + blackPiecesRemaining + " black pieces remaing" );
                        if(blackPiecesRemaining == 0) //game over if no black pieces remain
                        {
                            gameOver = true;
                            System.out.println("The game is over");
                            endGame();
                        }
                    }
                }
            } 
            else 
            {
                System.out.println("There is a piece there in cell [" + newRow + "," + newCol + "] : Component = 0: Move"); //debugging
            }
        }
        else 
        {
        System.out.println("There is no piece in cell [" + i + "," + j + "] : getComponentCount() > 0: Move" ); //debugging
        }  
        resetBoard();
        System.out.println("TURN OVER--------------------------------------------------------"); //just to organize the terminal a little more
    }

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

    /*
    this method checks the side of pieces that are juumped. "side" represents
    the side of the current player and return the side of the opponent player. 
    If the "side" parameter is equal to "red", it returns "black" vice versa
    */
    private String getOpponentSide(String side) 
    {
        return side.equals("red") ? "black" : "red";
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
    private void endGame()
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
                
            }
        }

        System.out.println("End game method has been called");

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
        redPiecesRemaining = 12;
        blackPiecesRemaining = 12;
        initalizeActionListeners();
        //add new pieces
        addPieces();
        //verify pieces remaining
        System.out.println("There are " + redPiecesRemaining + " red pieces remaing" );
        System.out.println("There are " + blackPiecesRemaining + " black pieces remaing" );
        gameOver = false; //reset game boolean
    }

    public void computerMove() 
    {
        // Generate all possible moves for the computer player
        ArrayList<int[]> possibleMoves = new ArrayList<>();
    
        // Find all pieces belonging to the player
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) 
            {
                if (buttons[i][j].getComponentCount() > 0) 
                {
                    Piece piece = (Piece) buttons[i][j].getComponent(0);
                    if (piece.getSide().equals("red")) 
                    {
                        // Add all possible moves for this piece to the list
                        ArrayList<int[]> moves = getPossibleMovesForPiece(i, j);
                        possibleMoves.addAll(moves);
                    }
                }
            }
        }
    
        // If there are no possible moves for the computer, return
        if (possibleMoves.isEmpty()) 
        {
            return;
        }
    
        // Randomly select a move from the list of possible moves
        Random rand = new Random();

        //select a random move in the possibleMoves arraylist
        int rand_int = rand.nextInt(possibleMoves.size());
        int[] selectedMove = possibleMoves.get(rand_int);

        // Extract the move coordinates from int[]
        int row = selectedMove[0];
        int col = selectedMove[1];
        int newRow = selectedMove[2];
        int newCol = selectedMove[3];
    
        // Move the piece to the randomly chosen move
        Timer timer = new Timer(500, e -> 
        {
            // Move the piece to the randomly chosen move after 1 second
            Move(row, col, newRow, newCol);
        });
        timer.setRepeats(false); // Set the timer to only fire once
        timer.start(); // Start the timer to move
    }


    //MOST IMPORTANT METHOD EVER ALL HAIL THE METHOD
    private ArrayList<int[]> getPossibleMovesForPiece(int row, int col) 
    {
         // Initialize an ArrayList to store possible moves
        ArrayList<int[]> possibleMoves = new ArrayList<>(); 
    
        Piece piece = (Piece) buttons[row][col].getComponent(0);  // Get the piece at the specified position
        String pieceColor = piece.getSide();  // Get the color of the piece
        Boolean pieceIsKinged = piece.getKinged(); // Check if the piece is kinged
    
        int[] dy; // Array to store row 
        int[] dx; // Array to store column
        
        // Define movement directions based on piece color
        if (pieceColor.equals("red")) 
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
            if (isValidCell(newRow, newCol) && buttons[newRow][newCol].getComponentCount() != 0 &&
                    isValidCell(jumpRow, jumpCol) && buttons[jumpRow][jumpCol].getComponentCount() == 0 &&
                    ((Piece) buttons[newRow][newCol].getComponent(0)).getSide().equals(getOpponentSide(pieceColor)))
            {
                // Add jump move to possible moves
                int[] move_ints = new int[]{row, col, jumpRow, jumpCol};
                possibleMoves.add(move_ints); 
            }
    
                // possibleMoves is an arraylist of arrays, one singular array to hold a seperate array of move information
                //index 0 = current row
                //indux 1 = current column
                //index 2 = new row if moved
                //index 3 = new col if moved
        }
        
        return possibleMoves;  // Return the list of possible moves
    }
}
    