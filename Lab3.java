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
        JFrame frame = new JFrame("******* points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300); //needs to be deleted after packing
        frame.setVisible(true);
    }
}
