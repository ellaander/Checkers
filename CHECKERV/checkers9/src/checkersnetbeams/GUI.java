package checkersnetbeams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI implements ActionListener
{
    static JFrame frame;
    private JPanel mainPanel;
    private JPanel howToPlayPanel;
    private JPanel settingsPanel;
    public static CardLayout cardLayout;

    ImageIcon howToPlayImage = new ImageIcon("howtoPlayCat.jpeg");
    ImageIcon settingsImage = new ImageIcon("settingsCat.jpeg");

    public GUI() 
    {
        frame = new JFrame();

        //JButtons setup
        JButton homeComponent2 = new JButton("Home Component 2");
        JButton homeComponent3 = new JButton("Home Component 3");
        JButton homeComponent4 = new JButton("Home Component 4");

        //Set up bounds for JButton
        homeComponent2.setBounds(200, 300, 400, 150);
        homeComponent3.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 600, 300, 400, 150);
        homeComponent4.setBounds(200, 550, 175, 175);

        //Add actionListener to JButtons
        homeComponent2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                frame.setState(Frame.ICONIFIED);
                new Board();

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


        //Initialize JPanels
        mainPanel = new JPanel(null);
        howToPlayPanel = new JPanel(null);
        settingsPanel = new JPanel(null);

        //Set JPanels sizes
        mainPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        howToPlayPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        settingsPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        //add buttons to mainPanel
        mainPanel.add(homeComponent4);
        mainPanel.add(homeComponent3);
        mainPanel.add(homeComponent2);

        //Initialize backbuttons
        JButton backButton_HowToPlay = new JButton("Back");
        JButton backButton_Settings = new JButton("Back");

        //setBounds of backbuttons
        backButton_HowToPlay.setBounds(200, 550, 175, 175);
        backButton_Settings.setBounds(200, 550, 175, 175);

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

        //add images to Panels
        JLabel image_how_to_play_Label = new JLabel(howToPlayImage);
        image_how_to_play_Label.setBounds(50, 50, howToPlayImage.getIconWidth(), howToPlayImage.getIconHeight());
        howToPlayPanel.add(image_how_to_play_Label);

        JLabel image_settings_Label = new JLabel(settingsImage);
        image_settings_Label.setBounds(50, 50, settingsImage.getIconWidth(), settingsImage.getIconHeight() - 200);
        settingsPanel.add(image_settings_Label);

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
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit? We'll miss you :(", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });


        frame.setTitle("Checkers");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
