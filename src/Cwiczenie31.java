import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Cwiczenie31 extends JFrame {

    private MyComponent komponent;

    private int n = 5;
    private int rr = 0;
    private int gg = 0;
    private int bb = 0;
    private Color kolor = new Color(rr,gg,bb);

    private Rama rama = new Rama("RGB");

    private int Bow(int r, int move, int center) {
        return (int)Math.round(Math.sqrt(r * r - (move-center) * (move-center)));
    }



    public class MyComponent extends JComponent{
        @Override
        protected void paintComponent(Graphics g) {
            int w = getWidth();
            int h = getHeight();
            int r = h/8;

            g.setColor(kolor);


            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            //ramka#1
            g.drawLine(0,0,w/4,h*3/4);
            g.drawLine(w/4,h*3/4,w/4,h/4);
            g.drawLine(w/4,h/4,w/2,h/2);
            g.drawLine(w/2,h/2,w*3/4,h*3/4);
            g.drawLine(w*3/4,h*3/4,w*3/4, h/4);
            g.drawLine(w*3/4,h/4,w,h);

            //ramka#2
            g.drawLine(0,h,w/4,h/4);
            g.drawLine(w/4,h*3/4,w/2,h/2);
            g.drawLine(w/2,h/2,w*3/4,h/4);
            g.drawLine(w*3/4,h*3/4,w,0);



            //kolka
            g.drawOval(w/2-r,h/4-r,r*2,r*2);
            g.drawOval(w/2-r,h*3/4-r,r*2,r*2);

            g.drawOval(w/2-r/2,h/4-r/2,r,r);
            g.drawOval(w/2-r/2,h*3/4-r/2,r,r);



            for(int i = 0; i<n;i++){
                int y = h*i/n;
                int x = w*i/n;

                //górna część
                if(x>0 && x<=w*1/6){
                    g.drawLine(x,3*y,x,0);

                }
                if(x>w*1/6 && x<=w/4){
                    g.drawLine(x,h-3*y,x,0);

                }
                if(x>=w/4 && x<=w/2-r){
                    g.drawLine(x,y,x,0);
                }
                if(x>=w/2+r && x<=w*3/4){
                    g.drawLine(x,h-y,x,0);
                }
                if(x>=w*3/4 && x<=w*5/6){
                    g.drawLine(x,0,x,(3*y)-(2*h));
                }
                if(x>=w*5/6 && x<=w){
                    g.drawLine(x,0,x,3*h-(3*y));
                }

                //dolna część
                if(y<=h && y>=h*3/4+r){
                    g.drawLine(w/3-x/3,y, w*2/3+x/3,y);
                }
                if(y>=h/2 && y<=h*3/4-r){
                    g.drawLine(w/3-x/3,y,x/3,y);
                    g.drawLine(x,y,w-x,y);
                }
                if(y>=h/2 && y<=h*3/4-r){
                    g.drawLine(w-x/3,y,w*2/3+x/3,y);
                }




                //kółka

                //kółko środek górne
                if(x>=w/2-r && x<=w/2){
                    int circleMidUp = Bow(r,x,w/2);

                    g.drawLine(x, h/4 - circleMidUp,x,0 );
                    g.drawLine(x, h/4 + circleMidUp,x,y );
                }
                if(x>=w/2 && x<=w/2+r){
                    int circleMidUp = Bow(r,x,w/2);

                    g.drawLine(x, h/4 - circleMidUp,x,0 );
                    g.drawLine(x, h/4 + circleMidUp,x,h-y );
                }

                //obszar w kółko górnym
                if(y>=h/4-r && y<=h/4+r){
                    int xCircleMidDown = Bow(r,y,h/4);
                    int xCircleMidDownS = Bow(r/2,y,h/4);

                    g.drawLine(w/2-xCircleMidDown,y,w/2-xCircleMidDownS,y);
                    g.drawLine(w/2+xCircleMidDown,y,w/2+xCircleMidDownS,y);
                }
                //małe kółko góra
                if(x>=w/2-r/2 && x<=w/2+r/2){
                    int yCircleMidDownS = Bow(r/2,x,w/2);

                    g.drawLine(x,h/4-yCircleMidDownS,x,h/4+yCircleMidDownS);
                }


                //kólko środek dolne
                if(y>=h*3/4-r && y<=h*3/4){
                    int xCircleMidDown = Bow(r,y,h*3/4);



                    g.drawLine(x,y,w/2+xCircleMidDown,y);
                    g.drawLine(w-x,y,w/2-xCircleMidDown,y);


                    //niekoło
                    g.drawLine(w/3-x/3,y,x/3,y);
                    g.drawLine(w-x/3,y,w*2/3+x/3,y);
                }

                //mniejsze kółko dolne
                if(y>=h*3/4-r/2 && y<=h*3/4+r/2){
                    int xCircleMidDownS = Bow(r/2,y,h*3/4);
                    //mniejsze kołko
                    g.drawLine(w/2-xCircleMidDownS,y,w/2+xCircleMidDownS,y);

                }

                //obszar w kółku
                if(x>=w/2-r && x<=w/2+r){

                    int yCircleMidDown = Bow(r,x,w/2);
                    int yCircleMidDownS = Bow(r/2,x,w/2);

                    g.drawLine(x,h*3/4+yCircleMidDown,x,h*3/4+yCircleMidDownS);
                    g.drawLine(x,h*3/4-yCircleMidDown,x,h*3/4-yCircleMidDownS);


                }
                //dolna czesc dolnego koła
                if(y>=h*3/4 && y<=h*3/4+r){

                    int xCircleMidDown = Bow(r,y,h*3/4);
                    int xCircleMidDownS = Bow(r/2,y,h*3/4);

                    g.drawLine(w*2/3+x/3,y,w/2+xCircleMidDown,y);
                    g.drawLine(w/3-x/3,y,w/2-xCircleMidDown,y);



                    //niekoło
                    g.drawLine(w/3-x/3,y,x/3,y);
                    g.drawLine(w*3/4,y,w*2/3+x/3,y);

                }

                //muszka
                if(y>=h/4 && y<=h*3/4){
                    g.drawLine(w/2,h/2,w/4,y);
                    g.drawLine(w/2,h/2,w*3/4,y);

                    g.drawLine(w*1/6,h/2,w/4,y);
                    g.drawLine(w*5/6,h/2,w*3/4,y);
                }
                //trójkąty lewa i prawa
                if(y>=0&& y<=h){
                    g.drawLine(0,y,w*1/6,h/2);
                    g.drawLine(w*5/6,h/2,w,y);
                }




            }


            super.paintComponent(g);

        }
    }



    public Cwiczenie31(String string){
        super(string);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();

        setBounds(d.width/6, d.height/6, d.width*2/3,d.height*2/3);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(komponent = new MyComponent());
        JPanel panel = new JPanel(new BorderLayout());

        final JSlider slider = new JSlider(1,3000,n);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                n = slider.getValue();
                komponent.repaint();
            }
        });
        panel.add(slider);
        add(panel,BorderLayout.SOUTH);




        JPanel rgb = new JPanel(new BorderLayout());
        final JSlider sliderR = new JSlider(0,255,rr);
        sliderR.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                rr = sliderR.getValue();
                kolor = new Color(rr,gg,bb);
                komponent.repaint();
            }
        });
        final JSlider sliderG = new JSlider(0,255,gg);
        sliderG.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gg = sliderG.getValue();
                kolor = new Color(rr,gg,bb);
                komponent.repaint();
            }
        });
        final JSlider sliderB = new JSlider(0,255,bb);
        sliderB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                bb = sliderB.getValue();
                kolor = new Color(rr,gg,bb);
                komponent.repaint();
            }
        });
        rgb.add(sliderR, BorderLayout.NORTH);
        rgb.add(sliderG);
        rgb.add(sliderB, BorderLayout.SOUTH);

        rama.add(rgb);


        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Plik");

        JMenuItem mi = new JMenuItem("Zapisz");
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    BufferedImage image = new BufferedImage(komponent.getWidth(), komponent.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics2D = image.createGraphics();
                    komponent.paint(graphics2D);
                    ImageIO.write(image,"jpeg", new File("proba4.jpeg"));
                }
                catch(Exception exception)
                {

                }
            }
        });
        m.add(mi);
        mb.add(m);

        mi = new JMenuItem("Zakończ");
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        m.add(mi);
        mb.add(m);

        m = new JMenu("Zmień kolor");
        mi = new JMenuItem("RGB");
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            rama.setVisible(true);

            }
        });
        m.add(mi);
        mb.add(m);





        setJMenuBar(mb);
        setVisible(true);

    }




    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cwiczenie31("Cwiczenie31");
            }
        });
    }
}
