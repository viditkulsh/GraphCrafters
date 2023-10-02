package DATPackage;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class savePanel extends JFrame {

    private static final long serialVersionUID = -6106838368264568606L;

	savePanel(JFreeChart chart) {
        JDialog dialog = new JDialog();
        JPanel tbl1 = new JPanel();
        JPanel tbl2 = new JPanel();
        dialog.setTitle("Download Chart");
        dialog.setSize(400, 200);
        dialog.setLayout(new GridLayout(3, 1, 60, 0));
        tbl1.setLayout(new GridLayout(1, 2));
        tbl2.setLayout(new GridLayout(1, 2));

        JLabel nameLabel = new JLabel("Filename:");
        JTextField nameField = new JTextField();
        JLabel formatLabel = new JLabel("Format:");
        JComboBox < String > formatComboBox = new JComboBox < > (new String[] {
            "JPEG",
            "PNG"
        });

        tbl1.add(nameLabel);
        tbl1.add(nameField);
        tbl2.add(formatLabel);
        tbl2.add(formatComboBox);

        JButton saveButton = new JButton("Save");

        dialog.add(tbl1);
        dialog.add(tbl2);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws NumberFormatException {
                String filename = nameField.getText();
                String format = (String) formatComboBox.getSelectedItem();

                String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
                File file = new File(desktopPath, filename + "." + format.toLowerCase());
                try {
                    int chartWidth = 800;
                    int chartHeight = 600;
                    java.awt.image.BufferedImage chartImage = new java.awt.image.BufferedImage(chartWidth, chartHeight, java.awt.image.BufferedImage.TYPE_INT_RGB);

                    Graphics2D g2 = chartImage.createGraphics();
                    chart.draw(g2, new Rectangle2D.Double(0, 0, chartWidth, chartHeight));
                    g2.dispose();

                    int width = chartImage.getWidth();
                    int height = chartImage.getHeight();

                    if ("JPEG".equals(format)) {
                        ChartUtilities.saveChartAsJPEG(file, chart, width, height);
                    } else if ("PNG".equals(format)) {
                        ChartUtilities.saveChartAsPNG(file, chart, width, height);
                    }
                    dialog.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error saving the chart.");
                }
            }
        });

        dialog.add(saveButton);
        dialog.setResizable(false);
        setLocationRelativeTo(null);
        dialog.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}