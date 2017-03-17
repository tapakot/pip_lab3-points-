import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame {
    MyFrame(){
        //initialisation
        super("points");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        super.setMinimumSize(new Dimension(400, 150));

        Box mainBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();

        JLabel label1 = new JLabel("X:");
        String[] XVariants = {"-10", "-4", "0", "4", "10"};
        JList list = new JList(XVariants);
        list.setVisibleRowCount(3);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        box1.add(Box.createHorizontalGlue());
        box1.add(label1);
        box1.add(Box.createHorizontalGlue());
        box1.add(new JScrollPane(list));
        box1.add(Box.createHorizontalGlue());

        JLabel label2 = new JLabel("Y:");
        JRadioButton rbtn1 = new JRadioButton("-6");
        JRadioButton rbtn2 = new JRadioButton("0");
        JRadioButton rbtn3 = new JRadioButton("2");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtn1);
        bg.add(rbtn2);
        bg.add(rbtn3);
        box2.add(Box.createHorizontalGlue());
        box2.add(label2);
        box2.add(Box.createHorizontalGlue());
        box2.add(rbtn1);
        box2.add(Box.createHorizontalGlue());
        box2.add(rbtn2);
        box2.add(Box.createHorizontalGlue());
        box2.add(rbtn3);
        box2.add(Box.createHorizontalGlue());

        JLabel label3 = new JLabel("R:");
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 3, 10, 3);
        box3.add(Box.createHorizontalGlue());
        box3.add(label3);
        box3.add(Box.createHorizontalGlue());
        box3.add(slider);
        box3.add(Box.createHorizontalGlue());

        JLabel label4 = new JLabel("last point:");
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(300,20));
        text.setMaximumSize(new Dimension(300, 20));
        box4.add(Box.createHorizontalGlue());
        box4.add(label4);
        box4.add(Box.createHorizontalGlue());
        box4.add(text);
        box4.add(Box.createHorizontalGlue());

        JButton addButton = new JButton("Add");

        mainBox.add(Box.createVerticalGlue());
        mainBox.add(box1);
        mainBox.add(Box.createVerticalGlue());
        mainBox.add(box2);
        mainBox.add(Box.createVerticalGlue());
        mainBox.add(box3);
        mainBox.add(Box.createVerticalGlue());
        mainBox.add(box4);
        mainBox.add(Box.createVerticalGlue());
        mainBox.add(addButton);
        mainBox.add(Box.createVerticalGlue());

        mainPanel.add(mainBox);

        setContentPane(mainPanel);
        pack();
    }

    class MouseL extends MouseAdapter{
        public void mouseClicked(MouseEvent event){

        }
    }
}
