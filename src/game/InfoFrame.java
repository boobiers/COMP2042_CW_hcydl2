package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * This is to create the info frame when infor button is clicked in game
 */
public class InfoFrame extends JFrame{

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 500;
    private static final String DEF_TITLE = "Game Information";
    private static final int TEXT_SIZE = 20;
    private static final Color BG_COLOR = Color.WHITE;
    private JLabel label;

    /**
     * This is to run the info frame when it is called
     */
    public InfoFrame(){

        initialize();
        InfoText();
        this.add(label);
    }

    /**
     * this is to initialize the variables in info frame class
     */
    public void initialize(){

        this.setTitle(DEF_TITLE);
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.autoLocate();
        this.setVisible(true);


    }

    /**
     * this is to locate the frame for the info frame
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * this is to show the text when info frame is clicked
     */
    private void InfoText(){

        label = new JLabel();
        label.setText("<html>" + "Game information <br><br>" +
                "<b>Goal:</b><br>" +
                "Destroy all the bricks without losing all 3 balls<br>" +
                "<br>" +
                "<b>Controls:</b>" +
                "<br>" +
                "'A' to move player to the left<br>" +
                "'D' to move player to the right<br>" +
                "'ALT + SHIFT + F1' to open debug console<br>" +
                "'ESC' to pause <br><br> " +
                "<html><font color=red size=4> press 'X' to go back to game menu");


        label.setFont(new Font(null,Font.PLAIN,TEXT_SIZE));
        label.setBounds(30,30,450,350);
        label.setOpaque(true);
        label.setBackground(BG_COLOR);
        label.setForeground(Color.BLUE);

    }

}
