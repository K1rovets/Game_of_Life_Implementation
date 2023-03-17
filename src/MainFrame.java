import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    Font font = new Font("Times New Roman", Font.BOLD, 20);

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setLayout(new BorderLayout());

        MainPanel panel = new MainPanel();
        panel.setPreferredSize(new Dimension(1400, 800));
        add(panel, BorderLayout.NORTH);

        //===========================================================
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout(1,1));
        add(center, BorderLayout.CENTER);

        JPanel centerWest = new JPanel();
        centerWest.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        center.add(centerWest, BorderLayout.WEST);

        JPanel centerEast = new JPanel();
        centerEast.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        center.add(centerEast, BorderLayout.EAST);
        //===========================================================

        JLabel sliderSurviveMaxLabel = new JLabel("Max number of neighbors to survive", JLabel.CENTER);
        sliderSurviveMaxLabel.setFont(font);
        centerWest.add(sliderSurviveMaxLabel);

        JSlider sliverSurviveMax = new JSlider(JSlider.HORIZONTAL, 0, 8, 3);
        sliverSurviveMax.setPreferredSize(new Dimension(300, 40));
        sliverSurviveMax.setMajorTickSpacing(2);
        sliverSurviveMax.setMinorTickSpacing(1);
        sliverSurviveMax.setPaintTicks(true);
        sliverSurviveMax.setPaintLabels(true);
        centerWest.add(sliverSurviveMax);

        sliverSurviveMax.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int mod = source.getValue();
                System.out.println("toSurvive_1 modifier = " + mod);
                panel.setToSurvive_1(mod);
            }
        });

        //===========================================================
        JLabel sliderSurviveMinLabel = new JLabel("Min number of neighbors to survive", JLabel.CENTER);
        sliderSurviveMinLabel.setFont(font);
        centerEast.add(sliderSurviveMinLabel);

        JSlider sliderSurviveMin = new JSlider(JSlider.HORIZONTAL, 0, 8, 2);
        sliderSurviveMin.setPreferredSize(new Dimension(300, 40));
        sliderSurviveMin.setMajorTickSpacing(2);
        sliderSurviveMin.setMinorTickSpacing(1);
        sliderSurviveMin.setPaintTicks(true);
        sliderSurviveMin.setPaintLabels(true);
        centerEast.add(sliderSurviveMin);

        sliderSurviveMin.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int mod = source.getValue();
                System.out.println("toSurvive_2 modifier = " + mod);
                panel.setToSurvive_2(mod);
            }
        });

        //===========================================================
        JPanel south = new JPanel();
        south.setLayout(new BorderLayout(1, 1));
        add(south, BorderLayout.SOUTH);

        JPanel southWest = new JPanel();
        southWest.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
        south.add(southWest, BorderLayout.WEST);

        JPanel southEast = new JPanel();
        southEast.setLayout(new FlowLayout());
        south.add(southEast, BorderLayout.CENTER);
        //===========================================================
        JLabel sliderToLiveLabel = new JLabel("Number of neighbors to live", JLabel.CENTER);
        sliderToLiveLabel.setFont(font);
        southWest.add(sliderToLiveLabel);

        JSlider sliderLive = new JSlider(JSlider.HORIZONTAL, 0, 8, 3);
        sliderLive.setPreferredSize(new Dimension(300, 40));
        sliderLive.setMajorTickSpacing(2);
        sliderLive.setMinorTickSpacing(1);
        sliderLive.setPaintTicks(true);
        sliderLive.setPaintLabels(true);
        southWest.add(sliderLive);

        sliderLive.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                int mod = source.getValue();
                System.out.println("toLive modifier = " + mod);
                panel.setToLive(mod);
            }
        });
        //===========================================================
        String instruction = "\"Middle Button\" to reset \n \"Right Button\" to pause";
        JTextArea instructArea = new JTextArea(instruction);
        instructArea.setBackground(new Color(240, 240, 240));
        instructArea.setFont(font);
        southEast.add(instructArea);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
