package checkersnetbeams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    // Colors for the board
    private Color COLOR_1 = new Color(200, 200, 200);
    private Color COLOR_2 = new Color(0, 0, 0);

    private Color HIGHLIGHT_COLOR = new Color(255, 255, 0);

    // Array to hold buttons
    private JButton[][] buttons = new JButton[8][8];
    private boolean[][] pressedStates = new boolean[8][8];

    // Frame and panels
    private JFrame frame = new JFrame();
    private JPanel buttonPanel = new JPanel();
    private JPanel textPanel = new JPanel();

    // Text field and button
    private JTextField text = new JTextField();
    private JButton backButton = new JButton();

    // Static variable for frame state
    public static Boolean checkJFrameState = true;

    //array of possible moves
    private ArrayList<JButton> possibleCells= new ArrayList<>();


    public Board() {


        // JFrame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Set the frame size based on screen size
        frame.setSize(screenWidth, screenHeight);

        // Text setup
        text.setBackground(Color.BLACK);
        text.setForeground(Color.RED);
        text.setFont(new Font("SERIF", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("CHECKERS");
        text.setOpaque(true);
        text.setEditable(false);

        // Text panel setup
        textPanel.setLayout(new BorderLayout());
        textPanel.setBounds(0, 0, 1500, 100);
        textPanel.add(text);

        // Back button setup
        backButton.setText("Back");
        backButton.setLayout(new BorderLayout());
        backButton.setSize(175, 175);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.frame.setState(Frame.NORMAL);
            }
        });

        // Add components to frame
        frame.add(backButton, BorderLayout.SOUTH);
        frame.add(textPanel, BorderLayout.NORTH);

        // Button panel setup
        buttonPanel.setLayout(new GridLayout(8, 8));
        buttonPanel.setPreferredSize(new Dimension(400,400));

        // Initialize buttons and add to panel
        initializeButtons();
        resetBoard();



        // Add button panel to frame
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Initialize buttons and add to panel
    private void initializeButtons() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int row = i; // Final variables for action listener
                final int col = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setFocusable(false);
                buttons[i][j].setOpaque(true);



                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try {
                            highlightPossibleMoves(row, col);
                            Move(row, col);
                        }catch (Exception k){}
                    }
                });
                    buttonPanel.add(buttons[i][j]);

            }
        }

        //add red pieces to board
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                
                Piece piece = new Piece("red");
                piece.setSide("red");
                buttons[i][j].add(piece);
                }
            }
        }
        //add black pieces to board
        for (int i = 6; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                Piece piece = new Piece("black");
                piece.setSide("black");
                buttons[i][j].add(piece);
                }
            }
        }
    }

    /*
    // Highlight possible moves buttons
    private void highlightDiagonals(int row, int col) {
        int[] dx = {1, 1}; //for the red pieces at the top 
        int[] dy = {1, -1}; //for the black pieces at the bottom

        String pieceColor = ((Piece) buttons[row][col].getComponent(0)).getSide();

        if(pieceColor.equals("red"))
        {
            dx[0] = -1;
            dx[1] = -1;
        }
        else
        {
            dy[0] = -1;
            dy[1] = 1;
        }
    }
    */

    
    //https://codeforces.com/blog/entry/78827 <- reference
    private void highlightPossibleMoves(int row, int col) {
        // Reset the board colors
        resetBoard();

        int[] dx; // Diagonal directions (right-down, right-up, left-down, left-up)
        int[] dy;

    
        String pieceColor = ((Piece) buttons[row][col].getComponent(0)).getSide();

        if(pieceColor.equals("red"))
        {
            dx = new int[]{1, 1}; 
            dy = new int[]{1, -1};
        }
        else
        {
            dx = new int[]{-1, -1}; 
            dy = new int[]{-1, 1};
        }

    
        for (int i = 0; i < 2; i++) { // Iterate over all diagonal directions
            int newRow = row + dx[i];
            int newCol = col + dy[i];
    
            if (isValidCell(newRow, newCol)) {
                buttons[newRow][newCol].setBackground(HIGHLIGHT_COLOR);
                pressedStates[newRow][newCol] = true;
                possibleCells.add(buttons[newRow][newCol]);
                disableBoard();
            }
        }
    
        pressedStates[row][col] = true;
    }
    

    // Reset the board colors
    private void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
    
                    buttons[i][j].setBackground(COLOR_1);

        
                } else {
                    buttons[i][j].setBackground(COLOR_2);


                }
                pressedStates[i][j] = false;

            }
        }
    }

    // Check if cell is within the board boundaries
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }



    //move method

    private void Move(int i, int j) {
        Piece myPiece= (Piece) buttons[i][j].getComponent(0);
        if (possibleCells.size()>0) {
            for (int k = 0; k < 2; k++) {
                final int p = k;
                possibleCells.get(k).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        possibleCells.get(p).add(myPiece);
                        buttons[i][j].remove(myPiece);
                    }
                });
            }
        }
        enableBoard();
    }



    //Disable buttons except those highlighted
    private void disableBoard(){

        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                buttons[i][j].setEnabled(false);
                for (int a=0;a<possibleCells.size();a++){
                    possibleCells.get(a).setEnabled(true);
                }

            }
        }
    }


    //Enable all buttons
    private void enableBoard(){
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                    buttons[i][j].setEnabled(true);
            }
        }
    }
}

