package tankwars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

    public GameWindow(int width, int height, String title, TankWars game) {
        
        JFrame frame = new JFrame(title);
        
        frame.setLocation(0, 0);
        frame.setSize(new Dimension(1280, 960));
        //frame.setPreferredSize(new Dimension(width, height));
        GridLayout layout = new GridLayout(0, 2);
        frame.setLayout(layout);
        
        JPanel panelOne = new JPanel();
        panelOne.setBackground(Color.RED);
        JPanel panelTwo = new JPanel();
        panelTwo.setBackground(Color.GREEN);
        
        /*
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        */
        
        //*********************************************
        //Full Screen
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //*********************************************/        
        
        
        
        frame.add(game, panelOne);
        frame.add(game, panelTwo);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);        
    }
}
