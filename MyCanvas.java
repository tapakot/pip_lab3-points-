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
    Graphics2D g2d;
    Point aniPoint;
    int aniPointR= 50;
    int pointR = 3;
    volatile boolean aniMode;
    //res Point instead of x,y

    public MyCanvas(double r, JTextField text){
        radUser = r;
        lastPoint = text;
        //aniMode = new ThreadLocal<>();
        aniMode = false;
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
        g2d = (Graphics2D)g;

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
        for(int i =0; i<countOfPoints; i++){
            boolean inFigure = false;
            realPoints[i] = getRealCoordinatesFromFields(userPoints[i], radUser);
            double x = userPoints[i].x;
            double y = userPoints[i].y;
            if((x < 0)&&(y<=0)&&(x>(-radUser/2))&&(y>-radUser)) { inFigure = true; }
            if((x < 0)&&(y>0)&&(y < x+radUser)) { inFigure = true; } //y=x+R
            if((x > 0)&&(y>0)&&(x*x + y*y < radUser*radUser)){ inFigure = true; }
            if(inFigure){
                g2d.setColor(Color.orange);
            }else{
                g2d.setColor(Color.red);
            }
            g2d.fillRect(width/2 + realPoints[i].x - 1, height/2 - realPoints[i].y - 1, 3, 3);
        }

        if (aniMode) {
            g2d.setColor(Color.red);
            g2d.fillRect(width/2 + aniPoint.x - aniPointR/2,height/2 - aniPoint.y -aniPointR/2, aniPointR, aniPointR);
            aniMode = false;
        }
    }

    void addPoint(double userX, double userY){
        boolean inFigure = false;
        if((userX < 0)&&(userY<=0)&&(userX>(-radUser/2))&&(userY>-radUser)) { inFigure = true; }
        if((userX < 0)&&(userY>0)&&(userY < userX+radUser)) { inFigure = true; } //y=x+R
        if((userX > 0)&&(userY>0)&&(userX*userX + userY*userY < radUser*radUser)){ inFigure = true; }
        if(!inFigure){
            Point aniPoint = getRealCoordinatesFromFields(new Point2D.Double(userX, userY), radUser);
            /*Thread aniThread = new Thread(new Runnable(){
                public void run(){
                    animation(aniPoint);
                }
            });*/
            AnimatedPoint myAniPoint = new AnimatedPoint(this, aniPoint);
            Thread aniThread = new Thread(myAniPoint);
            aniThread.start();
        }
        userPoints[countOfPoints] = new Point2D.Double(userX, userY);
        countOfPoints++;
        lastPoint.setText(String.format("x = %.2f; y = %.2f.", userX, userY));
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

    public synchronized void  animation(Point aniPoint){
        this.aniPoint = aniPoint;
        aniPointR=50;
        while (aniPointR>pointR) {
            aniMode = true;
            repaint();
            aniPointR-=1;
            try{Thread.sleep(20);}
            catch (InterruptedException e){}
        }
        aniMode = false;
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
