package DATPackage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;

public class PieGraphInitializationPanel extends JFrame {

    @Serial
    private static final long serialVersionUID = 8904563429485630467L;

    private final JTextField categoryField;
    private final JTextField valueField;

    public PieGraphInitializationPanel(String title) {
        super(title);

        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField(20);

        JLabel valueLabel = new JLabel("Value:");
        valueField = new JTextField(20);

        JButton addButton = new JButton("Add Data");
        JButton createButton = new JButton("Create Graph");

        DefaultPieDataset dataset = new DefaultPieDataset();

        addButton.addActionListener(e -> {
            String category = categoryField.getText();
            String valueText = valueField.getText();

            try {
                double value = Double.parseDouble(valueText);
                dataset.setValue(category, value);
                categoryField.setText("");
                valueField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value for the data.");
            }
        });

        createButton.addActionListener(e -> {
            String graphTitle = "Pie Chart";

            JFreeChart pieChart = createPieChart(graphTitle, dataset);

            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            JPanel chartPanelContainer = new JPanel();
            chartPanelContainer.add(chartPanel);
            chartPanelContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

            JFrame frame = new JFrame("Pie Chart");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(chartPanelContainer);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(valueLabel);
        panel.add(valueField);
        panel.add(addButton);
        panel.add(createButton);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    private JFreeChart createPieChart(String title, PieDataset dataset) {
        return ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PieGraphInitializationPanel panel = new PieGraphInitializationPanel("Pie Chart Initialization Panel");
            panel.setVisible(true);
        });
    }
}
