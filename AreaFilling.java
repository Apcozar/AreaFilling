/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apcoz
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AreaFilling implements Runnable {

    private JFrame frame;
    private final ArrayList<Point>  iniFin  = new ArrayList<>(2);
    private final  int count=0;
    
     
    public static void main(String[] args) {
        EventQueue.invokeLater(new AreaFilling());
    }

    @Override
    public void run() {
        initGUI();
    }

    public void initGUI() {
        frame = new JFrame("Areafilling");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(createPixels());
      
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createPixels() {
        int width = 30;
        int height = 30;
        int indice = 0;
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(height, width, 0, 0));

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                PixelPanel pixelPanel = new PixelPanel();
                Point point = new Point (column,row);
                pixelPanel.pos=point;
                pixelPanel.index=indice;
                indice++;
                pixelPanel.addMouseListener(new ColorListener(pixelPanel,panel));
                panel.add(pixelPanel);
            }
        }
        
        return panel;
    }

    public class PixelPanel extends JPanel {

        private static final int PIXEL_SIZE = 20;

        private Color backgroundColor;
        private Point pos;
        private int index;
        

        public PixelPanel() {
            this.index=index;
            this.pos=pos;
            this.backgroundColor = Color.WHITE;
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
        }
        
        public Color getBackgroundColor() {
            return backgroundColor;
        }
       
        public void setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(getBackgroundColor());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public class ColorListener  extends MouseAdapter {
       
        private final PixelPanel pixel;
        private final JPanel panel;
       
        public ColorListener(PixelPanel pixel,JPanel panel) {
            this.pixel = pixel;
            this.panel=panel;
        }
        
        @Override
        public void mousePressed(MouseEvent event) {
            
            if((event.getButton() == MouseEvent.BUTTON1)){
                pixel.setBackgroundColor(Color.BLUE);
                pixel.repaint();
            }else if (event.getButton() != MouseEvent.BUTTON2){
                iniFin.add(0,pixel.pos);
                
                boundaryFill4(panel,iniFin.get(0).x,iniFin.get(0).y);
                //boundaryFill8(panel,iniFin.get(0).x,iniFin.get(0).y);            } 
        }
    }
    } 
    public void boundaryFill4(JPanel panel, int x, int y){
        int a;
        PixelPanel aux;
        a = (y*30)+x;
        aux = (PixelPanel) panel.getComponent(a);
        if(aux.backgroundColor != Color.BLUE &&
            aux.backgroundColor != Color.RED){
            aux.setBackgroundColor(Color.RED);
            boundaryFill4(panel,x + 1, y); 
            boundaryFill4(panel,x, y + 1);
            boundaryFill4(panel,x - 1, y);
            boundaryFill4(panel,x, y - 1);
            

            
            aux.setBackgroundColor(Color.CYAN);
            aux.repaint();
        }
    }
    
    void boundaryFill8(JPanel panel, int x, int y){ 
        int a;
        PixelPanel aux;
        a = (y*30)+x;
        aux = (PixelPanel) panel.getComponent(a);
        if(aux.backgroundColor != Color.BLUE &&
                aux.backgroundColor != Color.GREEN){
            aux.setBackgroundColor(Color.GREEN);
            boundaryFill8(panel,x + 1, y); 
            boundaryFill8(panel,x, y + 1); 
            boundaryFill8(panel,x - 1, y); 
            boundaryFill8(panel,x, y - 1); 
            boundaryFill8(panel,x - 1, y - 1); 
            boundaryFill8(panel,x - 1, y + 1); 
            boundaryFill8(panel,x + 1, y - 1); 
            boundaryFill8(panel,x + 1, y + 1); 
        } 
    }   
}

