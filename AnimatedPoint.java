import java.awt.*;

public class AnimatedPoint extends Thread {
    Point point;
    MyCanvas canvas;
    public AnimatedPoint(MyCanvas canvas, Point point){
        this.point = point;
        this.canvas = canvas;
    }

    @Override
    public void run(){
        canvas.animation(point);
    }
}
