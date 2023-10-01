package DATPackage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;

public class BarGraphInitializationPanel extends JFrame {

    @Serial
    private static final long serialVersionUID = 8904563429485630467L;

    private final JTextField xAxisNameField;
    private final JTextField yAxisNameField;
    private final JTextField graphTitleField;
    private final JTextField categoryField;
    private final JTextField valueField;

    public BarGraphInitializationPanel(String title) {
        super(title);

        JLabel xAxisLabel = new JLabel("X-Axis Name:");
        xAxisNameField = new JTextField(20);

        JLabel yAxisLabel = new JLabel("Y-Axis Name:");
        yAxisNameField = new JTextField(20);

        JLabel titleLabel = new JLabel("Graph Title:");
        graphTitleField = new JTextField(20);

        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField(20);

        JLabel valueLabel = new JLabel("Value:");
        valueField = new JTextField(20);

        JButton addButton = new JButton("Add Data");
        JButton createButton = new JButton("Create Graph");

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        addButton.addActionListener(e -> {
            String category = categoryField.getText();
            String valueText = valueField.getText();

            try {
                double value = Double.parseDouble(valueText);
                dataset.addValue(value, category, category);
                categoryField.setText("");
                valueField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric value for the data.");
            }
        });

        createButton.addActionListener(e -> {
            String xAxisName = xAxisNameField.getText();
            String yAxisName = yAxisNameField.getText();
            String graphTitle = graphTitleField.getText();

            JFreeChart barChart = ChartFactory.createBarChart(
                    graphTitle,
                    xAxisName,
                    yAxisName,
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            JPanel chartPanelContainer = new JPanel();
            chartPanelContainer.add(chartPanel);
            chartPanelContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

            JFrame frame = new JFrame("Bar Graph");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(chartPanelContainer);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(xAxisLabel);
        panel.add(xAxisNameField);
        panel.add(yAxisLabel);
        panel.add(yAxisNameField);
        panel.add(titleLabel);
        panel.add(graphTitleField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(valueLabel);
        panel.add(valueField);
        panel.add(addButton);
        panel.add(createButton);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BarGraphInitializationPanel panel = new BarGraphInitializationPanel("Bar Graph Initialization Panel");
            panel.setVisible(true);
        });
    }
}
