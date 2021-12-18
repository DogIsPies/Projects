import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Rama extends JFrame {


    public Rama (String string){
        super(string);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();

        setBounds(d.width*1/8, d.height*1/8, d.width*1/3,d.height*1/6);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(false);
    }

}
