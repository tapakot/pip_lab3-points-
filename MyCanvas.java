import javax.swing.*;
import java.awt.*;


public class MyCanvas extends JPanel {

    public MyCanvas(){
        setPreferredSize(new Dimension(100, 100));
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        int height = getHeight()-10; //height of the visible canvas
        int width = getWidth()-10;
        int radX = width/3; //used only to paint figures
        int radY = height/3;

        super.paintComponent(g2d);
        this.setBackground(Color.green);

        //figures
        g2d.setColor(Color.blue);
        g2d.fillArc(width/2 - radX, height/2 - radY, radX*2, radY*2, 0, 90);
        g2d.fillRect(width/2 - radX/2, height/2, radX/2, radY);
        int xTriangle[] = {width/2 - radX, width/2, width/2};
        int yTriangle[] = {height/2, height/2 - radY, height/2};
        g2d.fillPolygon(xTriangle, yTriangle, 3);

        //arrows
        g2d.setColor(Color.black);
        g2d.drawLine(10, height/2, width - 10, height/2);
        g2d.drawLine(width - 20, height/2 - 5, width-10, height/2);
        g2d.drawLine(width - 20, height/2 + 5, width - 10, height/2);
        g2d.drawLine(width/2, height - 10, width/2, 10);
        g2d.drawLine(width/2 - 5, 20, width/2, 10);
        g2d.drawLine(width/2 + 5, 20, width/2, 10);

        //marks OX
        g2d.drawLine(width/2 - radX, height/2 - 5, width/2 - radX, height/2 + 5);
        g2d.drawString("-R", width/2 - radX - 7, height/2 + 15);
        g2d.drawLine(width/2 - radX/2, height/2 - 5, width/2 - radX/2, height/2 + 5);
        g2d.drawString("-R/2", width/2 - radX/2 - 15, height/2 + 15);
        g2d.drawLine(width/2 + radX/2, height/2 -5, width/2 + radX/2, height/2 + 5);
        g2d.drawString("R/2", width/2 + radX/2 - 10, height/2 + 15);
        g2d.drawLine(width/2 + radX, height/2 - 5, width/2 + radX, height/2 + 5);
        g2d.drawString("R", width/2 + radX - 4, height/2 + 15);
        g2d.drawString("X", width - 20, height/2 + 15);

        //marks OY
        g2d.drawLine(width/2 - 5, height/2 - radY, width/2 + 5, height/2 - radY);
        g2d.drawString("R", width/2 + 7, height/2 - radY + 5);
        g2d.drawLine(width/2 - 5, height/2 - radY/2, width/2 + 5, height/2 - radY/2);
        g2d.drawString("R/2", width/2 + 7, height/2 - radY/2 + 5);
        g2d.drawLine(width/2 - 5, height/2 + radY/2, width/2 + 5, height/2 + radY/2);        g2d.drawString("R", width/2 + 7, height/2 - radY + 5);
        g2d.drawString("-R/2", width/2 + 7, height/2 + radY/2 + 5);
        g2d.drawLine(width/2 - 5, height/2 + radY, width/2 + 5, height/2 + radY);        g2d.drawString("R", width/2 + 7, height/2 - radY + 5);
        g2d.drawString("-R", width/2 + 7, height/2 + radY + 5);
        g2d.drawString("Y", width/2 + 7, 20);

        //mark 0
        g2d.drawString("0", width/2 + 7, height/2 + 15);
    }
}