import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class MyFrame extends JFrame implements ChangeListener,  ActionListener, MouseListener{
    double rad = 3;
    MyCanvas myCanvas;
    JSlider slider;
    JButton addButton;
    JList list;
    ButtonGroup bg;
    JTextField text;
    double yValue = 0;
    JRadioButton rbtn1;
    JRadioButton rbtn2;
    JRadioButton rbtn3;
    //make them global and rename

    MyFrame(){
        //initialisation
        super("points");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        super.setMinimumSize(new Dimension(850, 450));
        super.setPreferredSize(getMinimumSize());

        Box mainBox = Box.createVerticalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();

        JLabel label1 = new JLabel("X:");
        String[] XVariants = {"-10", "-4", "0", "4", "10"};
        list = new JList(XVariants);
        list.setVisibleRowCount(3);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        box1.add(Box.createHorizontalGlue());
        box1.add(label1);
        box1.add(Box.createHorizontalGlue());
        box1.add(new JScrollPane(list));
        box1.add(Box.createHorizontalGlue());

        JLabel label2 = new JLabel("Y:");
        rbtn1 = new JRadioButton("-6");
        rbtn1.addActionListener(this);
        rbtn2 = new JRadioButton("0");
        rbtn2.addActionListener(this);
        rbtn2.setSelected(true);
        rbtn3 = new JRadioButton("2");
        rbtn3.addActionListener(this);
        bg = new ButtonGroup();
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
        slider = new JSlider(SwingConstants.HORIZONTAL, 3, 10, 3);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        box3.add(Box.createHorizontalGlue());
        box3.add(label3);
        box3.add(Box.createHorizontalGlue());
        box3.add(slider);
        box3.add(Box.createHorizontalGlue());

        JLabel label4 = new JLabel("last point:");
        text = new JTextField();
        text.setPreferredSize(new Dimension(300,20));
        text.setMaximumSize(new Dimension(300, 20));
        box4.add(Box.createHorizontalGlue());
        box4.add(label4);
        box4.add(Box.createHorizontalGlue());
        box4.add(text);
        box4.add(Box.createHorizontalGlue());

        addButton = new JButton("Add");
        addButton.addMouseListener(this);

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
        myCanvas = new MyCanvas(rad);
        myCanvas.setVisible(true);
        mainPanel.add(myCanvas);

        myCanvas.addPoint(0, 0);
        myCanvas.addPoint(1,5);
        myCanvas.addPoint(4, 2);

        setContentPane(mainPanel);
        pack();
    }

    @Override
    public void stateChanged(ChangeEvent e){
        if (e.getSource()==slider) {
            myCanvas.sliderEvent(slider);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JRadioButton button = (JRadioButton)e.getSource();
        if(button.equals(rbtn1)){
            yValue = -6;
        } else if(button.equals(rbtn2)){
            yValue = 0;
        } else {
            yValue = 2;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e){
        myCanvas.addPoint(Double.parseDouble(list.getSelectedValue().toString()), yValue);
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}
}
