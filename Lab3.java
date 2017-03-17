import java.awt.*;
import javax.swing.*;

public class Lab3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){ createGUI(); }
        });
    }

    private static void createGUI() {
        JFrame frame = new MyFrame(); //incapsulation
        frame.setVisible(true); //ought to control visibility from main class
    }
}
