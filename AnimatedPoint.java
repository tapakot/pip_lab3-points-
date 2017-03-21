import java.awt.*;
import javax.swing.*;
import java.awt.geom.Point2D;

public class AnimatedPoint implements Runnable {
    MyCanvas canvas;
    Point realPoint;
    int radX;
    int radY;
    public AnimatedPoint(MyCanvas canvas, Point point, int radX, int radY){
        this.canvas = canvas;
        this.realPoint = point;
        this.radX = radX;
        this.radY = radY;
    }

    public void run(){
        canvas.paintImmediately(0,0,100,100);
        canvas.animation();
        return;
    }
}
