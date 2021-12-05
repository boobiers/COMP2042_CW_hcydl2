package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class InfoFrame extends JFrame{

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final String DEF_TITLE = "Game Information";
    private static final int TEXT_SIZE = 20;
    private static final Color BG_COLOR = Color.WHITE;
    private JLabel label;

    public InfoFrame(){

        initialize();
        InfoText();
        this.add(label);
    }

    public void initialize(){

        this.setTitle(DEF_TITLE);
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.autoLocate();
        this.setVisible(true);


    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    private void InfoText(){

        label = new JLabel();
        label.setText("");

        label.setFont(new Font(null,Font.PLAIN,TEXT_SIZE));
        label.setBounds(30,30,450,350);
        label.setOpaque(true);
        label.setBackground(BG_COLOR);
        label.setForeground(Color.BLUE);

    }

}
