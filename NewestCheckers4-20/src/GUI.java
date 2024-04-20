
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI 
{
    

// Inside your constructor or initialization method


     
    static JFrame frame;
    private JPanel mainPanel;
    private JPanel howToPlayPanel;
    private JPanel settingsPanel;
    public static CardLayout cardLayout;
    private Clip clip;

    Piece piece = new Piece();

    //boolean for computer- 
    //if boolean = true, then the player plays against a computer
    //if boolean = false, then the player plays against another player
    public boolean computerPlays;

    ImageIcon checkersDescription = new ImageIcon("checksDecription.png");
    
    ImageIcon checkersMoveExample = new ImageIcon("checkersMoveExample.png");
    ImageIcon checkersJumpExample = new ImageIcon("checkersJumpExample.png");

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
    
    
   

     //themes 
     //Theme nibletVsKernelCobbTheme = new Theme();
    // Theme logoVsRingTheme = new Theme();`
     //Theme colorblind1Theme = new Theme();
     //Theme colorblind2Theme = new Theme();
    // Theme pink = new Theme(); 
     //Theme frogs = new Theme();

    public GUI() 
    {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
     
        
        //home component variables 
        int homey1 = 400;
        int homey2 = 525;
        int homey3 = 650;
        int homewidth = 550;
        int homeheight = 100;
        int screenSizeX = 900;

        //settings component variables
        int settingy1 = 300;
        int settingy2 = 590;
        int settingy3 = 710;
        int settingx1 = 225;
        int settingx2 = 450;
        int settingx3 = 675;
        int settingwidth = 150;
        int settingheight = 100;

        //JButtons setup
        JButton homeComponent2 = new JButton("Play Game");
        JButton homeComponent3 = new JButton("Settings");
        JButton homeComponent4 = new JButton("How to Play");
        JButton homeComponent5 = new JButton("Computer");

        JButton settingsComponent1 = new JButton("Concordia 1");
        JButton settingsComponent2 = new JButton("Frogs");
        JButton settingsComponent3 = new JButton("Colorblind 1");
        JButton settingsComponent4 = new JButton("sound1");
        JButton settingsComponent5 = new JButton("sound2");
        JButton settingsComponent6 = new JButton("sound3");
        JButton settingsComponent7 = new JButton("Concordia 2");
        JButton settingsComponent8 = new JButton("Pink");
        JButton settingsComponent9 = new JButton("Colorblind 2");
        
        
        
        // Method to play and loop the sound
   
        Dimension screenSize = new Dimension(900, 750);
        
        
        // Text panel setup
        JPanel textPanel = new JPanel();
        JTextField text = new JTextField();
        //JLabel text = new JLabel();

        text.setBackground(Color.WHITE);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("SERIF", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("CHECKERS");
        text.setOpaque(true);
        text.setEditable(false);
        
        textPanel.setLayout(new BorderLayout());
        textPanel.add(text);

        textPanel.setBounds(0, 0, 900, 100); //manually set bounds lol

        textPanel.add(text);


        //Add actionListener to JButtons
                                                
        homeComponent2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                computerPlays = false;
                frame.setState(Frame.ICONIFIED);
                new Board(computerPlays);

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
                new Board(computerPlays);

            
            }
        });

        settingsComponent1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

               //changeTheme();

            }
        });

        settingsComponent2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //changeTheme();

            }
        });

        settingsComponent3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //changeTheme();

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
                PlayMusic("CoolRevenge.wav");
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
                PlayMusic("July.wav");
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
                PlayMusic("Summer.wav");
            }
        });

    

        settingsComponent7.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //changeTheme();

            }
        });

        settingsComponent8.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //changeTheme();

            }
        });

        settingsComponent9.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                //changeTheme();

            }
        });

        //Initialize JPanels
        mainPanel = new JPanel(null);
        howToPlayPanel = new JPanel(null);
        settingsPanel = new JPanel(null);

        //Set JPanels sizes
        mainPanel.setPreferredSize(screenSize);
        howToPlayPanel.setPreferredSize(screenSize);
        settingsPanel.setPreferredSize(screenSize);
 
         //home component bounds
         homeComponent2.setBounds((screenSizeX/2 - homewidth/2), homey1, (homewidth - 125), homeheight);
         homeComponent3.setBounds((screenSizeX/2 - homewidth/2), homey2, homewidth, homeheight);
         homeComponent4.setBounds((screenSizeX/2 - homewidth/2), homey3, homewidth, homeheight);
         homeComponent5.setBounds((screenSizeX/2 + homewidth/2 - 100), homey1, homeheight, homeheight); 
 
         //setting component bounds
         settingsComponent1.setBounds((settingx1 - settingwidth/2), settingy2, settingwidth, settingheight);
         settingsComponent2.setBounds((settingx2 - settingwidth/2), settingy2, settingwidth, settingheight);
         settingsComponent3.setBounds((settingx3 - settingwidth/2), settingy2, settingwidth, settingheight);
         settingsComponent4.setBounds((settingx1 - settingwidth/2), settingy1, settingwidth, settingheight);
         settingsComponent5.setBounds((settingx2 - settingwidth/2), settingy1, settingwidth, settingheight);
         settingsComponent6.setBounds((settingx3 - settingwidth/2), settingy1, settingwidth, settingheight);
         settingsComponent7.setBounds((settingx1 - settingwidth/2), settingy3, settingwidth, settingheight);
         settingsComponent8.setBounds((settingx2 - settingwidth/2), settingy3, settingwidth, settingheight);
         settingsComponent9.setBounds((settingx3 - settingwidth/2), settingy3, settingwidth, settingheight);

        //add buttons to mainPanel
        mainPanel.add(homeComponent4);
        mainPanel.add(homeComponent3);
        mainPanel.add(homeComponent2);
        mainPanel.add(homeComponent5);

        mainPanel.add(textPanel); //add textPanel to mainPanel

         //add buttons to settingsPanel
         settingsPanel.add(settingsComponent1);
         settingsPanel.add(settingsComponent2);
         settingsPanel.add(settingsComponent3);
         settingsPanel.add(settingsComponent4);
         settingsPanel.add(settingsComponent5);
         settingsPanel.add(settingsComponent6);
         settingsPanel.add(settingsComponent7);
         settingsPanel.add(settingsComponent8);
         settingsPanel.add(settingsComponent9);


        //Initialize backbuttons
        JButton backButton_HowToPlay = new JButton("Back");
        JButton backButton_Settings = new JButton("Back");

        //setBounds of backbuttons
        backButton_HowToPlay.setBounds(75, 75, 100, 50);
        backButton_Settings.setBounds(75, 75, 100, 50);

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

        //add back buttons to panels
        howToPlayPanel.add(backButton_HowToPlay);
        settingsPanel.add(backButton_Settings);


        JLabel imageCheckersDecsription = new JLabel(checkersDescription);
        //imageCheckersDecsription.setBounds();
        howToPlayPanel.add(imageCheckersDecsription);

        JLabel imageCheckersMoveExample = new JLabel(checkersMoveExample);
        imageCheckersMoveExample.setBounds(10, 20, 400, 400);
        howToPlayPanel.add(imageCheckersMoveExample);

        JLabel imageCheckersJumpExample = new JLabel(checkersJumpExample);
        imageCheckersJumpExample.setBounds(400, 20, 400, 400);
        howToPlayPanel.add(imageCheckersJumpExample);

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
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 
}