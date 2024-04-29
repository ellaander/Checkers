

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI 
{
// Inside your constructor or initialization method
Theme one = new Theme(new Color(252,224,61),new Color(115, 14, 65), Color.BLACK, new Color(255, 243, 176),"niblet.png","kernelcobb.png", "nibletking.png", "kernelcobbking.png" );
Theme two = new Theme(new Color(160, 210, 165),new Color(0, 70, 40), new Color(0, 45, 25), new Color(190, 160, 110),"frog1.png","frog2.png", "frog1king.png", "frog2king.png");
Theme three = new Theme(new Color(210, 210, 210),new Color(10, 0, 90), Color.BLACK, new Color(210, 210, 210),"colorblindPiece1.png","colorblindPiece2.png", "colorblindPiece1King.png", "colorblindPiece2King.png");
Theme four = new Theme(new Color(252,224,61),new Color(115, 14, 65), Color.BLACK, new Color(255, 243, 176),"cobberRing.png","cobberLogo.png", "cobberRingKing.png", "cobberLogoKing.png");
Theme five = new Theme(new Color(180, 0, 90),new Color(255, 200, 210), Color.BLACK, new Color(255, 190, 190),"heart1.png","heart2.png", "heart1King.png", "heart2King.png");
Theme six = new Theme(new Color(10, 0, 90),new Color(210, 210, 210), new Color(10, 0, 90), new Color(240,240,240),"colorblindPiece1.png","colorblindPiece2.png", "colorblindPiece1King.png", "colorblindPiece2King.png");

Theme theme = new Theme();

    static JFrame frame;
    private JPanel mainPanel;
    private JPanel howToPlayPanel;
    private JPanel settingsPanel;
    public static CardLayout cardLayout;
    private Clip clip;
    FloatControl  fc; 
    float previousVolume = 0;
    float currentVolume = 0;
    boolean mute = false; 
    JSlider slider;
    
    Piece piece = new Piece();

    //boolean for computer- 
    //if boolean = true, then the player plays against a computer
    //if boolean = false, then the player plays against another player
    public boolean computerPlays;
    
    //Theme colorblind1Theme = new Theme();
    //Theme colorblind2Theme = new Theme();

    public void playMusic(String filename) {
        try {
            // Load the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            clip.open(audioInputStream);
    
            // Play the sound
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public GUI() 
    {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        //Initialize JPanels
        mainPanel = new JPanel(null);
        howToPlayPanel = new JPanel(null);
        settingsPanel = new JPanel(null);

        //Set JPanels sizes
        Dimension screenSize = new Dimension(900, 750);
        mainPanel.setPreferredSize(screenSize);
        howToPlayPanel.setPreferredSize(screenSize);
        settingsPanel.setPreferredSize(screenSize);

        // default theme 
        //String player1KingImage = "frog1King.png";
        //String player2KingImage = "frog2King.png";
        theme.setTheme(four);
        Color backgroundColor = one.getBackgroundColor();
        Color textColor = one.getForegroundColor();
        Color backgroundButtonColor = one.getBackgroundButtonColor();
        Color foregroundButtonColor = one.getForegroundButtonColor();

        
        //home component variables 
        int homey1 = 380;
        int homey2 = 475;
        int homey3 = 570;
        int homewidth = 550;
        int homeheight = 80;
        int screenSizeX = 900;

        //settings component variables
        int settingy1 = 200;
        int settingy2 = 500;
        int settingy3 = 600;
        int settingx1 = 275;
        int settingx2 = 450;
        int settingx3 = 625;
        int settingwidth = 150;
        int settingheight = 75;
        int sliderX = settingx3 + settingwidth / 2 + 50;
        
        mainPanel.setBackground(backgroundColor);
        settingsPanel.setBackground(backgroundColor);
        howToPlayPanel.setBackground(new Color(230,230,230));
        
// Home Page ----------------------------------------------------------------------------------------------------------

    //JButtons setup - 
    // first, it initializes and creates the button and the title
    // then, it sets the bounds
    // then, it adds the button to the page
        JButton homeComponent2 = new JButton("Play 1 v 1");
        homeComponent2.setBounds((screenSizeX/2 - homewidth/2), homey1, (homewidth/2 - 10), homeheight);
        homeComponent2.setFont(new Font("SERIF", Font.BOLD, 20));
        homeComponent2.setBackground(backgroundButtonColor);
        homeComponent2.setForeground(foregroundButtonColor);
        homeComponent2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        homeComponent2.setOpaque(true);
        mainPanel.add(homeComponent2);

        JButton homeComponent3 = new JButton("Settings");
        homeComponent3.setBounds((screenSizeX/2 - homewidth/2), homey2, homewidth, homeheight);
        homeComponent3.setFont(new Font("SERIF", Font.BOLD, 20));
        homeComponent3.setBackground(backgroundButtonColor);
        homeComponent3.setForeground(foregroundButtonColor);
        homeComponent3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        homeComponent3.setOpaque(true);
        mainPanel.add(homeComponent3);

        JButton homeComponent4 = new JButton("How to Play");
        homeComponent4.setBounds((screenSizeX/2 - homewidth/2), homey3, homewidth, homeheight);
        homeComponent4.setFont(new Font("SERIF", Font.BOLD, 20));
        homeComponent4.setBackground(backgroundButtonColor);
        homeComponent4.setForeground(foregroundButtonColor);
        homeComponent4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        homeComponent4.setOpaque(true);
        mainPanel.add(homeComponent4);

        JButton homeComponent5 = new JButton("Play against the Computer");
        homeComponent5.setBounds((screenSizeX/2 + 10), homey1, (homewidth/2 - 10), homeheight);
        homeComponent5.setFont(new Font("SERIF", Font.BOLD, 20));
        homeComponent5.setBackground(backgroundButtonColor);
        homeComponent5.setForeground(foregroundButtonColor);
        homeComponent5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        homeComponent5.setOpaque(true);
        mainPanel.add(homeComponent5); 


        JLabel cobber = new JLabel("COBBER");
        cobber.setBounds(screenSizeX/2 - 269, 75, 538, 125);
        cobber.setFont(new Font("SERIF", Font.BOLD, 125));
        cobber.setBackground(backgroundColor);
        cobber.setForeground(textColor);
        cobber.setOpaque(true);
        //cobber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); (for figuring out width for centering)
        mainPanel.add(cobber);

        JLabel checkers = new JLabel("CHECKERS");
        checkers.setBounds(screenSizeX/2 - 355, 185, 710, 125);
        checkers.setFont(new Font("SERIF", Font.BOLD, 125));
        checkers.setBackground(backgroundColor);
        checkers.setForeground(textColor);
        cobber.setOpaque(true);
        //checkers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); (for figuring out width for centering)
        mainPanel.add(checkers);

        // Text panel setup
        JLabel rectangle = new JLabel();
        rectangle.setBounds(screenSizeX/2 - 375, 70, 750, 250);
        rectangle.setBackground(backgroundColor);
        //rectangle.setForeground(backgroundColor);
        rectangle.setOpaque(true);
        rectangle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        mainPanel.add(rectangle);
// settings page ------------------------------------------------------------------------------------------------------        
    //JButtons setup - 
    // first, it initializes and creates the button and the title
    // then, it sets the bounds
    // then, it adds the button to the page
        JButton settingsComponent1 = new JButton("Concordia 1");
        settingsComponent1.setBounds((settingx1 - settingwidth/2), settingy2, settingwidth, settingheight);
        settingsComponent1.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent1.setBackground(backgroundButtonColor);
        settingsComponent1.setForeground(foregroundButtonColor);
        settingsComponent1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent1.setOpaque(true);
        settingsPanel.add(settingsComponent1);

        JButton settingsComponent2 = new JButton("Frogs");
        settingsComponent2.setBounds((settingx2 - settingwidth/2), settingy2, settingwidth, settingheight);
        settingsComponent2.setBackground(backgroundButtonColor);
        settingsComponent2.setForeground(foregroundButtonColor);
        settingsComponent2.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent2.setOpaque(true);
        settingsPanel.add(settingsComponent2);

        JButton settingsComponent3 = new JButton("Colorblind 1");
        settingsComponent3.setBounds((settingx3 - settingwidth/2), settingy2, settingwidth, settingheight);
        settingsComponent3.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent3.setBackground(backgroundButtonColor);
        settingsComponent3.setForeground(foregroundButtonColor);
        settingsComponent3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent3.setOpaque(true);
        settingsPanel.add(settingsComponent3);

        JButton settingsComponent4 = new JButton("Cool Revenge");
        settingsComponent4.setBounds((settingx1 - settingwidth/2), settingy1, settingwidth, settingheight);
        settingsComponent4.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent4.setBackground(backgroundButtonColor);
        settingsComponent4.setForeground(foregroundButtonColor);
        settingsComponent4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent4.setOpaque(true);
        settingsPanel.add(settingsComponent4);

        JButton settingsComponent5 = new JButton("July");
        settingsComponent5.setBounds((settingx2 - settingwidth/2), settingy1, settingwidth, settingheight);
        settingsComponent5.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent5.setBackground(backgroundButtonColor);
        settingsComponent5.setForeground(foregroundButtonColor);
        settingsComponent5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent5.setOpaque(true);
        settingsPanel.add(settingsComponent5);

        JButton settingsComponent6 = new JButton("Summer");
        settingsComponent6.setBounds((settingx3 - settingwidth/2), settingy1, settingwidth, settingheight);
        settingsComponent6.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent6.setBackground(backgroundButtonColor);
        settingsComponent6.setForeground(foregroundButtonColor);
        settingsComponent6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent6.setOpaque(true);
        settingsPanel.add(settingsComponent6);

        JButton settingsComponent7 = new JButton("Concordia 2");
        settingsComponent7.setBounds((settingx1 - settingwidth/2), settingy3, settingwidth, settingheight);
        settingsComponent7.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent7.setBackground(backgroundButtonColor);
        settingsComponent7.setForeground(foregroundButtonColor);
        settingsComponent7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent7.setOpaque(true);
        settingsPanel.add(settingsComponent7);

        JButton settingsComponent8 = new JButton("Pink");
        settingsComponent8.setBounds((settingx2 - settingwidth/2), settingy3, settingwidth, settingheight);
        settingsComponent8.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent8.setBackground(backgroundButtonColor);
        settingsComponent8.setForeground(foregroundButtonColor);
        settingsComponent8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent8.setOpaque(true);
        settingsPanel.add(settingsComponent8);

        JButton settingsComponent9 = new JButton("Colorblind 2");
        settingsComponent9.setBounds((settingx3 - settingwidth/2), settingy3, settingwidth, settingheight);
        settingsComponent9.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent9.setBackground(backgroundButtonColor);
        settingsComponent9.setForeground(foregroundButtonColor);
        settingsComponent9.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsComponent9.setOpaque(true);
        settingsPanel.add(settingsComponent9);

        JButton settingsComponent12 = new JButton("Mute");
        settingsComponent12.setBounds((settingx3 - settingwidth/2), settingy1 + settingheight + 10, settingwidth, settingheight);
        settingsComponent12.setFont(new Font("SERIF", Font.BOLD, 20));
        settingsComponent12.setBackground(backgroundButtonColor);
        settingsComponent12.setForeground(foregroundButtonColor);
        settingsComponent12.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingsPanel.add(settingsComponent12);

        JButton backButton_Settings = new JButton("Back");
        backButton_Settings.setBounds(50, 50, 100, 50);
        backButton_Settings.setFont(new Font("SERIF", Font.BOLD, 20));
        backButton_Settings.setBackground(backgroundButtonColor);
        backButton_Settings.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton_Settings.setOpaque(true);
        settingsPanel.add(backButton_Settings);

        // adding text to the panel
        JLabel soundLabel = new JLabel("SOUNDS");
        soundLabel.setBounds((screenSizeX/2 - 156), 100, 312, 75); // Set bounds as needed
        soundLabel.setFont(new Font("Times New Roman", Font.BOLD, 75)); // Set font and size
        soundLabel.setForeground(textColor); 
        soundLabel.setBackground(backgroundColor);
        soundLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        soundLabel.setOpaque(true);
        settingsPanel.add(soundLabel);

        JLabel themesLabel = new JLabel("THEMES");
        themesLabel.setBounds((screenSizeX/2 - 165), 400, 330, 75); // Set bounds as needed
        themesLabel.setFont(new Font("Times New Roman", Font.BOLD, 75)); // Set font and size
        themesLabel.setForeground(textColor); 
        themesLabel.setBackground(backgroundColor);
        themesLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        themesLabel.setOpaque(true);
        settingsPanel.add(themesLabel);
        

// How to Play page ---------------------------------------------------------------------------------------------------
        
        // images
        ImageIcon checkersMoveExample = new ImageIcon("checkersMoveExample.png");
        JLabel imageCheckersMoveExample = new JLabel(checkersMoveExample);
        imageCheckersMoveExample.setBounds(525, 50, checkersMoveExample.getIconWidth(), checkersMoveExample.getIconHeight());
        howToPlayPanel.add(imageCheckersMoveExample);

        ImageIcon checkersJumpExample = new ImageIcon("checkersJumpExample.png");
        JLabel imageCheckersJumpExample = new JLabel(checkersJumpExample);
        imageCheckersJumpExample.setBounds(525, 385, checkersJumpExample.getIconWidth(), checkersJumpExample.getIconHeight());
        howToPlayPanel.add(imageCheckersJumpExample);

        // back button
        JButton backButton_HowToPlay = new JButton("Back");
        backButton_HowToPlay.setBounds(30, 10, 100, 50);
        backButton_HowToPlay.setFont(new Font("SERIF", Font.BOLD, 20));
        backButton_HowToPlay.setBackground(Color.WHITE);
        backButton_HowToPlay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton_HowToPlay.setOpaque(true);
        howToPlayPanel.add(backButton_HowToPlay);

        JLabel howtoplayHTPpage = new JLabel("HOW TO PLAY");
        howtoplayHTPpage.setBounds(90, 100, 375, 50);
        howtoplayHTPpage.setFont(new Font("SERIF", Font.BOLD, 50));
        howtoplayHTPpage.setBackground(new Color(230,230,230));
        howtoplayHTPpage.setForeground(Color.BLACK);
        howtoplayHTPpage.setOpaque(true);
        //howtoplayHTPpage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); this is for finding the width
        howToPlayPanel.add(howtoplayHTPpage);

        JTextArea description = new JTextArea(
            "Checkers is a 2-player strategy game where each player is trying \n" + 
            "         to eliminate all of their opponent’s checker pieces. \n\n" +
            "On the 8x8 board, players alternate turns moving diagonally from\n" + 
            "   square to square. Eliminating the opponent’s pieces occurs by \n" + 
            "     jumping over the piece into a non-occupied square directly\n" +
            "   following the opponent’s piece. During each turn, a player is\n" +
            "allowed to move 1 square unless the player is jumping or double\n" +
            "                                            jumping.\n\n" +
            "Jumping occurs when both players have a piece adjacent to each\n" +
            "  other’s and the next diagonal square in the line is blank. When\n" +
            "this occurs, the player is allowed to “jump” over the square their\n" +
            "   opponent’s piece occupies and move their piece to the open\n" +
            "  square. Once a player jumps over their opponent’s piece, the\n" +
            "the opponent’s piece is eliminated and removed from the board.\n\n" +
            "Double jumping works in a very similar way. The difference is the\n" +
            " player can “jump” over 2 of their opponent’s pieces. If the player\n" +
            "  has jumped over one of the opponent’s pieces and there is piece\n" +
            " that could be jumped directly following, the player can jump the\n" +
            " second piece during the same turn. The second jump can occur in either\n" + 
            "                                                  direction\n\n" +
            "  The board is laid out in an 8x8 grid formation with alternating\n" +
            "   colors and the pieces will fall on every other square for 3 rows\n" +
            "   from each side of the board. All of the pieces will fall onto the\n" +
            "                                   same color squares."
        ); //the spacing is weird here because it makes it centered on the jframe 
        description.setFont(new Font("Times New Roman", Font.BOLD, 15));
        description.setBounds(60,175,440,510);
        description.setBackground(new Color(230,230,230));
        description.setOpaque(true);
        howToPlayPanel.add(description);

        JTextArea moveExample = new JTextArea("The piece highlighted in red can move to either\n" +
        "                              yellow square");
        moveExample.setFont(new Font("Times New Roman", Font.BOLD, 12));
        moveExample.setBounds(550,345,300,30);
        moveExample.setBackground(new Color(230,230,230));
        moveExample.setForeground(Color.BLACK);
        moveExample.setOpaque(true);
        howToPlayPanel.add(moveExample);

        JTextArea jumpExample = new JTextArea("The piece highlighted in red can jump over the\n" +
        "       opponent's piece into the yellow square");
        jumpExample.setFont(new Font("Times New Roman", Font.BOLD, 12));
        jumpExample.setBounds(550,680,300,30);
        jumpExample.setBackground(new Color(230,230,230));
        jumpExample.setForeground(Color.BLACK);
        howToPlayPanel.add(jumpExample);

//Add actionListener to JButtons --------------------------------------------------------------------------------------                     
        
        homeComponent2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                computerPlays = false;
                frame.setState(Frame.ICONIFIED);

                new Board(computerPlays, theme.getTheme());

            }
        });
        homeComponent3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cardLayout.show(frame.getContentPane(), "Settings");
            }
        });
        homeComponent4.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cardLayout.show(frame.getContentPane(), "HowToPlay");
            }
        });
        homeComponent5.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                computerPlays = true;
                frame.setState(Frame.ICONIFIED);
                new Board(computerPlays, theme.getTheme());
            }
        });

        //theme: concordia 1 (default)
        settingsComponent1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //theme creation
                theme.setTheme(one);
                System.out.println("Theme one");
                System.out.println(theme.getTheme());
                Color backgroundColor = one.getBackgroundColor();
                Color textColor = one.getForegroundColor();
                Color backgroundButtonColor = one.getBackgroundButtonColor();
                Color foregroundButtonColor = one.getForegroundButtonColor();

                
                //text background colors
                cobber.setBackground(backgroundColor);
                checkers.setBackground(backgroundColor);
                themesLabel.setBackground(backgroundColor);
                soundLabel.setBackground(backgroundColor);
                rectangle.setBackground(backgroundColor);
                //rectangle.setForeground(backgroundColor);
                
                //text colors
                cobber.setForeground(textColor);
                checkers.setForeground(textColor);
                rectangle.setBorder(BorderFactory.createLineBorder(textColor, 3));
                themesLabel.setForeground(textColor);
                themesLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));
                soundLabel.setForeground(textColor);
                soundLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));

                //background colors 
                homeComponent2.setBackground(backgroundButtonColor);
                homeComponent3.setBackground(backgroundButtonColor);
                homeComponent4.setBackground(backgroundButtonColor);
                homeComponent5.setBackground(backgroundButtonColor);
                settingsComponent1.setBackground(backgroundButtonColor);
                settingsComponent2.setBackground(backgroundButtonColor);
                settingsComponent3.setBackground(backgroundButtonColor);
                settingsComponent4.setBackground(backgroundButtonColor);
                settingsComponent5.setBackground(backgroundButtonColor);
                settingsComponent6.setBackground(backgroundButtonColor);
                settingsComponent7.setBackground(backgroundButtonColor);
                settingsComponent8.setBackground(backgroundButtonColor);
                settingsComponent9.setBackground(backgroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                backButton_Settings.setBackground(backgroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                slider.setBackground(backgroundButtonColor);

                //text colors
                homeComponent2.setForeground(foregroundButtonColor);
                homeComponent3.setForeground(foregroundButtonColor);
                homeComponent4.setForeground(foregroundButtonColor);
                homeComponent5.setForeground(foregroundButtonColor);
                settingsComponent1.setForeground(foregroundButtonColor);
                settingsComponent2.setForeground(foregroundButtonColor);
                settingsComponent3.setForeground(foregroundButtonColor);
                settingsComponent4.setForeground(foregroundButtonColor);
                settingsComponent5.setForeground(foregroundButtonColor);
                settingsComponent6.setForeground(foregroundButtonColor);
                settingsComponent7.setForeground(foregroundButtonColor);
                settingsComponent8.setForeground(foregroundButtonColor);
                settingsComponent9.setForeground(foregroundButtonColor);
                backButton_Settings.setForeground(foregroundButtonColor);
                settingsComponent12.setForeground(foregroundButtonColor);
                slider.setForeground(foregroundButtonColor);
                mainPanel.setBackground(backgroundColor);
                settingsPanel.setBackground(backgroundColor);
            }
        });

        //theme: frogs
        settingsComponent2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //theme creation
                theme.setTheme(two);
                System.out.println("Theme two");
                System.out.println(theme.getTheme());
                Color textColor = two.getForegroundColor();
                Color backgroundColor = two.getBackgroundColor();
                Color foregroundButtonColor = two.getForegroundButtonColor();
                Color backgroundButtonColor = two.getBackgroundButtonColor();


                //text background colors
                cobber.setBackground(backgroundColor);
                checkers.setBackground(backgroundColor);
                themesLabel.setBackground(backgroundColor);
                soundLabel.setBackground(backgroundColor);
                rectangle.setBackground(backgroundColor);
                //rectangle.setForeground(backgroundColor);
                
                //text colors
                cobber.setForeground(textColor);
                checkers.setForeground(textColor);
                rectangle.setBorder(BorderFactory.createLineBorder(textColor, 3));
                themesLabel.setForeground(textColor);
                themesLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));
                soundLabel.setForeground(textColor);
                soundLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));

                //background colors 
                homeComponent2.setBackground(backgroundButtonColor);
                homeComponent3.setBackground(backgroundButtonColor);
                homeComponent4.setBackground(backgroundButtonColor);
                homeComponent5.setBackground(backgroundButtonColor);
                settingsComponent1.setBackground(backgroundButtonColor);
                settingsComponent2.setBackground(backgroundButtonColor);
                settingsComponent3.setBackground(backgroundButtonColor);
                settingsComponent4.setBackground(backgroundButtonColor);
                settingsComponent5.setBackground(backgroundButtonColor);
                settingsComponent6.setBackground(backgroundButtonColor);
                settingsComponent7.setBackground(backgroundButtonColor);
                settingsComponent8.setBackground(backgroundButtonColor);
                settingsComponent9.setBackground(backgroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                backButton_Settings.setBackground(backgroundButtonColor);
                slider.setBackground(backgroundButtonColor);

                //text colors
                homeComponent2.setForeground(foregroundButtonColor);
                homeComponent3.setForeground(foregroundButtonColor);
                homeComponent4.setForeground(foregroundButtonColor);
                homeComponent5.setForeground(foregroundButtonColor);
                settingsComponent1.setForeground(foregroundButtonColor);
                settingsComponent2.setForeground(foregroundButtonColor);
                settingsComponent3.setForeground(foregroundButtonColor);
                settingsComponent4.setForeground(foregroundButtonColor);
                settingsComponent5.setForeground(foregroundButtonColor);
                settingsComponent6.setForeground(foregroundButtonColor);
                settingsComponent7.setForeground(foregroundButtonColor);
                settingsComponent8.setForeground(foregroundButtonColor);
                settingsComponent9.setForeground(foregroundButtonColor);
                settingsComponent12.setForeground(foregroundButtonColor);
                backButton_Settings.setForeground(foregroundButtonColor);
                slider.setForeground(foregroundButtonColor);
                mainPanel.setBackground(backgroundColor);
                settingsPanel.setBackground(backgroundColor);
            }
        });

        //theme: colorblind 1
        settingsComponent3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //theme creation
                theme.setTheme(three);
                System.out.println("Theme three");
                System.out.println(theme.getTheme());
                Color textColor = three.getForegroundColor();
                Color backgroundColor = three.getBackgroundColor();
                Color foregroundButtonColor = three.getForegroundButtonColor();
                Color backgroundButtonColor = three.getBackgroundButtonColor();


                //text background colors
                cobber.setBackground(backgroundColor);
                checkers.setBackground(backgroundColor);
                themesLabel.setBackground(backgroundColor);
                soundLabel.setBackground(backgroundColor);
                rectangle.setBackground(backgroundColor);
                //rectangle.setForeground(backgroundColor);
                
                //text colors
                cobber.setForeground(textColor);
                checkers.setForeground(textColor);
                rectangle.setBorder(BorderFactory.createLineBorder(textColor, 3));
                themesLabel.setForeground(textColor);
                themesLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));
                soundLabel.setForeground(textColor);
                soundLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));

                //background colors 
                homeComponent2.setBackground(backgroundButtonColor);
                homeComponent3.setBackground(backgroundButtonColor);
                homeComponent4.setBackground(backgroundButtonColor);
                homeComponent5.setBackground(backgroundButtonColor);
                settingsComponent1.setBackground(backgroundButtonColor);
                settingsComponent2.setBackground(backgroundButtonColor);
                settingsComponent3.setBackground(backgroundButtonColor);
                settingsComponent4.setBackground(backgroundButtonColor);
                settingsComponent5.setBackground(backgroundButtonColor);
                settingsComponent6.setBackground(backgroundButtonColor);
                settingsComponent7.setBackground(backgroundButtonColor);
                settingsComponent8.setBackground(backgroundButtonColor);
                settingsComponent9.setBackground(backgroundButtonColor);
                backButton_Settings.setBackground(backgroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                slider.setBackground(backgroundButtonColor);


                //text colors
                homeComponent2.setForeground(foregroundButtonColor);
                homeComponent3.setForeground(foregroundButtonColor);
                homeComponent4.setForeground(foregroundButtonColor);
                homeComponent5.setForeground(foregroundButtonColor);
                settingsComponent1.setForeground(foregroundButtonColor);
                settingsComponent2.setForeground(foregroundButtonColor);
                settingsComponent3.setForeground(foregroundButtonColor);
                settingsComponent4.setForeground(foregroundButtonColor);
                settingsComponent5.setForeground(foregroundButtonColor);
                settingsComponent6.setForeground(foregroundButtonColor);
                settingsComponent7.setForeground(foregroundButtonColor);
                settingsComponent8.setForeground(foregroundButtonColor);
                settingsComponent9.setForeground(foregroundButtonColor);
                backButton_Settings.setForeground(foregroundButtonColor);
                settingsComponent12.setForeground(foregroundButtonColor);
                slider.setForeground(foregroundButtonColor);

                mainPanel.setBackground(backgroundColor);
                settingsPanel.setBackground(backgroundColor);
                
            }
        });

        settingsComponent4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Stop any previously playing sound
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                    clip.close();
                }

                // Play sound in a loop (indefinitely)
                PlayMusic("CoolRevenge2.wav");
            }
        });


        settingsComponent5.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Stop any previously playing sound
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                    clip.close();
                }
                // Play sound in a loop (indefinitely)
                PlayMusic("July2.wav");
            }
        });

        settingsComponent6.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Stop any previously playing sound
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                    clip.close();
                }
                
                // Play sound in a loop (indefinitely)
                PlayMusic("Summer2.wav");
            }
        });

        //theme: concordia 2
        settingsComponent7.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //theme creation
                theme.setTheme(four);
                System.out.println("Theme four");
                System.out.println(theme.getTheme());
                Color backgroundColor = four.getBackgroundColor();
                Color textColor = four.getForegroundColor();
                Color backgroundButtonColor = four.getBackgroundButtonColor();
                Color foregroundButtonColor = four.getForegroundButtonColor();

                //text background colors
                cobber.setBackground(backgroundColor);
                checkers.setBackground(backgroundColor);
                themesLabel.setBackground(backgroundColor);
                soundLabel.setBackground(backgroundColor);
                rectangle.setBackground(backgroundColor);
                //rectangle.setForeground(backgroundColor);
                
                //text colors
                cobber.setForeground(textColor);
                checkers.setForeground(textColor);
                rectangle.setBorder(BorderFactory.createLineBorder(textColor, 3));
                themesLabel.setForeground(textColor);
                themesLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));
                soundLabel.setForeground(textColor);
                soundLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));

                //background colors 
                homeComponent2.setBackground(backgroundButtonColor);
                homeComponent3.setBackground(backgroundButtonColor);
                homeComponent4.setBackground(backgroundButtonColor);
                homeComponent5.setBackground(backgroundButtonColor);
                settingsComponent1.setBackground(backgroundButtonColor);
                settingsComponent2.setBackground(backgroundButtonColor);
                settingsComponent3.setBackground(backgroundButtonColor);
                settingsComponent4.setBackground(backgroundButtonColor);
                settingsComponent5.setBackground(backgroundButtonColor);
                settingsComponent6.setBackground(backgroundButtonColor);
                settingsComponent7.setBackground(backgroundButtonColor);
                settingsComponent8.setBackground(backgroundButtonColor);
                settingsComponent9.setBackground(backgroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                backButton_Settings.setBackground(backgroundButtonColor);
                slider.setBackground(backgroundButtonColor);


                //text colors
                homeComponent2.setForeground(foregroundButtonColor);
                homeComponent3.setForeground(foregroundButtonColor);
                homeComponent4.setForeground(foregroundButtonColor);
                homeComponent5.setForeground(foregroundButtonColor);
                settingsComponent1.setForeground(foregroundButtonColor);
                settingsComponent2.setForeground(foregroundButtonColor);
                settingsComponent3.setForeground(foregroundButtonColor);
                settingsComponent4.setForeground(foregroundButtonColor);
                settingsComponent5.setForeground(foregroundButtonColor);
                settingsComponent6.setForeground(foregroundButtonColor);
                settingsComponent7.setForeground(foregroundButtonColor);
                settingsComponent8.setForeground(foregroundButtonColor);
                settingsComponent9.setForeground(foregroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                backButton_Settings.setForeground(foregroundButtonColor);
                slider.setForeground(foregroundButtonColor);
                mainPanel.setBackground(backgroundColor);
                settingsPanel.setBackground(backgroundColor);

            }
        });

        //theme: pink
        settingsComponent8.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //theme creation
                theme.setTheme(five);
                System.out.println("Theme five");
                System.out.println(theme.getTheme());
                Color textColor = five.getForegroundColor();
                Color backgroundColor = five.getBackgroundColor();
                Color foregroundButtonColor = five.getForegroundButtonColor();
                Color backgroundButtonColor = five.getBackgroundButtonColor();

                //text background colors
                cobber.setBackground(backgroundColor);
                checkers.setBackground(backgroundColor);
                themesLabel.setBackground(backgroundColor);
                soundLabel.setBackground(backgroundColor);
                rectangle.setBackground(backgroundColor);
                //rectangle.setForeground(backgroundColor);
                
                //text colors
                cobber.setForeground(textColor);
                checkers.setForeground(textColor);
                rectangle.setBorder(BorderFactory.createLineBorder(textColor, 3));
                themesLabel.setForeground(textColor);
                themesLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));
                soundLabel.setForeground(textColor);
                soundLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));

                //background colors 
                homeComponent2.setBackground(backgroundButtonColor);
                homeComponent3.setBackground(backgroundButtonColor);
                homeComponent4.setBackground(backgroundButtonColor);
                homeComponent5.setBackground(backgroundButtonColor);
                settingsComponent1.setBackground(backgroundButtonColor);
                settingsComponent2.setBackground(backgroundButtonColor);
                settingsComponent3.setBackground(backgroundButtonColor);
                settingsComponent4.setBackground(backgroundButtonColor);
                settingsComponent5.setBackground(backgroundButtonColor);
                settingsComponent6.setBackground(backgroundButtonColor);
                settingsComponent7.setBackground(backgroundButtonColor);
                settingsComponent8.setBackground(backgroundButtonColor);
                settingsComponent9.setBackground(backgroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                backButton_Settings.setBackground(backgroundButtonColor);
                slider.setBackground(backgroundButtonColor);


                //text colors
                homeComponent2.setForeground(foregroundButtonColor);
                homeComponent3.setForeground(foregroundButtonColor);
                homeComponent4.setForeground(foregroundButtonColor);
                homeComponent5.setForeground(foregroundButtonColor);
                settingsComponent1.setForeground(foregroundButtonColor);
                settingsComponent2.setForeground(foregroundButtonColor);
                settingsComponent3.setForeground(foregroundButtonColor);
                settingsComponent4.setForeground(foregroundButtonColor);
                settingsComponent5.setForeground(foregroundButtonColor);
                settingsComponent6.setForeground(foregroundButtonColor);
                settingsComponent7.setForeground(foregroundButtonColor);
                settingsComponent8.setForeground(foregroundButtonColor);
                settingsComponent9.setForeground(foregroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                backButton_Settings.setForeground(foregroundButtonColor);
                slider.setForeground(backgroundButtonColor);
                mainPanel.setBackground(backgroundColor);
                settingsPanel.setBackground(backgroundColor);

            }
        });

        //theme: colorblind 2
        settingsComponent9.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {  

                //theme creation
                theme.setTheme(six);
                System.out.println("Theme six");
                System.out.println(theme.getTheme());
                Color textColor = six.getForegroundColor();
                Color backgroundColor = six.getBackgroundColor();
                Color foregroundButtonColor = six.getForegroundButtonColor();
                Color backgroundButtonColor = six.getBackgroundButtonColor();

                //text background colors
                cobber.setBackground(backgroundColor);
                checkers.setBackground(backgroundColor);
                themesLabel.setBackground(backgroundColor);
                soundLabel.setBackground(backgroundColor);
                rectangle.setBackground(backgroundColor);
                //rectangle.setForeground(backgroundColor);
                
                //text colors
                cobber.setForeground(textColor);
                checkers.setForeground(textColor);
                rectangle.setBorder(BorderFactory.createLineBorder(textColor, 3));
                themesLabel.setForeground(textColor);
                themesLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));
                soundLabel.setForeground(textColor);
                soundLabel.setBorder(BorderFactory.createLineBorder(textColor, 3));

                //background colors 
                homeComponent2.setBackground(backgroundButtonColor);
                homeComponent3.setBackground(backgroundButtonColor);
                homeComponent4.setBackground(backgroundButtonColor);
                homeComponent5.setBackground(backgroundButtonColor);
                settingsComponent1.setBackground(backgroundButtonColor);
                settingsComponent2.setBackground(backgroundButtonColor);
                settingsComponent3.setBackground(backgroundButtonColor);
                settingsComponent4.setBackground(backgroundButtonColor);
                settingsComponent5.setBackground(backgroundButtonColor);
                settingsComponent6.setBackground(backgroundButtonColor);
                settingsComponent7.setBackground(backgroundButtonColor);
                settingsComponent8.setBackground(backgroundButtonColor);
                settingsComponent9.setBackground(backgroundButtonColor);
                settingsComponent12.setBackground(backgroundButtonColor);
                backButton_Settings.setBackground(backgroundButtonColor);
                slider.setBackground(backgroundButtonColor);


                //text colors
                homeComponent2.setForeground(foregroundButtonColor);
                homeComponent3.setForeground(foregroundButtonColor);
                homeComponent4.setForeground(foregroundButtonColor);
                homeComponent5.setForeground(foregroundButtonColor);
                settingsComponent1.setForeground(foregroundButtonColor);
                settingsComponent2.setForeground(foregroundButtonColor);
                settingsComponent3.setForeground(foregroundButtonColor);
                settingsComponent4.setForeground(foregroundButtonColor);
                settingsComponent5.setForeground(foregroundButtonColor);
                settingsComponent6.setForeground(foregroundButtonColor);
                settingsComponent7.setForeground(foregroundButtonColor);
                settingsComponent8.setForeground(foregroundButtonColor);
                settingsComponent9.setForeground(foregroundButtonColor);
                settingsComponent12.setForeground(foregroundButtonColor);
                backButton_Settings.setForeground(foregroundButtonColor);
                slider.setForeground(foregroundButtonColor);
                mainPanel.setBackground(backgroundColor);
                settingsPanel.setBackground(backgroundColor);

            }
        });

        settingsComponent12.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                volumeMute(); 
            }
            
        });

        
        slider = new JSlider(-40,6);
        slider.setBounds(settingx2 - settingwidth / 2, settingy1 + settingheight + 10, settingwidth, settingheight);
        settingsPanel.add(slider);
        slider.setBackground(backgroundColor); // Set background color
        slider.setForeground(foregroundButtonColor); // Set foreground color
        slider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //detects when you change the slider
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                currentVolume = slider.getValue();
                if(currentVolume == -40) {
                    currentVolume = -80; 
                }
                System.out.println("Volume:"+currentVolume);
                fc.setValue(currentVolume); 
            } 
           
        });

        //add actionListeners to backButtons
        backButton_HowToPlay.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cardLayout.show(frame.getContentPane(), "Main");
            }
        });
        backButton_Settings.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cardLayout.show(frame.getContentPane(), "Main");
            }
        });

        //create cardLayout
        cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        //add panels to cardLayout
        frame.add(mainPanel, "Main");
        frame.add(howToPlayPanel, "HowToPlay");
        frame.add(settingsPanel, "Settings");

        cardLayout.show(frame.getContentPane(), "Main");

        //JFrame intializations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "plz dont leave :(", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        frame.setTitle("Checkers");

        //start playing music when opened
        PlayMusic("July2.wav");
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void PlayMusic(String location) {
        try {
            File musicpath = new File(location);
            if (musicpath.exists()) {
                // Stop previous music
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN); 
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void volumeUp() {
        currentVolume += 10.0f;
        if (currentVolume > 6.0f) {
            currentVolume = 6.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeDown() {
        currentVolume -= 10.0f;
        if (currentVolume < -80.0f) {
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeMute(){
        if (mute == false) {
            previousVolume = currentVolume;
            currentVolume = -80.0f; 
            fc.setValue(currentVolume);
            mute = true; 
        }
        else if (mute == true){
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute = false; 
        }
    }

    
}
