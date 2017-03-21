import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;


public class MyCanvas extends JPanel implements MouseListener {
    int height; //height of the visible canvas
    int width;
    int radX; //used only to paint figures
    int radY;
    double radUser;
    Point2D.Double userPoints[] = new Point2D.Double[100];
    Point realPoints[];
    int countOfPoints = 0;
    JTextField lastPoint;
    //res Point istead of x,y

    public MyCanvas(double r, JTextField text){
        radUser = r;
        lastPoint = text;
        addMouseListener(this);
        setPreferredSize(new Dimension(100, 100));
        super.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        height = getHeight()-10; //height of the visible canvas
        width = getWidth()-10;
        radX = width/3; //used only to paint figures
        radY = height/3;
        realPoints = new Point[100];
        Graphics2D g2d = (Graphics2D)g;

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
        g2d.drawLine(width/2 - 5, height/2 + radY/2, width/2 + 5, height/2 + radY/2);
        g2d.drawString("-R/2", width/2 + 7, height/2 + radY/2 + 5);
        g2d.drawLine(width/2 - 5, height/2 + radY, width/2 + 5, height/2 + radY); 
        g2d.drawString("-R", width/2 + 7, height/2 + radY + 5);
        g2d.drawString("Y", width/2 + 7, 20);

        //mark 0
        g2d.drawString("0", width/2 + 7, height/2 + 15);

        //points
        g2d.setColor(Color.red);
        for(int i =0; i<countOfPoints; i++){
            realPoints[i] = getRealCoordinatesFromFields(userPoints[i], radUser);
            g2d.fillRect(width/2 + realPoints[i].x - 1, height/2 - realPoints[i].y - 1, 3, 3);
        }
    }

    void addPoint(double x, double y){
        userPoints[countOfPoints] = new Point2D.Double(x, y);
        countOfPoints++;
        lastPoint.setText(String.format("x = %.2f; y = %.2f.", x, y));
        this.revalidate();
        this.repaint();
    }

    Point getRealCoordinatesFromFields(Point2D point, double radUser){      //big error because of float to int
        int rx = (int)(point.getX()*radX/radUser);
        int ry = (int)(point.getY()*radY/radUser);
        return new Point(rx, ry);
    }

    Point getRealCoordinatesFromMouse(Point point){
        int realX = point.x - width/2;
        int realY = height/2 - point.y;
        return new Point(realX, realY);

    }

    Point2D.Double getUserCoordinates(Point point, double radUser){
        double xUser = point.x * radUser / radX;
        double yUser = point.y * radUser / radY;
        return new Point2D.Double(xUser, yUser);
    }

    void sliderEvent(JSlider source){
        radUser = source.getValue();
        this.revalidate();
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        realPoints[countOfPoints] = getRealCoordinatesFromMouse(new Point(x, y));
        Point2D.Double userPoint = getUserCoordinates(realPoints[countOfPoints], radUser);
        addPoint(userPoint.x, userPoint.y);
        /*System.out.println("on canvas: "+x+ " " + y);
        System.out.println("user: "+ userPoints[countOfPoints-1].x+" "+userPoints[countOfPoints-1].y);
        System.out.println("real: "+ realPoints[countOfPoints-1].x+" " + realPoints[countOfPoints-1].y);*/
    }
}
